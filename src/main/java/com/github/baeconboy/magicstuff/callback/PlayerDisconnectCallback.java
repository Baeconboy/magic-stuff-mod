package com.github.baeconboy.magicstuff.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerDisconnectCallback {

    Event<PlayerDisconnectCallback> EVENT = EventFactory.createArrayBacked(PlayerDisconnectCallback.class,
            (listeners) -> (player) -> {
                for (PlayerDisconnectCallback listener : listeners) {
                    ActionResult result = listener.leave(player);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult leave(PlayerEntity player);
}
