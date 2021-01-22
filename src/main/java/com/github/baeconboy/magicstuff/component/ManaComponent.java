package com.github.baeconboy.magicstuff.component;


import dev.onyxstudios.cca.api.v3.component.Component;

public interface ManaComponent extends Component {
    void tick();

    int getMana();

    int getMaxMana();

    boolean useMana(int value);

    void regenerate();
}
