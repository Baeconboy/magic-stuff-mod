package com.baecon.magicstuff.item;

import com.baecon.magicstuff.MagicStuff;
import com.baecon.magicstuff.base.BlockItemBase;
import com.baecon.magicstuff.registry.ModBlocks;
import net.minecraft.item.ItemGroup;

public class ItemBlockTest extends BlockItemBase {

    public static Settings settings = new Settings()
            .group(ItemGroup.COMBAT)
            .maxCount(16);

    public ItemBlockTest() {
        super(ModBlocks.blockTest, settings, ModBlocks.blockTest.getBlockName());

    }
}
