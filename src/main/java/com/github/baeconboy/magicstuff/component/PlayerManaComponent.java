package com.github.baeconboy.magicstuff.component;

import com.github.baeconboy.magicstuff.Components;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerManaComponent implements ManaComponent, AutoSyncedComponent {

    public static final int defaultMaxMana = 100;
    public static final int defaultRegCoolDown = 20;
    private static final int appleIncrease = 5;


    private static final float regMultiplier = 0.5F;


    private enum updateEnum {
        NONE,
        MANA,
        MAX_MANA,
        REGCOOLDOWN
    }

    private final Object provider;

    private int regCoolDown;
    private int appleAmount;
    private int tempMana = defaultMaxMana;
    private int mana;
    private int maxMana;
    private int regBonus;


    private boolean infiniteMana = false;

    public PlayerManaComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public void tick() {
        maxMana = tempMana + (appleAmount * appleIncrease);

        regenerate();

    }

    @Override
    public void init(PlayerEntity player) {

    }

    @Environment(EnvType.SERVER)
    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.provider;
    }

    @Override
    public boolean useMana(int value) {
        if (!infiniteMana) {
            if (value <= mana) {
                mana -= value;
                regCoolDown = defaultRegCoolDown;
                Components.MANA.sync(this.provider);

                return true;
            } else
                return false;
        } else return true;
    }

    public void usedManaApple() {
        this.appleAmount++;
        this.regCoolDown = defaultRegCoolDown;
        Components.MANA.sync(this.provider);
    }

    @Override
    public void regenerate() {
        if (mana < maxMana) {
            if (regCoolDown > 0) {
                regCoolDown--;
            } else {
                //courtesy of Terraria mana regeneration
                int value = (int) ((maxMana / 7 + 1 + regBonus) * (mana / maxMana * 0.8 + 0.2) * regMultiplier);
                value = Math.max(1, value);
                mana += value;
                mana = Math.min(mana, maxMana);
                Components.MANA.sync(this.provider);

            }
        }
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.infiniteMana = tag.getBoolean("Infinite");
        this.regCoolDown = tag.getInt("CoolDown");
        this.appleAmount = tag.getInt("Apples");
        this.mana = tag.getInt("Mana");
        this.tempMana = tag.getInt("MaxMana");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putBoolean("Infinite", infiniteMana);
        tag.putInt("CoolDown", regCoolDown);
        tag.putInt("Apples", appleAmount);
        tag.putInt("Mana", mana);
        tag.putInt("MaxMana", tempMana);


    }

    //Getters
    @Override
    public int getMana() {
        return this.mana;
    }

    public boolean getInfinite() {
        return infiniteMana;
    }


    @Override
    public int getMaxMana() {
        return this.maxMana;
    }

    @Override
    public int getRegBonus() {
        return this.regBonus;
    }

    //SETTERS
    @Override
    public void setMana(int value) {
        mana = value;
        Components.MANA.sync(this.provider);
    }

    public void setInfinite(boolean value) {
        this.infiniteMana = value;
        Components.MANA.sync(this.provider);
    }

    @Override
    public void setMaxMana(int value) {
        tempMana = value;
        Components.MANA.sync(this.provider);

    }

    @Override
    public void setRegBonus(int value) {
        regBonus = value;
    }


}
