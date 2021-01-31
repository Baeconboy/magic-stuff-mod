package com.github.baeconboy.magicstuff.registry;

import com.github.baeconboy.magicstuff.MagicStuff;
import com.github.baeconboy.magicstuff.base.BlockBase;
import com.github.baeconboy.magicstuff.block.BlockTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class ModBlocks implements modRegistry {

    public static final BlockTest blockTest = new BlockTest();

    private static final ArrayList<BlockBase> blocks = new ArrayList<>();

    public ModBlocks() {
    }

    @Override
    public void init() {
        this.add(blockTest);

        this.register();

    }

    public void add(Object block) {
        blocks.add((BlockBase) block);
    }

    public void register() {
        for (BlockBase block : blocks) {
            Registry.register(Registry.BLOCK, new Identifier(MagicStuff.MOD_ID, block.getBlockName()), block);
        }
    }
}