package com.github.baeconboy.magicstuff.block;

import com.github.baeconboy.magicstuff.base.BlockBase;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;

public class BlockTest extends BlockBase {

    public static String blockName = "test";
    public static FabricBlockSettings settings = FabricBlockSettings
            .of(Material.GLASS)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .luminance(50)
            .strength(5.0F, 30.0F);

    public BlockTest() {
        super(BlockTest.settings, blockName);
    }


}