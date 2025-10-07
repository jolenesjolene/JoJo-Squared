package net.jolene.jojosquared.entity.client;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.entity.custom.SteelBallProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class SteelBallProjectileRenderer extends EntityRenderer<SteelBallProjectileEntity, SteelBallProjectileRenderer.SpinState> {
    protected SteelBallProjectileModel model;

    public static class SpinState extends EntityRenderState {
        public float ageWithDelta = 0f;
    }

    public SteelBallProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new SteelBallProjectileModel(ctx.getPart(SteelBallProjectileModel.STEEL_BALL));
    }

    @Override
    public SpinState createRenderState() {
        return new SpinState();
    }

    @Override
    public void updateRenderState(SteelBallProjectileEntity entity, SpinState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.ageWithDelta = entity.age + tickDelta;
    }

    @Override
    public void render(SpinState state, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        float yOffset = -1.0f;
        matrices.translate(0.0, yOffset, 0.0);

        float rotationDegrees = state.ageWithDelta * 36.0f;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationDegrees));

        VertexConsumer vertexconsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(JoJoSquared.MOD_ID, "textures/entity/steel_ball/steel_ball.png")), false, false);
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();

        super.render(state, matrices, vertexConsumers, light);
    }
}

