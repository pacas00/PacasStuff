package net.petercashel.PacasStuff.DIM_Redlands;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.petercashel.PacasStuff.DIM_Common.PacasDimensionWorldGenBigTree;
import net.petercashel.PacasStuff.DIM_Common.PacasDimensionWorldGenTree;

public class BiomeGenRedlands extends BiomeGenBase {

	public final Material blockMaterial;
	public static final BiomeGenBase.Height height_Redlands = new BiomeGenBase.Height(0.2F, 0.2F);
	protected WorldGenTrees worldGeneratorTrees;

	/** The big tree generator. */
	protected WorldGenBigTree worldGeneratorBigTree;

	public BiomeGenRedlands(int i) {
		super(i);
		this.blockMaterial = Material.water;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = (Blocks.grass);
		this.fillerBlock = (Blocks.dirt);
		this.worldGeneratorTrees = new PacasDimensionWorldGenTree(false);
		this.worldGeneratorBigTree = new PacasDimensionWorldGenBigTree(false);
		this.theBiomeDecorator = this.createBiomeDecorator();
		this.theBiomeDecorator.treesPerChunk = 8;

		///** this changes the water colour **/
		//waterColorMultiplier = 0xFF6000;
		//this.waterColorMultiplier = 0xFF6000;
		//this.color = 0xAA1900;

	}

	/**
	 * Allocate a custom BiomeDecorator for the Redlands Biome
	 */
	public BiomeDecorator createBiomeDecorator() {
		return new RedLandsBiomeDecorator();
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
	{
		return 0xAA1900;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
	{
		return 0x990000;
	}
	
	@Override  //getRandomWorldGenForTrees
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
	}
}
