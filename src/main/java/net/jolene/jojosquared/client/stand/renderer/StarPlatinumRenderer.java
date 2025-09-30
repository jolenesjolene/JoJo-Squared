package net.jolene.jojosquared.client.stand.renderer;

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
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class StarPlatinumRenderer extends EntityRenderer<StandEntity, StarPlatinumRenderState> {
    private final EntityModel<StarPlatinumRenderState> model;

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
        return RenderLayer.getEntityCutout(JoJoSquared.location("textures/entity/stand/star_platinum.png"));
    }

    @Override
    public void render(StarPlatinumRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (state.entity == null)
            return;

        Stand owner = state.entity.getOwner();
        if (owner == null)
            return;
        matrices.push();

        matrices.translate(-state.x, -state.y, -state.z); // reset pos so we have access to the raw world coords
        determinePos(state, matrices);

        matrices.translate(0f, state.standingEyeHeight, 0f);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(state.renderYaw));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));
        matrices.translate(state.floatDistance, 0F, state.floatDistance);

        addRenderOffset(state, matrices);

        this.model.setAngles(state);
        this.model.render(matrices, vertexConsumers.getBuffer(getRenderLayer()), light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        // super.render(state, matrices, vertexConsumers, light); // not needed, just renders leads and such
    }

    @Override // gets called before render, sets up the state
    public void updateRenderState(StandEntity entity,
                                  StarPlatinumRenderState state,
                                  float tickProgress) {
        super.updateRenderState(entity, state, tickProgress); // sets standingEyeHeight, x, y, z, etc...

        if (entity.getOwner() == null)
            return;

        state.entity = entity;

        state.realYaw = entity.getBodyYaw();
        state.realPitch = entity.getPitch();

        float lerpFactor = 0.1f;
        state.renderYaw = MathHelper.lerpAngleDegrees(lerpFactor, state.renderYaw, state.realYaw);
        state.renderPitch = MathHelper.lerpAngleDegrees(lerpFactor, state.renderPitch, state.realPitch);

        state.renderOffset = state.entity.getOwner().getRenderOffset();

        // Minecraft automatically interpolates state.x,y,z but we don't want their way of doing it
        state.desiredX = entity.getX();
        state.desiredY = entity.getY();
        state.desiredZ = entity.getZ();

        if (state.renderX == 0f && state.renderY == 0f && state.renderZ == 0f)
        {
            state.renderX = state.desiredX;
            state.renderY = state.desiredY;
            state.renderZ = state.desiredZ;
        }

        state.age = entity.age;

        if (state.animStates == null)
        {
            if (entity.getOwner() != null)
                state.animStates = entity.getOwner().createAnimationStates();
        }

        if (state.animStates != null)
        {
            int index = 0;
            for (AnimationState animState : state.animStates)
            { animState.copyFrom(state.entity.animations.get(index++)); }
        }
    }

    private void determinePos(StarPlatinumRenderState state, MatrixStack matrices)
    {
        double lerpFactor = 0.1;

        state.renderX += (state.desiredX - state.renderX) * lerpFactor;
        state.renderY += (state.desiredY - state.renderY) * lerpFactor;
        state.renderZ += (state.desiredZ - state.renderZ) * lerpFactor;

        matrices.translate(state.renderX, state.renderY, state.renderZ);
    }

    private void addRenderOffset(StarPlatinumRenderState state, MatrixStack matrices)
    {
        double lerpFactor = 0.1;

        double x = (state.renderOffset.x - state.renderOffsetPos.x) * lerpFactor;
        double y = (state.renderOffset.y - state.renderOffsetPos.y) * lerpFactor;
        double z = (state.renderOffset.z - state.renderOffsetPos.z) * lerpFactor;

        state.renderOffsetPos = state.renderOffsetPos.add(new Vec3d(x, y, z));
        matrices.translate(state.renderOffsetPos);
    }
}