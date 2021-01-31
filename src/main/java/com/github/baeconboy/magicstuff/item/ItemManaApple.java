package com.github.baeconboy.magicstuff.item;

import com.github.baeconboy.magicstuff.Components;
import com.github.baeconboy.magicstuff.base.ItemBase;
import com.github.baeconboy.magicstuff.component.ManaComponent;
import com.github.baeconboy.magicstuff.component.PlayerManaComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class ItemManaApple extends ItemBase {

    public static String itemName = "mana_apple";
    public static Settings settings = new Settings()
            .group(ItemGroup.FOOD)
            .maxCount(64)
            .food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.3F).alwaysEdible().build());
    private static final ComponentKey<ManaComponent> mana = Components.MANA;


    public ItemManaApple() {
        super(settings, itemName);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            PlayerManaComponent comp = (PlayerManaComponent) mana.get(serverPlayerEntity);
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            comp.usedManaApple();
        }

        if (user instanceof PlayerEntity && !((PlayerEntity)user).abilities.creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            user.clearStatusEffects();
        }

        return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
