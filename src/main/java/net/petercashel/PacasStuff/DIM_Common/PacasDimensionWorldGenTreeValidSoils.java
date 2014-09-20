package net.petercashel.PacasStuff.DIM_Common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;



public enum PacasDimensionWorldGenTreeValidSoils {
	INSTANCE;

	private static final List<Block> validSoil;

	static {
		validSoil = new ArrayList<Block>();
		addValidSoil(Blocks.grass);
		addValidSoil(Blocks.dirt);
		addValidSoil(Blocks.farmland);
	}

	public static void addValidSoil(Block soilBlock) {
		validSoil.add(soilBlock);
	}

	public static boolean isValidSoil(Block soil) {
		return validSoil.contains(soil);
	}

	public static boolean isValidSoil(int blockID) {
		return isValidSoil(Block.getBlockById(blockID));
	}
}