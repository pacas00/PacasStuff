package net.petercashel.PacasStuff.DIM_Common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class PacasDimensionWorldGenBigTree extends WorldGenBigTree {

	private static Block trunkBlock = Blocks.log;
	private static int trunkMetadata = 1;
	private static Block leavesBlock = Blocks.leaves;
	private static int leavesMetadata = 4;

	public static void setLeavesBlock(Block block, int metadata) {
		PacasDimensionWorldGenBigTree.leavesBlock = block;
		PacasDimensionWorldGenBigTree.leavesMetadata = metadata;
	}

	public static void setTrunkBlock(Block block, int metadata) {
		PacasDimensionWorldGenBigTree.trunkBlock = block;
		PacasDimensionWorldGenBigTree.trunkMetadata = metadata;
	}

	public PacasDimensionWorldGenBigTree(boolean par1) {
		super(par1);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		Block belowB = world.getBlock(x, y - 1, z);
		int below = Block.getIdFromBlock(belowB);
		int height = rand.nextInt(8) + 24;

		if (!PacasDimensionWorldGenTreeValidSoils.isValidSoil(Integer.valueOf(below)) || y >= 256 - height - 1)
			return false;

		if (y < 1 || y + height + 1 > 256)
			return false;

		final int j = 1 + rand.nextInt(12);
		final int l = 2 + rand.nextInt(6);

		for (int i1 = y; i1 <= y + 1 + height; i1++) {
			if (i1 < 0 || i1 >= 256)
				return false;

			int k1 = 1;

			if (i1 - y < j)
				k1 = 0;
			else
				k1 = l;

			for (int x1 = x - k1; x1 <= x + k1; x1++)
				for (int z1 = z - k1; z1 <= z + k1; z1++) {
					final Block id = world.getBlock(x1, i1, z1);

					if (id != null && !Block.getBlockById(Block.getIdFromBlock(id)).isLeaves(world, x1, i1, z1))
						return false;
				}
		}

		world.setBlock(x, y - 1, z, Blocks.dirt);
		int l1 = rand.nextInt(2);
		int j2 = 1;
		boolean flag1 = false;

		for (int i3 = 0; i3 <= height - j; i3++) {
			final int k3 = y + height - i3;

			for (int i4 = x - l1; i4 <= x + l1; i4++) {
				final int k4 = i4 - x;

				for (int l4 = z - l1; l4 <= z + l1; l4++) {
					final int i5 = l4 - z;

					final Block block = world.getBlock(i4, k3, l4);

					if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0) && (block == null || block.canBeReplacedByLeaves(world, i4, k3, l4)))
						setBlockAndNotifyAdequately(world, i4, k3, l4, leavesBlock, leavesMetadata);
				}
			}

			if (l1 >= j2) {
				l1 = flag1 ? 1 : 0;
				flag1 = true;

				if (++j2 > l)
					j2 = l;
			} else
				l1++;
		}

		final int j3 = rand.nextInt(3);

		for (int l3 = 0; l3 < height - j3; l3++) {
			final Block id = world.getBlock(x, y + l3, z);

			if (id == null || Block.getBlockById(Block.getIdFromBlock(id)).isLeaves(world, x, y + l3, z))
				setBlockAndNotifyAdequately(world, x, y + l3, z, trunkBlock, trunkMetadata);
		}

		return true;
	}
}