package net.jolene.jojosquared.stand.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public abstract class StandAbility {
    public abstract void actionPress(int pressContext);
    public abstract void actionRelease(int pressContext, int ticksHeldFor);

    public abstract String getTranslationKey();
    public abstract int getCooldown();

    public void tick() {};
    @Environment(EnvType.CLIENT)
    public void render(EntityRenderState state, MatrixStack matrices, float age, VertexConsumerProvider vertexConsumers) {};
}