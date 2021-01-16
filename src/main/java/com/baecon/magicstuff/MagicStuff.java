package com.baecon.magicstuff;

import com.baecon.magicstuff.registry.ModBlocks;
import com.baecon.magicstuff.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class MagicStuff implements ModInitializer {

    public static final String MOD_ID = "magic_stuff";


    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModItems.init();

    }
}
