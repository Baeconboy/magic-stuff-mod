package com.github.baeconboy.magicstuff;

import com.github.baeconboy.magicstuff.callback.PlayerDisconnectCallback;
import com.github.baeconboy.magicstuff.registry.ModBlocks;
import com.github.baeconboy.magicstuff.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;

public class MagicStuff implements ModInitializer {

    public static final String MOD_ID = "magic_stuff";

    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModItems.init();
    }
}
