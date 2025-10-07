package net.jolene.jojosquared.entity.client;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.entity.custom.SteelBallProjectileEntity;
import net.jolene.jojosquared.entity.custom.ThrowingKnifeProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ThrowingKnifeProjectileRenderer extends EntityRenderer<ThrowingKnifeProjectileEntity, EntityRenderState> {
    protected ThrowingKnifeProjectileModel model;

    public ThrowingKnifeProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new ThrowingKnifeProjectileModel(ctx.getPart(ThrowingKnifeProjectileModel.THROWING_KNIFE));
    }

    @Override
    public void render(EntityRenderState state, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        VertexConsumer vertexconsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(JoJoSquared.MOD_ID, "textures/entity/steel_ball/steel_ball.png")), false, false);
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
