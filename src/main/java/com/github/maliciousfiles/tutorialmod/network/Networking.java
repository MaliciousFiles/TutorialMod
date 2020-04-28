package com.github.maliciousfiles.tutorialmod.network;

import com.github.maliciousfiles.tutorialmod.TutorialMod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {

    public static SimpleChannel instance;
    private static int id = 0;

    public static int nextID() {
        return id++;
    }

    public static void registerMessages() {
        instance = NetworkRegistry.newSimpleChannel(new ResourceLocation(TutorialMod.MODID, "tutorialmod"), () -> "1.0", s -> true, s -> true);

        instance.registerMessage(nextID(),
                PacketOpenGui.class,
                PacketOpenGui::toBytes,
                PacketOpenGui::new,
                PacketOpenGui::handle);
        instance.registerMessage(nextID(),
                PacketSpawn.class,
                PacketSpawn::toBytes,
                PacketSpawn::new,
                PacketSpawn::handle);
    }
}