package net.jolene.jojosquared.client.stand.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.client.stand.anim.StarPlatinumAnims;
import net.jolene.jojosquared.client.stand.state.StarPlatinumRenderState;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;

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

	public Animation manifest;
	public Animation withdraw;
	public Animation idle;
	public Animation passive;
	public Animation default_hold;
	public Animation default_1;
	public Animation default_2;
	public Animation default_3;

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

		this.manifest = StarPlatinumAnims.manifest.createAnimation(root);
		this.withdraw = StarPlatinumAnims.withdraw.createAnimation(root);
		this.idle = StarPlatinumAnims.idle.createAnimation(root);
		this.passive = StarPlatinumAnims.passive.createAnimation(root);
		this.default_hold = StarPlatinumAnims.default_hold.createAnimation(root);
		this.default_1 = StarPlatinumAnims.default_1.createAnimation(root);
		this.default_2 = StarPlatinumAnims.default_2.createAnimation(root);
		this.default_3 = StarPlatinumAnims.default_3.createAnimation(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData StarPlatinum = modelPartData.addChild("StarPlatinum", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 8.0F, 0.0F));

		ModelPartData Body = StarPlatinum.addChild("Body", ModelPartBuilder.create().uv(32, 21).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(32, 37).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(0.0F, 4.0F, 0.0F));

		ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(0, 21).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 12.0F, new Dilation(0.5F)), ModelTransform.origin(0.0F, -12.0F, 0.0F));

		ModelPartData ClothFront = Body.addChild("ClothFront", ModelPartBuilder.create().uv(56, 16).cuboid(-3.0F, 0.0F, -0.025F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, -2.175F));

		ModelPartData ClothBack = Body.addChild("ClothBack", ModelPartBuilder.create().uv(56, 22).cuboid(-3.0F, 0.0F, 0.025F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, 2.175F));

		ModelPartData RightArm = Body.addChild("RightArm", ModelPartBuilder.create().uv(0, 38).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(16, 38).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(-5.0F, -10.0F, 0.0F));

		ModelPartData LeftArm = Body.addChild("LeftArm", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(32, 53).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(5.0F, -10.0F, 0.0F));

		ModelPartData RightLeg = StarPlatinum.addChild("RightLeg", ModelPartBuilder.create().uv(48, 53).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 54).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(-2.0F, 4.0F, 0.0F));

		ModelPartData LeftLeg = StarPlatinum.addChild("LeftLeg", ModelPartBuilder.create().uv(16, 54).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(56, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(2.0F, 4.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(StarPlatinumRenderState state) {
		super.setAngles(state);
		this.manifest.apply(state.manifest, state.age);
		this.withdraw.apply(state.withdraw, state.age);
		this.idle.apply(state.idle, state.age);
		this.passive.apply(state.passive, state.age);
		this.default_hold.apply(state.default_hold, state.age);
		this.default_1.apply(state.default_1, state.age);
		this.default_2.apply(state.default_2, state.age);
		this.default_3.apply(state.default_3, state.age);
	}

	public void setTorsoAngles(float pitch, float yaw, float roll)
	{
		this.Body.setAngles((float) Math.toRadians(pitch), (float) Math.toRadians(yaw), (float) Math.toRadians(roll));
	}

	public void setHeadAngles(float pitch, float yaw, float roll)
	{
		this.Head.setAngles((float) Math.toRadians(pitch), (float) Math.toRadians(yaw), (float) Math.toRadians(roll));
	}
}