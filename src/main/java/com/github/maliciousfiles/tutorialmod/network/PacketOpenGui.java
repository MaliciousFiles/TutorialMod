package com.github.maliciousfiles.tutorialmod.network;

import java.util.function.Supplier;

import com.github.maliciousfiles.tutorialmod.gui.SpawnerScreen;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketOpenGui {

    public PacketOpenGui(PacketBuffer buf) {
    }

    public void toBytes(PacketBuffer buf) {
    }
    
    public PacketOpenGui() {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(SpawnerScreen::open);
        ctx.get().setPacketHandled(true);
    }
}
