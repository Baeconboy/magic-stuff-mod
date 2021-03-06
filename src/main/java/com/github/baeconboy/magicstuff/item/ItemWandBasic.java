package com.github.baeconboy.magicstuff.item;

import com.github.baeconboy.magicstuff.Components;
import com.github.baeconboy.magicstuff.base.ItemBase;
import com.github.baeconboy.magicstuff.component.ManaComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemWandBasic extends ItemBase {

    public static String itemName = "basic_wand";
    public static Settings settings = new Settings()
            .group(ItemGroup.COMBAT)
            .maxCount(1);
    private static final ComponentKey<ManaComponent> mana = Components.MANA;


    public ItemWandBasic() {
        super(ItemWandBasic.settings, itemName);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        ManaComponent comp = mana.get(player);

        if (!player.getEntityWorld().isClient()) {
            if (comp.useMana(100)) {
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.fail(itemStack);
    }
}

