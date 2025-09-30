package net.jolene.jojosquared.client.stand.anim;// Save this class in your mod and generate all required imports

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.*;

@Environment(EnvType.CLIENT)
public class StarPlatinumAnims {
	public static final AnimationDefinition idle = AnimationDefinition.Builder.create(6.0F).looping()
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5F, AnimationHelper.createRotationalVector(2.5F, 0.0F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.0F, 5.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(2.0F, AnimationHelper.createRotationalVector(22.5F, -12.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createRotationalVector(-5.0F, 5.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(5.0F, AnimationHelper.createRotationalVector(12.5F, -12.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(2.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(5.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.0F, -5.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(2.0F, AnimationHelper.createRotationalVector(22.5F, 12.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createRotationalVector(-5.0F, -5.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(5.0F, AnimationHelper.createRotationalVector(12.5F, 12.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(5.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("StarPlatinum", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 4.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5F, AnimationHelper.createTranslationalVector(0.0F, 4.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.75F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.75F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5F, AnimationHelper.createRotationalVector(2.5F, 0.0F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 2.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 2.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(2.5F, 0.0F, -2.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.5F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, -2.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.75F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.75F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("ClothFront", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.75F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.75F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("ClothBack", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.75F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.0F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(4.75F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(6.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.build();

	public static final AnimationDefinition default_1 = AnimationDefinition.Builder.create(0.8F)
			.addBoneAnimation("root", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(18.0F, -14.0F, 8.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.LINEAR)
			))

			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-22.5F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(22.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-90.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-90.0F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-90.0F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(3.0F, 0.0F, 6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-90.0F, 67.5F, -22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-45.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-45.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(2.0F, -2.0F, -2.3F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(1.0F, -2.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(1.0F, -2.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(22.5F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(45.0F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(45.0F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, 2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(45.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(22.5F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(4.0F, 1.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition default_2 = AnimationDefinition.Builder.create(0.8F)
			.addBoneAnimation("root", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(14.0F, -14.0F, 8.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-22.5F, -67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(22.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-90.0F, -67.5F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(-2.0F, -2.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-1.0F, -2.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(-1.0F, -2.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-90.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-90.0F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-90.0F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(-3.0F, 0.0F, 6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(45.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(22.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(-4.0F, 1.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(22.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(45.0F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(45.0F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, 2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition default_3 = AnimationDefinition.Builder.create(0.8F)
			.addBoneAnimation("root", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 8.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(22.5F, -67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-45.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-45.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(22.5F, 0.0F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-90.0F, -67.5F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(-2.0F, -2.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-1.0F, -2.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(-1.0F, -2.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(-90.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-90.0F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-90.0F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(-3.0F, 0.0F, 6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-3.0F, 0.0F, -5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(-3.0F, 0.0F, -5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(67.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(67.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(67.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(-4.0F, 0.0F, -6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-2.0F, -1.0F, 2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(-2.0F, -1.0F, 2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createRotationalVector(45.0F, -22.5F, -22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-90.0F, -22.5F, -67.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createRotationalVector(-90.0F, -22.5F, -67.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.3F, AnimationHelper.createTranslationalVector(1.0F, -1.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(3.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.45F, AnimationHelper.createTranslationalVector(3.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.8F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition passive_idle = AnimationDefinition.Builder.create(1.5F)
			.addBoneAnimation("root", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createRotationalVector(22.5F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.0F, AnimationHelper.createRotationalVector(22.5F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(90.0F, 90.0F, 90.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.9F, AnimationHelper.createRotationalVector(90.0F, 90.0F, 90.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.9F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, -22.5F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-1.0F, -2.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-90.0F, 67.5F, -22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(0.0F, -2.0F, -2.3F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(22.5F, 45.0F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.9F, AnimationHelper.createRotationalVector(67.5F, 0.0F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(-2.0F, 0.0F, -4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.9F, AnimationHelper.createTranslationalVector(-2.0F, -2.0F, -2.38F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createRotationalVector(-22.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.9F, AnimationHelper.createRotationalVector(45.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.4F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.9F, AnimationHelper.createTranslationalVector(1.0F, -1.0F, 0.38F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("StarPlatinum", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition pose_1 = AnimationDefinition.Builder.create(1.8F)
			.addBoneAnimation("root", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 10.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-22.5F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createRotationalVector(-7.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-7.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-22.5F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-82.5F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(1.0F, -1.0F, 6.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-90.0F, 22.5F, -22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, -67.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, -67.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(1.0F, -1.0F, -3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createTranslationalVector(3.0F, -1.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(3.0F, -1.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(7.5F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createRotationalVector(7.5F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(7.5F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(45.0F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createRotationalVector(7.5F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(7.5F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -5.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.5F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition pose_2 = AnimationDefinition.Builder.create(1.8F)
			.addBoneAnimation("root", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.65F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-90.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(-112.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-112.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(2.0F, -3.0F, 3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(-2.0F, -3.0F, 3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-90.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(-112.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-112.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(-2.0F, -3.0F, 3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(2.0F, -3.0F, 3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(67.5F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-45.0F, 22.5F, 7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(-3.0F, 0.0F, 2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(-1.0F, 1.0F, -3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(67.5F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, -7.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(3.0F, 0.0F, 2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(1.0F, 1.0F, -3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition pose_3 = AnimationDefinition.Builder.create(1.8F)
			.addBoneAnimation("root", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, -4.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(16.0F, -14.0F, 4.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(-11.25F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("Head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, -1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-112.5F, -67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(-90.0F, -67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-90.0F, -67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(0.0F, -4.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(0.0F, -2.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(-112.5F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(-90.0F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(-90.0F, 67.5F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftArm", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(0.0F, -4.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(0.0F, -2.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(67.5F, 22.5F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(45.0F, 22.5F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(45.0F, 22.5F, 22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, 22.5F, 11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("RightLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(-2.0F, 0.0F, -3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(-0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createRotationalVector(67.5F, -22.5F, -22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createRotationalVector(45.0F, -22.5F, -22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createRotationalVector(45.0F, -22.5F, -22.5F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createRotationalVector(11.25F, -22.5F, -11.25F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("LeftLeg", new Transformation(Transformation.Targets.MOVE_ORIGIN,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.35F, AnimationHelper.createTranslationalVector(2.0F, 0.0F, -3.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(0.6F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.4F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, -2.0F), Transformation.Interpolations.CUBIC),
					new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 1.0F), Transformation.Interpolations.LINEAR)
			))
			.build();
}