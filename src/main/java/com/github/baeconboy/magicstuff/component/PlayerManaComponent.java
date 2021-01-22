package com.github.baeconboy.magicstuff.component;

import net.minecraft.nbt.CompoundTag;

public class PlayerManaComponent implements ManaComponent {

    public static int defaultMaxMana = 500;
    public static int defaultRegCoolDown = 20;

    private int regStreak;
    private int regCoolDown;
    private int mana;
    private int maxMana;
    private int manaPercentage;

    @Override
    public void tick() {
        //manaPercentage = (mana * 100) / maxMana;
        regenerate();
    }

    @Override
    public int getMana() {
        return this.mana;
    }

    public void setMana(int value) {
        mana = value;
    }

    @Override
    public int getMaxMana() {
        return this.maxMana;
    }

    public void setMaxMana(int value) {
        maxMana = value;
    }

    @Override
    public boolean useMana(int value) {
        if (value <= mana) {
            mana -= value;
            regCoolDown = defaultRegCoolDown;
            regStreak = 0;
            return true;
        } else
            return false;


    }

    @Override
    public void regenerate() {
        if (mana < maxMana) {
            if (regCoolDown > 0) {
                regCoolDown--;
            } else {
                mana++;
                regStreak++;
                regCoolDown = defaultRegCoolDown - Math.min(defaultRegCoolDown, regStreak);
                System.out.println(mana);
            }
        }
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.mana = tag.getInt("Mana");
        this.maxMana = tag.getInt("MaxMana");

        this.regCoolDown = tag.getInt("RegCoolDown");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if (maxMana > 0) {
            tag.putInt("Mana", mana);
            tag.putInt("MaxMana", maxMana);
        } else {
            tag.putInt("Mana", defaultMaxMana);
            tag.putInt("MaxMana", defaultMaxMana);
        }
        tag.putInt("RegCoolDown", regCoolDown);

    }

}
