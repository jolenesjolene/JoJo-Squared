package net.jolene.jojosquared.entity.client;

import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.Identifier;

public class SteelBallProjectileModel extends EntityModel<EntityRenderState> {
    public static final EntityModelLayer STEEL_BALL = new EntityModelLayer(Identifier.of(JoJoSquared.MOD_ID, "steel_ball"), "main");
    private final ModelPart steel_ball;

    public SteelBallProjectileModel(ModelPart root) {
        super(root);
        this.steel_ball = root.getChild("steel_ball");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData steel_ball = modelPartData.addChild("steel_ball", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, 1.0F, -4.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-4.0F, 3.0F, -2.0F, 8.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 16.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
}
