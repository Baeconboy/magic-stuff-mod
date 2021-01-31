package com.github.baeconboy.magicstuff.command;

import com.github.baeconboy.magicstuff.Components;
import com.github.baeconboy.magicstuff.base.CommandBase;
import com.github.baeconboy.magicstuff.component.ManaComponent;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

import java.util.Collection;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ManaCommand extends CommandBase {

    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("mana")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .then(literal("get")
                        .then(argument("target", EntityArgumentType.player())
                                .executes(context -> executeGetMana(context.getSource(), EntityArgumentType.getPlayer(context, "target")))))
                .then(literal("getMax")
                        .then(argument("target", EntityArgumentType.player())
                                .executes(context -> executeGetMaxMana(context.getSource(), EntityArgumentType.getPlayer(context, "target")))))
                .then(literal("set")
                        .then(argument("targets", EntityArgumentType.players())
                                .then(argument("value", IntegerArgumentType.integer(0))
                                        .executes(context -> executeSetMana(context.getSource(), EntityArgumentType.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "value"))))
                                .then(argument("infinite", BoolArgumentType.bool())
                                        .executes(context -> executeSetManaInfinite(context.getSource(), EntityArgumentType.getPlayers(context, "targets"), BoolArgumentType.getBool(context, "infinite"))))))
                .then(literal("setMax")
                        .then(argument("targets", EntityArgumentType.players())
                                .then(argument("value", IntegerArgumentType.integer(1))
                                        .executes(context -> executeSetMaxMana(context.getSource(), EntityArgumentType.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "value")))))));
    }

    public int executeSetMana(ServerCommandSource context, Collection<? extends Entity> targets, int value) {

        String feedback;
        Entity player = null;

        for (Entity entity : targets) {
            ManaComponent component = Components.MANA.get(entity);
            component.setMana(value);
            player = entity;
        }
        if (targets.size() == 1) {
            context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.setmana.single", player.getDisplayName(), value), true);
        } else {
            context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.setmana.multiple", value), true);
        }
        return value;
    }

    public int executeSetManaInfinite(ServerCommandSource context, Collection<? extends Entity> targets, boolean infinite) {

        String feedback;
        Entity player = null;

        for (Entity entity : targets) {
            ManaComponent component = Components.MANA.get(entity);
            component.setInfinite(infinite);
            player = entity;
        }
        if (targets.size() == 1) {
            context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.setmana.single", player.getDisplayName(), infinite ? "infinite" : "finite"), true);
        } else {
            context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.setmana.multiple", infinite ? "infinite" : "finite"), true);
        }
        return 1;
    }

    public int executeSetMaxMana(ServerCommandSource context, Collection<? extends Entity> targets, int value) {

        String feedback;
        Entity player = null;

        for (Entity entity : targets) {
            ManaComponent component = Components.MANA.get(entity);
            component.setMaxMana(value);
            player = entity;
        }
        if (targets.size() == 1) {
            context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.setmaxmana.single", player.getDisplayName(), value), true);
        } else {
            context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.setmaxmana.multiple", value), true);
        }
        return value;
    }

    public int executeGetMana(ServerCommandSource context, ServerPlayerEntity target) {
        int value = Components.MANA.get(target).getMana();
        context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.getmana", target.getDisplayName(), value), true);
        return value;
    }

    public int executeGetMaxMana(ServerCommandSource context, ServerPlayerEntity target) {
        int value = Components.MANA.get(target).getMaxMana();
        context.sendFeedback(new TranslatableText("commands.magic_stuff.mana.getmaxmana", target.getDisplayName(), value), true);
        return value;
    }
}
