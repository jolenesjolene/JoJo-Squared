package net.jolene.jojosquared.entity;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModEntities {
    public static void registerModEntities()
    {
        JoJoSquared.LOGGER.info("Registering Entities for {}", JoJoSquared.MOD_ID);
    }

    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    private static RegistryKey<EntityType<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, JoJoSquared.location(id));
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }

    public static EntityType<StandEntity> STAR_PLATINUM = register("star_platinum",
            EntityType.Builder.create(
                            StandEntity::new,
                            SpawnGroup.MISC
                    )
                    .dimensions(0.6F, 1.8F)
                    .eyeHeight(1.62F)
                    .vehicleAttachment(0.04F)
                    .maxTrackingRange(8)
                    .trackingTickInterval(2)
    );
}