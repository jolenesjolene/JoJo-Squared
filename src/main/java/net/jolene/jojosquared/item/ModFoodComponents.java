package net.jolene.jojosquared.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent ROKAKAKA = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).alwaysEdible().build();
    public static final FoodComponent GOLDEN_ROKAKAKA = new FoodComponent.Builder().nutrition(4).saturationModifier(1.2F).alwaysEdible().build();
}
