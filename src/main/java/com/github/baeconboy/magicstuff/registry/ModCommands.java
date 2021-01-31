package com.github.baeconboy.magicstuff.registry;

import com.github.baeconboy.magicstuff.base.CommandBase;
import com.github.baeconboy.magicstuff.command.ManaCommand;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

import java.util.ArrayList;

public class ModCommands implements modRegistry {
    public static final CommandBase manaCommand = new ManaCommand();

    private static final ArrayList<CommandBase> commands = new ArrayList<>();


    @Override
    public void init() {
        this.add(manaCommand);

        this.register();
    }

    @Override
    public void add(Object item) {
        commands.add((CommandBase) item);
    }

    @Override
    public void register() {
        for (CommandBase command : commands) {
            CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> command.register(dispatcher));
        }

    }
}
