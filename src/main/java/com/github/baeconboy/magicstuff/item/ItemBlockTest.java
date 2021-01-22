package com.github.baeconboy.magicstuff.item;

import com.github.baeconboy.magicstuff.base.BlockItemBase;
import com.github.baeconboy.magicstuff.registry.ModBlocks;
import net.minecraft.item.ItemGroup;

public class ItemBlockTest extends BlockItemBase {

    public static Settings settings = new Settings()
            .group(ItemGroup.COMBAT)
            .maxCount(16);

    public ItemBlockTest() {
        super(ModBlocks.blockTest, settings, ModBlocks.blockTest.getBlockName());

    }
}