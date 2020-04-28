package com.github.maliciousfiles.tutorialmod.commands;

import com.github.maliciousfiles.tutorialmod.init.TutorialDimensions;
import com.github.maliciousfiles.tutorialmod.util.TeleportationTools;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class CommandTpDim implements Command<CommandSource> {

    private static final CommandTpDim CMD = new CommandTpDim();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("dim")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        if (player.dimension.equals(TutorialDimensions.tutorial_dimension_type)) {
            TeleportationTools.teleport(player, DimensionType.OVERWORLD, new BlockPos(player.posX, 200, player.posZ));
        } else {
            TeleportationTools.teleport(player, TutorialDimensions.tutorial_dimension_type, new BlockPos(player.posX, 200, player.posZ));
        }
        return 0;
    }
}
