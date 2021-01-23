package com.github.baeconboy.magicstuff.component;


import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.entity.player.PlayerEntity;

public interface ManaComponent extends Component {
    void tick();

    /**
     * @param player Player to be initialized
     */
    void init(PlayerEntity player);

    /**
     * @param value Amount of mana to be used
     * @return true if mana has been used successfully, false otherwise
     */
    boolean useMana(int value);

    void regenerate();

    int getMaxMana();

    int getMana();

    int getRegBonus();

    void setMana(int value);

    void setMaxMana(int value);

    void setRegBonus(int value);
}