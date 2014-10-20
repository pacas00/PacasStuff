package net.petercashel.PacasStuff.DIM_WOP;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerWOP extends GenLayer {
	
	public GenLayerWOP(long seed) {
		super(seed);
	}

	public static GenLayer[] makeTheWorld(long seed, WorldType worldtype) {

		GenLayer biomes = new GenLayerBiomesWOP(1L);
		GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(4L, biomes);
		GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayerfuzzyzoom);

        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerdeepocean);
        GenLayerFuzzyZoom genlayerfuzzyzoom2 = new GenLayerFuzzyZoom(4L, genlayersmooth);
        GenLayerSmooth genlayersmooth2 = new GenLayerSmooth(1000L, genlayerfuzzyzoom2);
        
        
		// more GenLayerZoom = bigger biomes
		biomes = new GenLayerZoom(1000L, genlayersmooth2);
		biomes = new GenLayerZoom(1001L, biomes);
		biomes = new GenLayerZoom(1002L, biomes);
		biomes = new GenLayerZoom(1003L, biomes);
		biomes = new GenLayerZoom(1004L, biomes);
		biomes = new GenLayerZoom(1005L, biomes);

		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, genlayervoronoizoom};
	}

}
