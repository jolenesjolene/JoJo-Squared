package net.jolene.jojosquared.client.stand;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.entity.AnimationState;

/// Helper class for storing stand animations.
public record StandAnimation(Animation animation, AnimationState state) { }