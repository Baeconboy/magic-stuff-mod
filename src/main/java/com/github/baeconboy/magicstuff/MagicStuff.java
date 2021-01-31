package com.github.baeconboy.magicstuff;

import com.github.baeconboy.magicstuff.registry.ModBlocks;
import com.github.baeconboy.magicstuff.registry.ModCommands;
import com.github.baeconboy.magicstuff.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class MagicStuff implements ModInitializer {

    public static final String MOD_ID = "magic_stuff";

    public static final ModBlocks modBlocks = new ModBlocks();
    public static final ModItems modItems = new ModItems();
    public static final ModCommands modCommands = new ModCommands();

    @Override
    public void onInitialize() {
        modBlocks.init();
        modItems.init();
        modCommands.init();
    }
}
