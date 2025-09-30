package net.jolene.jojosquared.client.stand.renderer;

import com.mojang.blaze3d.opengl.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.client.stand.model.StarPlatinumModel;
import net.jolene.jojosquared.client.stand.state.StarPlatinumRenderState;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.client.MinecraftClient;
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

        Stand owner = state.entity.getOwner();
        if (owner == null)
            return;
        LivingEntity ownerEnt = owner.getOwner();
        if (ownerEnt == null)
            return;

        owner.render(state, matrices, state.age, vertexConsumers);

        matrices.push();

        matrices.translate(-state.x, -state.y, -state.z); // reset pos so we have access to the raw world coords
        determinePos(state, matrices);

        matrices.translate(0f, state.standingEyeHeight + Math.sin(state.age / 12.0) * 0.125, 0f);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(state.entity.renderYaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));

        if (state.entity.idle.isRunning() || (state.entity.manifest.isRunning() || state.entity.withdraw.isRunning()))
        {
            addRenderOffset(state, matrices);

            this.model.setAngles(state);

            float modelPitch = (state.entity.renderPitch / 2.0f) - 270.0f;
            this.model.setTorsoAngles(modelPitch, 0.0f, 0.0f);
            this.model.setHeadAngles(modelPitch, 0.0f, 0.0f);
            matrices.translate(state.floatDistance, 0F, state.floatDistance);
        }
        else {
            this.model.setAngles(state);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.entity.renderPitch - 180.0f));
            matrices.translate(state.floatDistance, 0F, -state.floatDistance);
            addRenderOffset(state, matrices);
        }

        GlStateManager._disableCull();
        this.model.render(matrices, vertexConsumers.getBuffer(getRenderLayer()), light, OverlayTexture.DEFAULT_UV);
        GlStateManager._enableCull();

        matrices.pop();
        // super.render(state, matrices, vertexConsumers, light); // not needed, just renders leads and such
    }

    @Override // gets called before render, sets up the state
    public void updateRenderState(StandEntity entity,
                                  StarPlatinumRenderState state,
                                  float tickProgress) {
        super.updateRenderState(entity, state, tickProgress); // sets standingEyeHeight, x, y, z, etc...

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null)
            return;
        if (entity.getOwner() == null || entity.getOwner().getOwner() == null)
            return;

        state.owns = entity.getOwner().getOwner().equals(client.player);
//        if (!state.owns)
//            return;

        state.entity = entity;

        state.entity.realYaw = entity.getBodyYaw(); // left-right
        state.entity.realPitch = (entity.getPitch() - 180.f) % 360.0f; // up-down

        float lerpFactor = 0.1f;
        state.entity.renderYaw = MathHelper.lerpAngleDegrees(lerpFactor, state.entity.renderYaw, state.entity.realYaw);
        state.entity.renderPitch = MathHelper.lerpAngleDegrees(lerpFactor, state.entity.renderPitch, state.entity.realPitch);

        state.entity.renderOffset = state.entity.getOwner().getRenderOffset();

        LivingEntity ownerEnt = state.entity.getOwner().getOwner();
        if (ownerEnt == null)
            return;

        // Minecraft automatically interpolates state.x,y,z but we don't want their way of doing it
        state.entity.desiredX = entity.getX();
        state.entity.desiredY = entity.getY();
        state.entity.desiredZ = entity.getZ();

        if (state.entity.renderX == 0f && state.entity.renderY == 0f && state.entity.renderZ == 0f)
        {
            state.entity.renderX = state.entity.desiredX;
            state.entity.renderY = state.entity.desiredY;
            state.entity.renderZ = state.entity.desiredZ;
        }

        if (state.animStates == null)
        {
            if (entity.getOwner() != null)
                state.animStates = entity.getOwner().createAnimationStates();
        }

        state.manifest.copyFrom(state.entity.manifest);
        state.withdraw.copyFrom(state.entity.withdraw);
        state.passive.copyFrom(state.entity.passive);
        state.idle.copyFrom(state.entity.idle);
        state.default_hold.copyFrom(state.entity.default_hold);
        state.default_1.copyFrom(state.entity.default_1);
        state.default_2.copyFrom(state.entity.default_2);
        state.default_3.copyFrom(state.entity.default_3);
    }

    private void determinePos(StarPlatinumRenderState state, MatrixStack matrices)
    {
        double lerpFactor = 0.1;

        state.entity.renderX += (state.entity.desiredX - state.entity.renderX) * lerpFactor;
        state.entity.renderY += (state.entity.desiredY - state.entity.renderY) * lerpFactor;
        state.entity.renderZ += (state.entity.desiredZ - state.entity.renderZ) * lerpFactor;

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