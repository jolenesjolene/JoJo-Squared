package net.jolene.jojosquared.util;

import net.jolene.jojosquared.JoJoSquared;
import xyz.zetalasis.limelight.util.config.api.ModConfig;

public class JoJoSquaredConfig extends ModConfig {
    @Override
    public String getModId() {
        return JoJoSquared.MOD_ID;
    }

    @Override
    public String getType() {
        return "Common";
    }
}