package net.jolene.jojosquared.client.stand.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.AnimationState;

@Environment(EnvType.CLIENT)
public class StarPlatinumRenderState extends EntityRenderState {
    public StandEntity entity;
    public double tickProgress;

    public AnimationState manifest = new AnimationState();
    public AnimationState withdraw = new AnimationState();
    public AnimationState idle = new AnimationState();
    public AnimationState passive = new AnimationState();
    public AnimationState default_hold = new AnimationState();
    public AnimationState default_1 = new AnimationState();
    public AnimationState default_2 = new AnimationState();
    public AnimationState default_3 = new AnimationState();
}