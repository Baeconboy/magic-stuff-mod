package com.github.baeconboy.magicstuff.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerManaComponent implements ManaComponent, AutoSyncedComponent {

    public static final int defaultMaxMana = 100;
    public static final int defaultRegCoolDown = 20;
    private static final float regMultiplier = 0.5F;

    private enum updateEnum {
        NONE,
        MANA,
        MAX_MANA,
        REGCOOLDOWN
    }

    private final Object provider;

    private int regCoolDown;
    private int mana;
    private int maxMana;
    private int regBonus;

    private boolean infiniteMana = false;
    private boolean manaDirty = true;
    private boolean maxManaDirty = true;
    private boolean regCoolDownDirty = true;

    public PlayerManaComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public void tick() {
        regenerate();


    }

    @Override
    public void init(PlayerEntity player) {

    }


    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        this.writeSyncPacket(buf, recipient, updateEnum.NONE);
    }

    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient, updateEnum update) {
        if (update == updateEnum.MANA)
            buf.writeVarInt(this.mana);
        if (update == updateEnum.MAX_MANA)
            buf.writeVarInt(this.maxMana);
        if (update == updateEnum.REGCOOLDOWN)
            buf.writeVarInt(this.regCoolDown);
        buf.writeEnumConstant(update);
    }


    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        updateEnum update = buf.readEnumConstant(updateEnum.MANA.getDeclaringClass());
    }

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
                return true;
            } else
                return false;
        } else return true;
    }

    @Override
    public void regenerate() {
        if (mana < maxMana) {
            if (regCoolDown > 0) {
                regCoolDown--;
            } else {
                //courtesy of Terraria mana regeneration
                mana += Math.min(((int) ((maxMana / 7 + 1 + regBonus) * (mana / maxMana * 0.8 + 0.2) * regMultiplier)), maxMana);
            }
        }
    }

    public void regenerate(Float value) {

    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.mana = tag.getInt("Mana");
        this.maxMana = tag.getInt("MaxMana");
        this.regCoolDown = tag.getInt("RegCoolDown");
        this.infiniteMana = tag.getBoolean("Infinite");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if (maxMana > 0) {
            tag.putInt("Mana", mana);
            tag.putInt("MaxMana", maxMana);
        } else {
            tag.putInt("MaxMana", defaultMaxMana);
            tag.putInt("Mana", 0);
        }
        tag.putInt("RegCoolDown", regCoolDown);
        tag.putBoolean("Infinite", infiniteMana);

    }

    //Getters
    @Override
    public int getMana() {
        return this.mana;
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
    }

    @Override
    public void setMaxMana(int value) {
        maxMana = value;
    }

    @Override
    public void setRegBonus(int value) {
        regBonus = value;
    }


}
