package com.github.baeconboy.magicstuff.mixin;

import com.github.baeconboy.magicstuff.Components;
import com.github.baeconboy.magicstuff.component.ManaComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract void sendMessage(Text message, boolean actionBar);

    @Shadow public abstract String getEntityName();

    private ManaComponent comp = Components.MANA.get(this);


    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        if(this.getEntityWorld().isClient())
        this.sendMessage(new TranslatableText("player.shitmate.cancer", comp.getMana(), comp.getMaxMana()), true);
        comp.tick();
    }

    @Environment(EnvType.CLIENT)
    @Inject(at = @At("HEAD"), method = "afterSpawn")
    private void afterSpawn(CallbackInfo ci) {
        comp.init((PlayerEntity) (Object) this);
    }

}
