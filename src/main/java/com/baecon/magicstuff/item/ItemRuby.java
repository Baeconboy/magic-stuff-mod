package com.baecon.magicstuff.item;

import com.baecon.magicstuff.base.ItemBase;
import net.minecraft.item.ItemGroup;

public class ItemRuby extends ItemBase {

    public static String itemName = "ruby";
    public static Settings settings = new Settings()
            .group(ItemGroup.COMBAT)
            .maxCount(16);

    public ItemRuby() {
        super(ItemRuby.settings, itemName);
    }
}
