package net.petercashel.PacasStuff.DIM_Redlands;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class GenLayerBiomesRedlands extends GenLayer {

	protected static BiomeGenBase[] allowedBiomesReal = {mod_PacasStuff.Redlands, mod_PacasStuff.RedlandsM, BiomeGenBase.ocean, mod_PacasStuff.Redlands_Short};
	protected static BiomeGenBase[] allowedBiomes = allowedBiomesReal;
	

	public GenLayerBiomesRedlands(long seed, GenLayer genlayer) {
		super(seed);
		this.parent = genlayer;
	}

	public GenLayerBiomesRedlands(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width*depth);

		for (int dz=0; dz<depth; dz++)
		{
			for (int dx=0; dx<width; dx++)
			{
				this.initChunkSeed(dx+x, dz+z);
				dest[(dx+dz*width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
	
	   /**
     * return the biome specified by biomeID, or 0 (ocean) if out of bounds
     */
    public static BiomeGenBase getBiome(int p_150568_0_)
    {
        if (p_150568_0_ >= 0 && p_150568_0_ <= allowedBiomes.length)
        {
            return allowedBiomes[p_150568_0_];
        }
        else
        {
            return BiomeGenBase.getBiome(p_150568_0_);
        }
    }

}
