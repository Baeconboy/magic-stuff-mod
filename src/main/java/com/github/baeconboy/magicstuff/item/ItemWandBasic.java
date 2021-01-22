package com.github.baeconboy.magicstuff.item;

import com.github.baeconboy.magicstuff.base.ItemBase;
import com.github.baeconboy.magicstuff.mana.Mana;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemWandBasic extends ItemBase {

    public static String itemName = "basic_wand";
    public static Settings settings = new Settings()
            .group(ItemGroup.COMBAT)
            .maxCount(1);

    public ItemWandBasic() {
        super(ItemWandBasic.settings, itemName);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!player.getEntityWorld().isClient()) {
            if (Mana.use(player, 100)) {
                return TypedActionResult.success(itemStack);
            } else return TypedActionResult.fail(itemStack);
        } else return TypedActionResult.fail(itemStack);
    }
}

