package com.github.maliciousfiles.tutorialmod.init;

import com.github.maliciousfiles.tutorialmod.TutorialMod;
import com.github.maliciousfiles.tutorialmod.commands.CommandSpawner;
import com.github.maliciousfiles.tutorialmod.commands.CommandTest;
import com.github.maliciousfiles.tutorialmod.commands.CommandTpDim;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class TutorialCommands {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(
                Commands.literal(TutorialMod.MODID)
                        .then(CommandTest.register(dispatcher))
                        .then(CommandSpawner.register(dispatcher))
                        .then(CommandTpDim.register(dispatcher))
        );

        dispatcher.register(Commands.literal("tut").redirect(cmdTut));
	}
}
