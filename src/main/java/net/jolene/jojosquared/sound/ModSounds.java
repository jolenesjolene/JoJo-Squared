package net.jolene.jojosquared.sound;

import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent MORIOH_CHO_RADIO = registerSoundEvent("morioh_cho_radio");
public static final RegistryKey<JukeboxSong> MORIOH_CHO_RADIO_KEY =
        RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(JoJoSquared.MOD_ID, "morioh_cho_radio"));


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(JoJoSquared.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        JoJoSquared.LOGGER.info("Registering Sounds for " + JoJoSquared.MOD_ID);
    }
}
