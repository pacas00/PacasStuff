package net.petercashel.PacasStuff.DIM_WOP;

import java.util.ArrayList;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class BiomeGenWOP extends BiomeGenBase {

	public static final BiomeGenBase.Height height_WOP = new BiomeGenBase.Height(0.165F, 0.125F);
	public static Random r = new Random();
	public static ArrayList<Block> blkRandom = new ArrayList<Block>();
	public static Block stoneBlock = Blocks.stone;
	public static int nextBlockID = 0;


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

		blkRandom.add(Blocks.stone);
		blkRandom.add(Blocks.glass);
		blkRandom.add(Blocks.clay);
		blkRandom.add(Blocks.stone);
		blkRandom.add(Blocks.emerald_block);
		blkRandom.add(Blocks.diamond_block);
		blkRandom.add(Blocks.gold_block);
		blkRandom.add(Blocks.stone);
		blkRandom.add(Blocks.iron_block);
		blkRandom.add(Blocks.redstone_block);
		blkRandom.add(Blocks.coal_ore);
		blkRandom.add(Blocks.quartz_ore);
		
		
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

	@Override
	public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
	{
		this.genWOPBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
	}

	public final void genWOPBiomeTerrain(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_)
	{
		boolean flag = true;
		Block block = mod_PacasStuff.Redlands_Grass;
		byte b0 = (byte)(this.field_150604_aj & 255);
		Block block1 = mod_PacasStuff.Redlands_Dirt;
		int k = -1;
		int l = (int)(p_150560_7_ / 3.0D + 3.0D + p_150560_2_.nextDouble() * 0.25D);
		int i1 = p_150560_5_ & 15;
		int j1 = p_150560_6_ & 15;
		int k1 = p_150560_3_.length / 256;
		Block stn = stoneBlock;
		boolean all = false;
		boolean top = false;
		if (r.nextInt(100) > 75) { 
			all = true;
			if (r.nextInt(7) > 4) top = true;
		}
		for (int l1 = 255; l1 >= 0; --l1)
		{
			int i2 = (j1 * 16 + i1) * k1 + l1;


			if (l1 <= 0 + p_150560_2_.nextInt(5))
			{
				p_150560_3_[i2] = Blocks.bedrock;
			}
			else
			{
				Block block2 = p_150560_3_[i2];

				if (block2 != null && block2.getMaterial() != Material.air)
				{
					if (block2 == Blocks.stone)
					{
						if (k == -1)
						{
							if (l <= 0)
							{
								block = null;
								b0 = 0;
								block1 = Blocks.stone;
							}
							else if (l1 >= 59 && l1 <= 64)
							{
								block = mod_PacasStuff.Redlands_Grass;
								b0 = (byte)(this.field_150604_aj & 255);
								block1 = mod_PacasStuff.Redlands_Dirt;
							}

							if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
							{
								if (this.getFloatTemperature(p_150560_5_, l1, p_150560_6_) < 0.15F)
								{
									block = Blocks.ice;
									b0 = 0;
								}
								else
								{
									block = Blocks.water;
									b0 = 0;
								}
							}

							k = l;

							if (l1 >= 62)
							{
								p_150560_3_[i2] = block;
								p_150560_4_[i2] = b0;
							}
							else if (l1 < 56 - l)
							{
								block = null;
								block1 = Blocks.stone;
								p_150560_3_[i2] = Blocks.gravel;
							}
							else
							{
								p_150560_3_[i2] = block1;
							}
						}
						else if (k > 0)
						{
							--k;
							p_150560_3_[i2] = block1;

							if (k == 0 && block1 == Blocks.sand)
							{
								k = p_150560_2_.nextInt(4) + Math.max(0, l1 - 63);
								block1 = Blocks.sandstone;
							}
						}
						//p_150560_3_[i2]
						if (l1 < 50 - l && l1 > 20)
						{
							if (p_150560_3_[i2] == Blocks.stone)
								p_150560_3_[i2] = stn;
						}
						if (all) {
							if (i1 < 8 && j1 < 8) {
								if (BiomeGenWOP.blkRandom.contains(p_150560_3_[i2]) || p_150560_3_[i2] == mod_PacasStuff.Redlands_Dirt)
									p_150560_3_[i2] = stn;
							}

							if (i1 > 8 && j1 > 8) {
								if (BiomeGenWOP.blkRandom.contains(p_150560_3_[i2]) || p_150560_3_[i2] == mod_PacasStuff.Redlands_Dirt) {
									p_150560_3_[i2] = stn;
								}
							}
							if (top) {
								if (i1 < 8 && j1 < 8) {
									if (p_150560_3_[i2] == mod_PacasStuff.Redlands_Grass)
										p_150560_3_[i2] = stn;
								}

								if (i1 > 8 && j1 > 8) {
									if (p_150560_3_[i2] == mod_PacasStuff.Redlands_Grass) {
										p_150560_3_[i2] = stn;
									}
								}

							}
						}
					}
				}
				else
				{
					k = -1;
				}
			}
		}
	}

	public static void nextBlock() {
		// BiomeGenWOP.stoneBlock = BiomeGenWOP.blkRandom.get(  ( BiomeGenWOP.r.nextInt(BiomeGenWOP.blkRandom.size()*9) )/10  );
        nextBlockID++;
        if (BiomeGenWOP.blkRandom.size() == nextBlockID) nextBlockID = 0;
        BiomeGenWOP.stoneBlock = BiomeGenWOP.blkRandom.get(nextBlockID);
	}
}
