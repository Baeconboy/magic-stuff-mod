package com.github.baeconboy.magicstuff.mana;

import com.github.baeconboy.magicstuff.Component;
import com.github.baeconboy.magicstuff.component.ManaComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;

public class Mana {

    private static final ComponentKey<ManaComponent> mana = Component.MANA;

    public static boolean use(PlayerEntity player, int value) {
        ManaComponent comp = mana.get(player);
        player.sendMessage(new TranslatableText("player.shitmate.cancer", comp.getMaxMana(), comp.getMana()), false);
        return comp.useMana(value);
    }
}
