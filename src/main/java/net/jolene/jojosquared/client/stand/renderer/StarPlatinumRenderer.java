package net.jolene.jojosquared.client.stand.renderer;

import com.mojang.blaze3d.opengl.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.client.stand.model.StarPlatinumModel;
import net.jolene.jojosquared.client.stand.state.StarPlatinumRenderState;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class StarPlatinumRenderer extends EntityRenderer<StandEntity, StarPlatinumRenderState> {
    private final StarPlatinumModel<StandEntity> model;

    public StarPlatinumRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new StarPlatinumModel<>(context.getPart(StarPlatinumModel.layer));
    }

    @Override
    public StarPlatinumRenderState createRenderState() {
        return new StarPlatinumRenderState();
    }

    private RenderLayer getRenderLayer()
    {
        return RenderLayer.getEntityCutoutNoCull(JoJoSquared.location("textures/entity/stand/star_platinum.png"));
    }

    @Override
    public void render(StarPlatinumRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (state.entity == null)
            return;
        Stand stand = state.entity.getOwner();
        if (stand == null)
            return;
        LivingEntity standOwner = stand.getOwner();
        if (standOwner == null)
            return;

        // yaw = left-right
        // pitch = up-down
        state.entity.bodyYaw = (float) MathHelper.lerp(state.tickProgress, standOwner.lastBodyYaw, standOwner.bodyYaw); // interpolates to get the real head yaw
        state.entity.headYaw = standOwner.getHeadYaw();
        state.entity.realPitch = standOwner.getPitch();

        float lerpFactor = 0.1f;
        state.entity.renderBodyYaw = MathHelper.lerpAngleDegrees(lerpFactor, state.entity.renderBodyYaw, state.entity.bodyYaw);
        state.entity.renderHeadYaw = MathHelper.lerpAngleDegrees(lerpFactor, state.entity.renderHeadYaw, state.entity.headYaw);
        state.entity.renderPitch = MathHelper.lerpAngleDegrees(lerpFactor, state.entity.renderPitch, state.entity.realPitch);

        state.entity.renderOffset = stand.getRenderOffset();

        state.entity.dx = MathHelper.lerp(state.tickProgress, standOwner.lastRenderX, standOwner.getX());
        state.entity.dy = MathHelper.lerp(state.tickProgress, standOwner.lastRenderY, standOwner.getY());
        state.entity.dz = MathHelper.lerp(state.tickProgress, standOwner.lastRenderZ, standOwner.getZ());

        if (state.entity.renderX == 0f && state.entity.renderY == 0f && state.entity.renderZ == 0f)
        {
            state.entity.renderX = state.entity.dx;
            state.entity.renderY = state.entity.dy;
            state.entity.renderZ = state.entity.dz;
        }

        stand.render(state, matrices, state.age, vertexConsumers);

        matrices.push();

        matrices.translate(-state.x, -state.y, -state.z); // reset pos so we have access to the raw world coords
        determinePos(state, matrices);

        matrices.translate(0f, state.standingEyeHeight + Math.sin(state.age / 12.0) * 0.125, 0f);

        this.model.setAngles(state);

        float headBodyYawOffset = state.entity.renderHeadYaw - state.entity.renderBodyYaw;
        if (state.idle.isRunning() || (state.manifest.isRunning() || state.withdraw.isRunning()))
        {
            matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(state.entity.renderBodyYaw));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));

            addRenderOffset(state, matrices);

            float modelPitch = state.entity.renderPitch / 2f;
            this.model.setTorsoAngles(modelPitch, 0.0f, 0.0f);
            this.model.setHeadAngles(modelPitch, headBodyYawOffset, 0.0f);
            matrices.translate(state.entity.floatDistance, 0F, state.entity.floatDistance);
        }
        else {
            matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(state.entity.renderHeadYaw));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.entity.renderPitch));

            matrices.translate(state.entity.floatDistance, 0F, -state.entity.floatDistance);
            addRenderOffset(state, matrices);
        }

        GlStateManager._disableCull();
        this.model.render(matrices, vertexConsumers.getBuffer(getRenderLayer()), light, OverlayTexture.DEFAULT_UV);
        GlStateManager._enableCull();

        matrices.pop();
        // super.render(state, matrices, vertexConsumers, light); // not needed, just renders leads and such
    }

    @Override // gets called before render, sets up the state
    // NOTE: state has to be deterministic. states are shared between entities!!!
    public void updateRenderState(StandEntity entity,
                                  StarPlatinumRenderState state,
                                  float tickProgress) {
        super.updateRenderState(entity, state, tickProgress); // sets standingEyeHeight, x, y, z, etc...
        state.entity = entity;
        state.tickProgress = tickProgress;

        state.manifest.copyFrom(entity.manifest);
        state.withdraw.copyFrom(entity.withdraw);
        state.passive.copyFrom(entity.passive);
        state.idle.copyFrom(entity.idle);
        state.default_hold.copyFrom(entity.default_hold);
        state.default_1.copyFrom(entity.default_1);
        state.default_2.copyFrom(entity.default_2);
        state.default_3.copyFrom(entity.default_3);
    }

    private void determinePos(StarPlatinumRenderState state, MatrixStack matrices)
    {
        double lerpFactor = 0.9;

        state.entity.renderX += (state.entity.dx - state.entity.renderX) * lerpFactor;
        state.entity.renderY += (state.entity.dy - state.entity.renderY) * lerpFactor;
        state.entity.renderZ += (state.entity.dz - state.entity.renderZ) * lerpFactor;

        matrices.translate(state.entity.renderX, state.entity.renderY, state.entity.renderZ);
    }

    private void addRenderOffset(StarPlatinumRenderState state, MatrixStack matrices)
    {
        double lerpFactor = 0.1;

        double x = (state.entity.renderOffset.x - state.entity.renderOffsetPos.x) * lerpFactor;
        double y = (state.entity.renderOffset.y - state.entity.renderOffsetPos.y) * lerpFactor;
        double z = (state.entity.renderOffset.z - state.entity.renderOffsetPos.z) * lerpFactor;

        state.entity.renderOffsetPos = state.entity.renderOffsetPos.add(new Vec3d(x, y, z));
        matrices.translate(state.entity.renderOffsetPos);
    }
}