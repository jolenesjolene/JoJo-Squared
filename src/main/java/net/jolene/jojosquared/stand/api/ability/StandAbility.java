package net.jolene.jojosquared.stand.api.ability;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.stand.api.Stand;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public abstract class StandAbility {
    public abstract void actionPress(int pressContext);
    public abstract void actionRelease(int pressContext, int ticksHeldFor);

    public abstract String getTranslationKey();
    public abstract int getCooldown();
    public Stand getOwner() {return null;};

    public void tick() {};
    /// Only override if the ability is a region ability.
    public boolean affectsEntity(Entity entity, boolean excludeOwner) { return false; };
    @Environment(EnvType.CLIENT)
    public void render(EntityRenderState state, MatrixStack matrices, float age, VertexConsumerProvider vertexConsumers) {};
}