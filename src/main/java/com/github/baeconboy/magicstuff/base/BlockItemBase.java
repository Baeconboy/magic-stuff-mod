package com.github.baeconboy.magicstuff.base;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockItemBase extends BlockItem {

    private String itemName;

    public BlockItemBase(Block block, Settings settings, String name) {
        super(block, settings);
        this.itemName = name;
    }

    public String getItemName() {
        return itemName;
    }

}