package net.jolene.jojosquared.client.stand.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.AnimationState;
import net.minecraft.util.math.Vec3d;

import java.util.List;

@Environment(EnvType.CLIENT)
public class StarPlatinumRenderState extends EntityRenderState {
    public StandEntity entity;
    public float renderYaw, renderPitch;
    public float realYaw, realPitch;

    public Vec3d renderOffset;
    public double renderX, renderY, renderZ = 0f;
    public double desiredX, desiredY, desiredZ;
    public Vec3d renderOffsetPos = new Vec3d(0., 0., 0.);

    public float floatDistance = 0.425f;
    public float age;

    public List<AnimationState> animStates;

    public StarPlatinumRenderState()
    { }
}