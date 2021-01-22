package com.github.baeconboy.magicstuff.base;

import net.minecraft.item.Item;

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