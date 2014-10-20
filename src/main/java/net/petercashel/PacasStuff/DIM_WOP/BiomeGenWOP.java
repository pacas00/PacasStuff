package net.petercashel.PacasStuff.DIM_WOP;

import java.util.Random;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fluids.FluidRegistry;

public class BiomeGenWOP extends BiomeGenBase {
	
	public static final BiomeGenBase.Height height_WOP = new BiomeGenBase.Height(0.165F, 0.125F);

	public BiomeGenWOP(int i) {
		super(i);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
        
        this.spawnableWaterCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySquid.class, 10, 4, 4));
        
		this.topBlock = (Blocks.grass);
		this.fillerBlock = (Blocks.dirt);
		
		this.enableSnow = false;
		this.enableRain = true;
		
		
		this.theBiomeDecorator = this.createBiomeDecorator();
		
		///** this changes the water colour **/
		//this.waterColorMultiplier = 0xE42E57;
		
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Provides the basic grass colour based on a hex colour 
	 */
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
		return 0x66AD2D;
	}
	
	/**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
    {
    	//0xA6FF63
    	return 0x85E53B;
    }
    
    /**
	 * Allocate a custom BiomeDecorator for the WOP Biome
	 */
	public BiomeDecorator createBiomeDecorator() {
		return new WOPBiomeDecorator();
	}
	
	@Override  //getRandomWorldGenForTrees
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
	}
}
