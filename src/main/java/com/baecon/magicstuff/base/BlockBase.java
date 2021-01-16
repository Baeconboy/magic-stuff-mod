package com.baecon.magicstuff.base;

import net.minecraft.block.Block;


public class BlockBase extends Block {
    private String blockName;

    public BlockBase(Settings settings, String name) {
        super(settings);
        this.blockName = name;
    }

    public String getBlockName() {
        return blockName;
    }

}
