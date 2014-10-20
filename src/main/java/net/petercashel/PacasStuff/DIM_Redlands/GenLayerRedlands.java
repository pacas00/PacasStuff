package net.petercashel.PacasStuff.DIM_Redlands;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerRedlands extends GenLayer {
	
	public GenLayerRedlands(long seed) {
		super(seed);
	}

	public static GenLayer[] makeTheWorld(long seed, WorldType worldtype) {

		GenLayer biomes = new GenLayerBiomesRedlands(1L);
		GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(4L, biomes);
		GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayerfuzzyzoom);
		
		GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayerremovetoomuchocean);

        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerdeepocean);

		// more GenLayerZoom = bigger biomes
		biomes = new GenLayerZoom(1000L, biomes);
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
