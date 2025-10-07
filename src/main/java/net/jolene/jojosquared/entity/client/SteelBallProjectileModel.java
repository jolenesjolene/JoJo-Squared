package net.jolene.jojosquared.entity.client;

import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.client.model.*;
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
        ModelPartData steel_ball = modelPartData.addChild("steel_ball", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 16.5F, 0.0F));

        ModelPartData cube_r1 = steel_ball.addChild("cube_r1", ModelPartBuilder.create().uv(8, 7).cuboid(1.5F, 2.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r2 = steel_ball.addChild("cube_r2", ModelPartBuilder.create().uv(7, 9).cuboid(0.5F, -1.5F, -0.5F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r3 = steel_ball.addChild("cube_r3", ModelPartBuilder.create().uv(3, 10).cuboid(-2.5F, -1.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r4 = steel_ball.addChild("cube_r4", ModelPartBuilder.create().uv(1, 4).cuboid(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r5 = steel_ball.addChild("cube_r5", ModelPartBuilder.create().uv(18, 1).cuboid(-0.5F, -9.0F, -0.5F, 1.0F, 18.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
}
