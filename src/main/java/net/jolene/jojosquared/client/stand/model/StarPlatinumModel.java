package net.jolene.jojosquared.client.stand.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.client.stand.state.StarPlatinumRenderState;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class StarPlatinumModel<T extends StandEntity> extends EntityModel<StarPlatinumRenderState> {
	public static EntityModelLayer layer = new EntityModelLayer(JoJoSquared.location("star_platinum"), "star_platinum");

	private final ModelPart StarPlatinum;
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart ClothFront;
	private final ModelPart ClothBack;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;

	public StarPlatinumModel(ModelPart root) {
		super(root);
		this.StarPlatinum = root.getChild("StarPlatinum");
		this.Body = this.StarPlatinum.getChild("Body");
		this.Head = this.Body.getChild("Head");
		this.ClothFront = this.Body.getChild("ClothFront");
		this.ClothBack = this.Body.getChild("ClothBack");
		this.RightArm = this.Body.getChild("RightArm");
		this.LeftArm = this.Body.getChild("LeftArm");
		this.RightLeg = this.StarPlatinum.getChild("RightLeg");
		this.LeftLeg = this.StarPlatinum.getChild("LeftLeg");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData StarPlatinum = modelPartData.addChild("StarPlatinum", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 25.0F, 0.0F));

		ModelPartData Body = StarPlatinum.addChild("Body", ModelPartBuilder.create().uv(32, 20).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(32, 36).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(0.0F, -13.0F, 0.0F));

		ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(0, 20).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 11.0F, new Dilation(0.5F)), ModelTransform.origin(0.0F, -12.0F, 0.0F));

		ModelPartData ClothFront = Body.addChild("ClothFront", ModelPartBuilder.create().uv(56, 16).cuboid(-3.0F, 0.0F, -0.025F, 6.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, -2.175F));

		ModelPartData ClothBack = Body.addChild("ClothBack", ModelPartBuilder.create().uv(56, 23).cuboid(-3.0F, 0.0F, 0.025F, 6.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, 2.175F));

		ModelPartData RightArm = Body.addChild("RightArm", ModelPartBuilder.create().uv(0, 37).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(16, 37).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(-5.0F, -10.0F, 0.0F));

		ModelPartData LeftArm = Body.addChild("LeftArm", ModelPartBuilder.create().uv(38, 0).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(32, 52).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(5.0F, -10.0F, 0.0F));

		ModelPartData RightLeg = StarPlatinum.addChild("RightLeg", ModelPartBuilder.create().uv(48, 52).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 53).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(-2.0F, -13.0F, 0.0F));

		ModelPartData LeftLeg = StarPlatinum.addChild("LeftLeg", ModelPartBuilder.create().uv(16, 53).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(54, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(2.0F, -13.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
}