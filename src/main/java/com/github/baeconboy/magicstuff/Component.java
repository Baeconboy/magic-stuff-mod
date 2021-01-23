package com.github.baeconboy.magicstuff;

import com.github.baeconboy.magicstuff.component.ManaComponent;
import com.github.baeconboy.magicstuff.component.PlayerManaComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public class Component implements EntityComponentInitializer {
    public static final ComponentKey<ManaComponent> MANA =
            ComponentRegistry.getOrCreate(new Identifier(MagicStuff.MOD_ID, "mana"), ManaComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MANA, PlayerManaComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
