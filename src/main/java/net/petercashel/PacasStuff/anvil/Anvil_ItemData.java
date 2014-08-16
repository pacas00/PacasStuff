package net.petercashel.PacasStuff.anvil;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Anvil_ItemData is used house data for saving and loading items registered in the anvil.
 * 
 * It is heavily based of Asie's EnderID class (because it's simply genius)
 */
public class Anvil_ItemData {

	public String modId;
	public String name;
	public int metadata;

	public String RepairMat_modId;
	public String RepairMat_name;
	public int RepairMat_metadata, RepairMat_Divider;

	public class DataConversionException extends Exception {
		public DataConversionException(String modId, String name, String info, String DisplayName, String type) {
			super("Error converting " + DisplayName + " to Anvil_ItemData:" + type + ": " + info + "(mod "+modId+", name "+name+")");
		}
	}

	public Anvil_ItemData(ItemStack item, ItemStack repair, int divider) throws DataConversionException {
		if(!(item instanceof ItemStack)) throw new DataConversionException("", "unknown", "Custom item stacks unsupported!", item.getDisplayName(), "Item");
		if(!(repair instanceof ItemStack)) throw new DataConversionException("", "unknown", "Custom item stacks unsupported!", repair.getDisplayName(), "Repair");
		UniqueIdentifier itemID = GameRegistry.findUniqueIdentifierFor(item.getItem());
		if(itemID == null) {
			this.modId = "Minecraft";
			this.name = item.getItem().getUnlocalizedName();
		} else {
			this.modId = itemID.modId;
			this.name = itemID.name;
		}
		this.metadata = item.getItemDamage();

		UniqueIdentifier itemID2 = GameRegistry.findUniqueIdentifierFor(repair.getItem());
		if(itemID2 == null) {
			this.RepairMat_modId = "Minecraft";
			this.RepairMat_name = repair.getItem().getUnlocalizedName();
		} else {
			this.RepairMat_modId = itemID2.modId;
			this.RepairMat_name = itemID2.name;
		}
		this.RepairMat_metadata = repair.getItemDamage();
		this.RepairMat_Divider = divider;

	}

	public Anvil_ItemData(String item_modId, String item_name, int item_metadata, String RepairMat_modId, String RepairMat_name, int RepairMat_metadata, int RepairMat_Divider) {
		this.modId = item_modId;
		this.name = item_name;
		this.metadata = item_metadata;
		this.RepairMat_modId = RepairMat_modId;
		this.RepairMat_name = RepairMat_name;
		this.RepairMat_metadata = RepairMat_metadata;
		this.RepairMat_Divider = RepairMat_Divider;
	}

	public ItemStack getItem() {
		ItemStack stack = null;
		if(this.modId.equals("Minecraft")) {
			for(Object o : GameData.getItemRegistry()) {
				if(o instanceof Item) {
					Item i = (Item) o;
					if(i == null) continue;
					if(i.getUnlocalizedName().equals(this.name)) {
						stack = new ItemStack(i, 1);
					}
				}
			}
			if(stack == null) return null;
		} else stack = GameRegistry.findItemStack(modId, name, 1);
		return stack;
	}

	public ItemStack getRepairItem() {
		ItemStack stack = null;
		if(this.modId.equals("Minecraft")) {
			for(Object o : GameData.getItemRegistry()) {
				if(o instanceof Item) {
					Item i = (Item) o;
					if(i == null) continue;
					if(i.getUnlocalizedName().equals(this.RepairMat_name)) {
						stack = new ItemStack(i, 1, this.RepairMat_metadata);
					}
				}
			}
			if(stack == null) return null;
		} else {
			stack = GameRegistry.findItemStack(this.RepairMat_modId, this.RepairMat_name, 1);
			stack.setItemDamage(this.RepairMat_metadata);
		}
		return stack;
	}

	public int getRepairDivider() { return this.RepairMat_Divider; }
}
