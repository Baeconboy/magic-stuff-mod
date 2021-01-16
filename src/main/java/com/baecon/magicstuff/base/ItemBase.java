package com.baecon.magicstuff.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    private String itemName;

    public ItemBase(Settings settings, String name) {
        super(settings);
        this.itemName = name;
    }

    public String getItemName() {
        return itemName;
    }
}
