package net.jolene.jojosquared.network.api;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModOrigin;
import net.jolene.jojosquared.JoJoSquared;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/// Scans all the loaded mods and the source directory (if applicable) for MessageListeners.
/// WARNING: this class is super experimental!
public class MessageListenerScanner {
    public List<Class<?>> scan() {
        List<Class<?>> allHits = new ArrayList<>();

        for (Path modPath : allModPaths()) {
            if (modPath.toString().endsWith("build\\resources\\main"))
            {
                allHits.addAll(scanModPath(
                        Path.of(modPath.toString().split("build\\\\resources\\\\main")[0] + "build\\classes\\java\\main")
                ));
            }
            else
                allHits.addAll(scanModPath(modPath));
        }

        return allHits.stream().distinct().collect(Collectors.toList());
    }

    private static List<Path> allModPaths() {
        return FabricLoader.getInstance()
                .getAllMods()
                .stream()
                .map(ModContainer::getOrigin)
                .flatMap(origin -> {
                    if (origin.getKind() != ModOrigin.Kind.PATH)
                        return null;
                    return origin.getPaths().stream();
                })
                .collect(Collectors.toList());
    }

    private List<Class<?>> scanModPath(Path path) {
        if (Files.isDirectory(path)) {
            return scanDirectory(path);
        }
        else {
            return scanJar(path);
        }
    }

    private List<Class<?>> scanJar(Path jarPath) {
        List<Class<?>> hits = new ArrayList<>();
        try (JarFile jar = new JarFile(jarPath.toFile())) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry e = entries.nextElement();
                if (!e.getName().endsWith(".class")) continue;

                String name = toClassName(e.getName());
                if (name.toLowerCase().contains("mixin")) continue;

                loadIfAnnotated(name).ifPresent(hits::add);
            }
        } catch (IOException ex) {
            JoJoSquared.LOGGER.warn("Failed to read mod jar {}: {}", jarPath, ex.toString());
        }
        return hits;
    }

    private List<Class<?>> scanDirectory(Path root) {
        List<Class<?>> hits = new ArrayList<>();
        try {
            Files.walk(root)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".class"))
                    .forEach(p -> {
                        String rel = root.relativize(p).toString();
                        String name = toClassName(rel);
                        if (!name.contains(".mixin.")) {
                            loadIfAnnotated(name).ifPresent(hits::add);
                        }
                    });
        } catch (IOException ex) {
            JoJoSquared.LOGGER.warn("Failed to scan mod dir {}: {}", root, ex.toString());
        }
        return hits;
    }

    private String toClassName(String entryName) {
        return entryName.replace('/', '.')
                .replace('\\', '.')
                .replaceAll("\\.class$", "");
    }

    private Optional<Class<?>> loadIfAnnotated(String className) {
        try {
            Class<?> c = Class.forName(className, false,
                    Thread.currentThread().getContextClassLoader());
            boolean has = Arrays.stream(c.getMethods())
                    .anyMatch(m -> m.isAnnotationPresent(MessageListener.class));
            if (has) {
                JoJoSquared.LOGGER.info("Found @MessageListener in {}", className);
                return Optional.of(c);
            }
        } catch (ClassNotFoundException | RuntimeException | LinkageError ignored) { }
        return Optional.empty();
    }
}