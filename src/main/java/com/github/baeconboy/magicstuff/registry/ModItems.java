package com.github.baeconboy.magicstuff.registry;

import com.github.baeconboy.magicstuff.MagicStuff;
import com.github.baeconboy.magicstuff.base.BlockItemBase;
import com.github.baeconboy.magicstuff.base.ItemBase;
import com.github.baeconboy.magicstuff.item.ItemBlockTest;
import com.github.baeconboy.magicstuff.item.ItemWandBasic;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class ModItems {

    public static final ItemWandBasic itemWandBasic = new ItemWandBasic();
    public static final ItemBlockTest itemBlockTest = new ItemBlockTest();


    private static final ArrayList<Item> items = new ArrayList<>();

    public static void init() {
        ModItems.add(itemWandBasic);
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