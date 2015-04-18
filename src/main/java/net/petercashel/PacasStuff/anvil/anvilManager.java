package net.petercashel.PacasStuff.anvil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.*;
import net.petercashel.PacasStuff.mod_PacasStuff;

/**
 * anvilManager handles items and materials for PacasAnvilManaged.
 * 
 */
public class anvilManager {

	protected static List<Anvil_ItemData> anvilList = new ArrayList<Anvil_ItemData>();

	//Anvil_ItemData

	/**
	 * For adding an item to the anvilManager.
	 * params: Item to repair, Item of repair Material, repair divider
	 */
	public static void registerItem(ItemStack par1Item, ItemStack par2Item, int par3) {
		boolean isInManager = isInAnvilManager(par1Item);
		if (isInManager == false) {

			Anvil_ItemData data = null;
			try {
				data = new Anvil_ItemData(par1Item, par2Item, par3);
			} catch (net.petercashel.PacasStuff.anvil.Anvil_ItemData.DataConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (data != null) {
				anvilList.add(data);
			}
		}
	}


	public static void registerItem(ItemStack par1Item, ItemStack par2Item) {
		registerItem(par1Item, par2Item, 2);
	}

	/**
	 * For adding an item to the anvilManager.
	 * params: Item to repair, Block of repair Material, repair divider
	 */
	public static void registerItem(ItemStack par1Item, Block par2Block, int par3) {
		boolean isInManager = isInAnvilManager(par1Item);

		Anvil_ItemData data = null;
		try {
			data = new Anvil_ItemData(par1Item, new ItemStack(par2Block), par3);
		} catch (net.petercashel.PacasStuff.anvil.Anvil_ItemData.DataConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (data != null) {
			anvilList.add(data);
		}
	}


	public static void registerItem(ItemStack par1Item, Block par2Block) {
		registerItem(par1Item, par2Block, 2);	
	}

	/**
	 * For removing an item from the anvilManager.
	 * params: Item
	 */
	public static void unregisterItem(ItemStack par1Item) {
		boolean isInManager = isInAnvilManager(par1Item);

		Iterator<Anvil_ItemData> iterator = anvilList.iterator();
		while (iterator.hasNext()) {
			Anvil_ItemData p = iterator.next();
			try {
				if (p.getItem().isItemEqual(par1Item)) {
					iterator.remove();
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * For getting the repair material of an item. WARNING! WILL CRASH IF NOT IN ANVILMANAGER!
	 * params: Item
	 */
	public static ItemStack getItemRepairMatID(ItemStack par1Item) {

		ItemStack localClone = new ItemStack (par1Item.getItem());
		localClone.setItemDamage(0);
		Iterator<Anvil_ItemData> iterator = anvilList.iterator();
		while (iterator.hasNext()) {
			Anvil_ItemData p = iterator.next();
			try {
				if (p.getItem().isItemEqual(localClone)) {
					return p.getRepairItem();
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		throw new IllegalArgumentException("Could not get repair material for item " + par1Item + " . not in anvilManager");
	}

	/**
	 * For getting the repair divider of an item. WARNING! WILL CRASH IF NOT IN ANVILMANAGER!
	 * params: Item
	 */
	public static int getItemRepairDivider(ItemStack par1Item) {
		ItemStack localClone = new ItemStack (par1Item.getItem());
		localClone.setItemDamage(0);
		Iterator<Anvil_ItemData> iterator = anvilList.iterator();
		while (iterator.hasNext()) {
			Anvil_ItemData p = iterator.next();
			try {
				if (p.getItem().isItemEqual(localClone)) {
					return p.getRepairDivider();
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		throw new IllegalArgumentException("Could not get repair divider for item " + par1Item + " . not in anvilManager");
	}


	/**
	 * For checking if item is in the anvilManager.
	 * params: Item
	 */
	public static boolean isInAnvilManager(ItemStack par1Item) {

		ItemStack localClone = new ItemStack (par1Item.getItem());
		Iterator<Anvil_ItemData> iterator = anvilList.iterator();
		while (iterator.hasNext()) {
			Anvil_ItemData p = iterator.next();
			try {
				if (p.getItem().isItemEqual(localClone)) {
					return true;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * For saving all data in the anvilManager.
	 * 
	 */
	public static boolean Save() {

		JsonArray jsonRoot = new JsonArray();

		Iterator<Anvil_ItemData> iterator = anvilList.iterator();
		while (iterator.hasNext()) {
			Anvil_ItemData p = iterator.next();
			JsonObject jobj = new JsonObject();

			jobj.addProperty("modId", p.modId);
			jobj.addProperty("name", p.name);
			jobj.addProperty("metadata", p.metadata);
			jobj.addProperty("RepairMat_modId", p.RepairMat_modId);
			jobj.addProperty("RepairMat_name", p.RepairMat_name);
			jobj.addProperty("RepairMat_metadata", p.RepairMat_metadata);
			jobj.addProperty("RepairMat_Divider", p.RepairMat_Divider);

			jsonRoot.add(jobj);
		}

		// Use GSON to pretty up my JSON.Simple
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString;
		jsonString = gson.toJson(jsonRoot);
		// done

		FileOutputStream fop = null;
		File file;
		File dir = new File("config" + File.separator + "anvil" + File.separator);
		dir.mkdirs();
		String content = jsonString;

		try {

			file = new File("config" + File.separator + "anvil" + File.separator + "anvilConfig.json");
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * For loading data into the anvilManager.
	 * 
	 */
	public static boolean Load() {
		String content = "";
		new File("config" + File.separator).mkdir();
		try {
			content = readFile("config" + File.separator + "anvil" + File.separator + "anvilConfig.json", Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AnvilCompatibility AnvilCompat = new net.petercashel.PacasStuff.anvil.AnvilCompatibility();
			if (mod_PacasStuff.CompatEnable) {
				AnvilCompat.init();
			}
			return false;
		}
		JsonElement jelement = new JsonParser().parse(content);
		//JsonObject  jobject = jelement.getAsJsonObject();
		JsonArray jarray = jelement.getAsJsonArray();

		Iterator<JsonElement> iterator = jarray.iterator();
		while (iterator.hasNext()) {
			JsonElement e = iterator.next();
			JsonObject s = e.getAsJsonObject();
			Gson gson = new GsonBuilder().create(); 
			anvilConfigData r = gson.fromJson(s, anvilConfigData.class);
			Anvil_ItemData data = new Anvil_ItemData(
					s.get("modId").getAsString(),
					s.get("name").getAsString(),
					s.get("metadata").getAsInt(),
					s.get("RepairMat_modId").getAsString(),
					s.get("RepairMat_name").getAsString(),
					s.get("RepairMat_metadata").getAsInt(),
					s.get("RepairMat_Divider").getAsInt()
					);

			anvilList.add(data);
		}

		return true;
	}

	public static String readFile(String path, Charset encoding) throws IOException {

		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	protected static void CompatLoad(Anvil_ItemData data) {
		anvilManager.registerItem(data.getItem(), data.getRepairItem(), data.getRepairDivider());
	}


}