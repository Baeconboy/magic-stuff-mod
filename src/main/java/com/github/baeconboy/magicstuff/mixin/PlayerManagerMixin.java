package com.github.baeconboy.magicstuff.mixin;

import com.github.baeconboy.magicstuff.callback.PlayerDisconnectCallback;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(at = @At("HEAD"), method = "remove", cancellable = true)
    private void remove(ServerPlayerEntity player, CallbackInfo info){

        ActionResult result = PlayerDisconnectCallback.EVENT.invoker().leave(player);

        if(result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
