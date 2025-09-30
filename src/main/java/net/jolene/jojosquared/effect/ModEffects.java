package net.jolene.jojosquared.effect;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.effect.soft_and_wet.PlunderFriction;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> MENACING = registerStatusEffect("menacing",
            new Menacing(StatusEffectCategory.NEUTRAL, 0x5b8eeb)
    );
    public static final RegistryEntry<StatusEffect> EQUIVALENT_EXCHANGE = registerStatusEffect("equivalent_exchange",
            new EquivalentExchange(StatusEffectCategory.BENEFICIAL, 0x5b8eeb)
    );
    public static final RegistryEntry<StatusEffect> PLUNDER_FRICTION = registerStatusEffect("plunder_friction",
            new PlunderFriction(StatusEffectCategory.HARMFUL, 0x783f04)
    );
    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
            return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(JoJoSquared.MOD_ID, name), statusEffect);
        }

        public static void registerEffects() {
            JoJoSquared.LOGGER.info("Registering Effects for {}", JoJoSquared.MOD_ID);
        }
    }