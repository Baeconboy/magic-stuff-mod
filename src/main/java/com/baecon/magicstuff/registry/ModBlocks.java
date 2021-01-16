package com.baecon.magicstuff.registry;

import com.baecon.magicstuff.MagicStuff;
import com.baecon.magicstuff.base.BlockBase;
import com.baecon.magicstuff.block.BlockTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class ModBlocks {

    public static final BlockTest blockTest = new BlockTest();

    private static final ArrayList<BlockBase> blocks = new ArrayList<>();

    public static void init() {
        ModBlocks.add(blockTest);

        ModBlocks.register();
    }

    public static void add(BlockBase block) {
        blocks.add(block);
    }

    public static void register() {
        for (BlockBase block : blocks) {
            Registry.register(Registry.BLOCK, new Identifier(MagicStuff.MOD_ID, block.getBlockName()), block);
        }
    }
}
