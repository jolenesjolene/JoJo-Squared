package net.jolene.jojosquared.client.stand.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.client.stand.StandAnimation;
import net.jolene.jojosquared.client.stand.anim.StarPlatinumAnims;
import net.jolene.jojosquared.client.stand.state.StarPlatinumRenderState;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationDefinition;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.AnimationState;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

	private final List<StandAnimation> animations = new ArrayList<>();

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

		ModelPartData Body = StarPlatinum.addChild("Body", ModelPartBuilder.create().uv(32, 21).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(32, 37).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(0.0F, -13.0F, 0.0F));

		ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(0, 21).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 12.0F, new Dilation(0.5F)), ModelTransform.origin(0.0F, -12.0F, 0.0F));

		ModelPartData ClothFront = Body.addChild("ClothFront", ModelPartBuilder.create().uv(56, 16).cuboid(-3.0F, 0.0F, -0.025F, 6.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, -2.175F));

		ModelPartData ClothBack = Body.addChild("ClothBack", ModelPartBuilder.create().uv(56, 23).cuboid(-3.0F, 0.0F, 0.025F, 6.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, 2.175F));

		ModelPartData RightArm = Body.addChild("RightArm", ModelPartBuilder.create().uv(0, 38).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(16, 38).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(-5.0F, -10.0F, 0.0F));

		ModelPartData LeftArm = Body.addChild("LeftArm", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(32, 53).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(5.0F, -10.0F, 0.0F));

		ModelPartData RightLeg = StarPlatinum.addChild("RightLeg", ModelPartBuilder.create().uv(48, 53).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 54).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(-2.0F, -13.0F, 0.0F));

		ModelPartData LeftLeg = StarPlatinum.addChild("LeftLeg", ModelPartBuilder.create().uv(16, 54).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
				.uv(56, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.origin(2.0F, -13.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(StarPlatinumRenderState state) {
		super.setAngles(state);

		if (animations.isEmpty())
		{
			int index = 0;
			for (Field f : StarPlatinumAnims.class.getFields())
			{
				try
				{
					f.setAccessible(true);
					AnimationDefinition anim = (AnimationDefinition) f.get(null);
					animations.add(new StandAnimation(anim.createAnimation(root), state.animStates.get(index++)));
				} catch (Exception e) {
					throw new RuntimeException(e); // fatal crash. nothing we can do if a anim is missing
				}
			}
		}

		for (StandAnimation anim : animations)
		{
			anim.animation().apply(anim.state(), state.age);
		}
	}
}