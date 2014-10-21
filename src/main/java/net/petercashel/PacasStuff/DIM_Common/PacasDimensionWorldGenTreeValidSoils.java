package net.petercashel.PacasStuff.DIM_Common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.petercashel.PacasStuff.mod_PacasStuff;



public enum PacasDimensionWorldGenTreeValidSoils {
	INSTANCE;

	private static final List<Block> validSoil;

	static {
		validSoil = new ArrayList<Block>();
		addValidSoil(Blocks.grass);
		addValidSoil(Blocks.dirt);
		addValidSoil(Blocks.farmland);
		addValidSoil(mod_PacasStuff.Redlands_Dirt);
		addValidSoil(mod_PacasStuff.Redlands_Grass);
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