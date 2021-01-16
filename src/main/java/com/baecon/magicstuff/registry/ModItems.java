package com.baecon.magicstuff.registry;

import com.baecon.magicstuff.MagicStuff;
import com.baecon.magicstuff.base.BlockItemBase;
import com.baecon.magicstuff.base.ItemBase;
import com.baecon.magicstuff.item.ItemBlockTest;
import com.baecon.magicstuff.item.ItemRuby;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class ModItems {

    public static final ItemRuby itemRuby = new ItemRuby();
    public static final ItemBlockTest itemBlockTest = new ItemBlockTest();


    private static final ArrayList<Item> items = new ArrayList<>();

    public static void init() {
        ModItems.add(itemRuby);
        ModItems.add(itemBlockTest);

        ModItems.register();
    }

    public static void add(Item item) {
        items.add(item);
    }

    public static void register() {
        for (Item item : items) {
            if (item instanceof ItemBase)
                Registry.register(Registry.ITEM, new Identifier(MagicStuff.MOD_ID, ((ItemBase) item).getItemName()), item);
            else if (item instanceof BlockItemBase)
                Registry.register(Registry.ITEM, new Identifier(MagicStuff.MOD_ID, ((BlockItemBase) item).getItemName()), item);

        }
    }
}
