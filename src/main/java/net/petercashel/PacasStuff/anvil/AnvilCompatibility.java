package net.petercashel.PacasStuff.anvil;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraftforge.oredict.*;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class AnvilCompatibility {

	public void init() {
		
		//Vanilla Compat
		
		anvilManager.registerItem(new ItemStack(Items.diamond_sword), new ItemStack(Items.diamond));
		anvilManager.registerItem(new ItemStack(Items.diamond_pickaxe), new ItemStack(Items.diamond), 3);
		anvilManager.registerItem(new ItemStack(Items.diamond_shovel), new ItemStack(Items.diamond), 1);
		anvilManager.registerItem(new ItemStack(Items.diamond_hoe), new ItemStack(Items.diamond), 1);
		anvilManager.registerItem(new ItemStack(Items.diamond_axe), new ItemStack(Items.diamond), 3);

		anvilManager.registerItem(new ItemStack(Items.iron_sword), new ItemStack(Items.iron_ingot));
		anvilManager.registerItem(new ItemStack(Items.iron_pickaxe), new ItemStack(Items.iron_ingot), 3);
		anvilManager.registerItem(new ItemStack(Items.iron_shovel), new ItemStack(Items.iron_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.iron_hoe), new ItemStack(Items.iron_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.iron_axe), new ItemStack(Items.iron_ingot), 3);

		anvilManager.registerItem(new ItemStack(Items.golden_sword), new ItemStack(Items.gold_ingot));
		anvilManager.registerItem(new ItemStack(Items.golden_pickaxe), new ItemStack(Items.gold_ingot));
		anvilManager.registerItem(new ItemStack(Items.golden_shovel), new ItemStack(Items.gold_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.golden_hoe), new ItemStack(Items.gold_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.golden_axe), new ItemStack(Items.gold_ingot));

		anvilManager.registerItem(new ItemStack(Items.bow), new ItemStack(Items.stick));
		anvilManager.registerItem(new ItemStack(Items.fishing_rod), new ItemStack(Items.stick));
		
		
		if (Loader.isModLoaded("IC2") && mod_PacasStuff.CompatIC2) {

			try {
				//Ic2Recipes.addMaceratorRecipe(new ItemStack(pacas00.pacasOre.mod_PacasOreMain.oreMetaBlock, 1, 7), new ItemStack(getIC2Item("ironDust"), 4));
				//ic2.api.recipe.IMachineRecipeManager.addRecipe(new ItemStack(pacas00.pacasOre.mod_PacasOreMain.oreMetaBlock, 1, 7), new ItemStack(getIC2Item("ironDust"), 4));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemToolBronzeSword", 0, "IC2", "itemIngot", 2, 2));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemToolBronzeHoe", 0, "IC2", "itemIngot", 2, 2));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemToolBronzeSpade", 0, "IC2", "itemIngot", 2, 1));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemToolBronzeAxe", 0, "IC2", "itemIngot", 2, 3));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemToolBronzePickaxe", 0, "IC2", "itemIngot", 2, 3));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemArmorBronzeBoots", 0, "IC2", "itemIngot", 2, 4));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemArmorBronzeHelmet", 0, "IC2", "itemIngot", 2, 5));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemArmorBronzeLegs", 0, "IC2", "itemIngot", 2, 7));
				anvilManager.CompatLoad(new Anvil_ItemData("IC2", "itemArmorBronzeChestplate", 0, "IC2", "itemIngot", 2, 8));

				System.out.println("[PacasOre] IC2 Compatibility Loaded.");			

			} catch (Exception e) {
				System.out.println("[PacasOre]: IC2 integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed."); 
			}
		} else { System.out.println("IC2 Not Loaded"); }
		
		if (Loader.isModLoaded("ThermalExpansion") && mod_PacasStuff.CompatTE4) {

			try {
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarSword", 0, "Minecraft", "item.thermalexpansion.material", 71, 2));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarHoe", 0, "Minecraft", "item.thermalexpansion.material", 71, 2));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarShovel", 0, "Minecraft", "item.thermalexpansion.material", 71, 1));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarAxe", 0, "Minecraft", "item.thermalexpansion.material", 71, 3));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarPickaxe", 0, "Minecraft", "item.thermalexpansion.material", 71, 3));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.armor.invarBoots", 0, "Minecraft", "item.thermalexpansion.material", 71, 4));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.armor.invarHelmet", 0, "Minecraft", "item.thermalexpansion.material", 71, 5));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.armor.invarLegs", 0, "Minecraft", "item.thermalexpansion.material", 71, 7));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.armor.invarPlate", 0, "Minecraft", "item.thermalexpansion.material", 71, 8));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarShears", 0, "Minecraft", "item.thermalexpansion.material", 71, 1));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarFishingRod", 0, "Minecraft", "item.thermalexpansion.material", 71, 2));
				anvilManager.CompatLoad(new Anvil_ItemData("Minecraft", "item.thermalexpansion.tool.invarSickle", 0, "Minecraft", "item.thermalexpansion.material", 71, 3));

				System.out.println("[PacasOre] ThermalExpansio4 Compatibility Loaded.");			

			} catch (Exception e) {
				System.out.println("[PacasOre]: ThermalExpansion4 integration was unsuccessful - please contact the author of this mod to let them know that the API may have changed."); 
			}
		} else { System.out.println("ThermalExpansion4 Not Loaded"); }
		
	}
}