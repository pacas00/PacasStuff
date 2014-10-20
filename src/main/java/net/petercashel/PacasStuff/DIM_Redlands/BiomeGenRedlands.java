package net.petercashel.PacasStuff.DIM_Redlands;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
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

	public static final BiomeGenBase.Height height_Redlands = new BiomeGenBase.Height(5.25F, 1.75F);
	public static final BiomeGenBase.Height height_Redlands_Short = new BiomeGenBase.Height(1.25F, 0.2F);
	protected WorldGenTrees worldGeneratorTrees;

	/** The big tree generator. */
	protected WorldGenBigTree worldGeneratorBigTree;

	public BiomeGenRedlands(int i) {
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
		this.topBlock = (Blocks.grass);
		this.fillerBlock = (Blocks.dirt);
		this.worldGeneratorTrees = new PacasDimensionWorldGenTree(false);
		this.worldGeneratorBigTree = new PacasDimensionWorldGenBigTree(false);
		this.theBiomeDecorator = this.createBiomeDecorator();
		this.theBiomeDecorator.treesPerChunk = 5;

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

	@Override  //getRandomWorldGenForTrees
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
	}
}
