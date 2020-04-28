package com.github.maliciousfiles.tutorialmod.dimensions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.github.maliciousfiles.tutorialmod.init.TutorialBiomes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

public class TutorialBiomeProvider extends BiomeProvider {

    private final Biome biome;
    private static final List<Biome> SPAWN = Collections.singletonList(TutorialBiomes.tutorial_biome);

    public TutorialBiomeProvider() {
        biome = TutorialBiomes.tutorial_biome;
    }

    @Override
    public Biome getBiome(int x, int y) {
        return biome;
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return SPAWN;
    }

    @Override
    public Biome[] getBiomes(int x, int y, int width, int height, boolean b) {
        Biome[] biomes = new Biome[width * height];
        Arrays.fill(biomes, biome);
        return biomes;
    }

    @Override
    public Set<Biome> getBiomesInSquare(int x, int y, int radius) {
        return Collections.singleton(biome);
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int y, int radius, List<Biome> list, Random random) {
        return new BlockPos(x, 65, y);
    }

    @Override
    public boolean hasStructure(Structure<?> structure) {
        return false;
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        if (topBlocksCache.isEmpty()) {
            topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
        }

        return topBlocksCache;
    }
}
