package net.petercashel.PacasStuff.anvil.AE_Enabled;

import java.util.ArrayList;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.petercashel.PacasStuff.mod_PacasStuff;
import net.petercashel.PacasStuff.anvil.anvilManager;

public class BlockPacasAnvil extends BlockContainer implements ITileEntityProvider {

	//essential methods, so that the model is visible

	public BlockPacasAnvil()
	{
		super(Material.iron);
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
	}

	public int getRenderType()
	{
		return -1;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("pacas_stuff:PacasAnvilModel");
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}


	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityPacasAnvil();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack i)
	{
		super.onBlockPlacedBy(w, x, y, z, e, i);

		if (i.hasDisplayName())
		{

		}
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par5EntityPlayer.getHeldItem() == null) {
			if (!par1World.isRemote) {
				// Chat Message saying item does not need repair
				par5EntityPlayer
				.addChatMessage(new ChatComponentText("Sorry, but your Princess is in Another Castle."));

				par5EntityPlayer.inventoryContainer.detectAndSendChanges();
			}
			return false;
		} else {
			ItemStack heldItem = par5EntityPlayer.getHeldItem();
			if (anvilManager.isInAnvilManager(heldItem)) {
				System.out.println(heldItem + " " + anvilManager.getItemRepairMatID(heldItem));
				if (par5EntityPlayer.getHeldItem().getItemDamage() == 0) {
					if (!par1World.isRemote) {
						// Chat Message saying item does not need repair

						par5EntityPlayer
						.addChatMessage(new ChatComponentText("That does not need repairing."));	

						par5EntityPlayer.inventoryContainer.detectAndSendChanges();
					}
					return false;
				}
				if (hasCompatableItemStack(par5EntityPlayer, anvilManager.getItemRepairMatID(heldItem))) {
					ItemStack repairItem = getCompatableItemStack(par5EntityPlayer, anvilManager.getItemRepairMatID(heldItem));
					return performRepair(par5EntityPlayer, heldItem, repairItem, par1World);

				} else if (TryAE2Intergration(par1World,par2, par3,
						par4,  par5EntityPlayer, par6, par7,
						par8, par9, heldItem)) {
					return true;
				} else if (par5EntityPlayer.capabilities.isCreativeMode) {
					int maxDam = par5EntityPlayer.getHeldItem()
							.getMaxDamage();
					int currDam = par5EntityPlayer.getHeldItem()
							.getItemDamage();
					int repairamount = (maxDam / (anvilManager.getItemRepairDivider(heldItem))) + 1;
					int newDam = currDam - repairamount;
					String damageword = " a little";
					if (newDam < 0) {
						newDam = 0;
						damageword = " completely";
					}
					par5EntityPlayer.getHeldItem().setItemDamage(newDam);
					if (!par1World.isRemote) {
						par5EntityPlayer.addChatMessage(new ChatComponentText("Repaired your " + (heldItem).getDisplayName() + damageword + ", Master Pacas00. Now go away!"));
					}
					par5EntityPlayer.inventoryContainer.detectAndSendChanges();
					return true;

				} else {
					Block varblock = par1World.getBlock(par2, par3 - 1, par4);
					if (varblock instanceof BlockChest) {
						TileEntityChest chest = (TileEntityChest) par1World.getTileEntity(par2, par3 - 1, par4);

						if (hasCompatableItemStack(chest, anvilManager.getItemRepairMatID(heldItem))) {
							ItemStack repairItem = getCompatableItemStack(chest, anvilManager.getItemRepairMatID(heldItem));
							return performRepair(chest, par5EntityPlayer, heldItem, repairItem, par1World);

						}
						if (!par1World.isRemote) {
							par5EntityPlayer
							.addChatMessage(new ChatComponentText("Insufficent Material."));
						}
					} else {
						if (!par1World.isRemote) {
							par5EntityPlayer
							.addChatMessage(new ChatComponentText("Insufficent Material."));
							return false;
						}
					}
				}
			} else {
				if (!par1World.isRemote) {
					if (net.petercashel.PacasStuff.mod_PacasStuff.anvilBadItem = true)
					{
						if (Item.getIdFromItem(heldItem.getItem()) == Item.getIdFromItem(net.petercashel.PacasStuff.mod_PacasStuff.ItemPacasAnvilTool)) { //TODO REWRITE - NO ITEM IDS
							if (isPlayerOp(par5EntityPlayer.getGameProfile())) {
								if (par5EntityPlayer.inventory.currentItem == 8) {
									if (par5EntityPlayer.getHeldItem().getItemDamage() == 1) {
										par5EntityPlayer.addChatMessage(new ChatComponentText("Adding " + par5EntityPlayer.inventory.getStackInSlot(0).getDisplayName() + " to be repaired with " + par5EntityPlayer.inventory.getStackInSlot(1).stackSize + " " + par5EntityPlayer.inventory.getStackInSlot(1).getDisplayName()));
										anvilManager.registerItem(par5EntityPlayer.inventory.getStackInSlot(0), par5EntityPlayer.inventory.getStackInSlot(1), par5EntityPlayer.inventory.getStackInSlot(1).stackSize);
										anvilManager.Save();
									} else {
										par5EntityPlayer.addChatMessage(new ChatComponentText("Removing " + par5EntityPlayer.inventory.getStackInSlot(0).getDisplayName()));
										anvilManager.unregisterItem(par5EntityPlayer.inventory.getStackInSlot(0));
										anvilManager.Save();
									}
									//par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(mod_PacasStuff.PacasOreChest, 1));
									par5EntityPlayer.inventoryContainer.detectAndSendChanges();
								} else {
									if (par5EntityPlayer.getHeldItem().getItemDamage() == 1) {
										par5EntityPlayer.addChatMessage(new ChatComponentText("=====How To Use===="));
										par5EntityPlayer.addChatMessage(new ChatComponentText("Place this item in the Slot 9 of your hotbar, Place the item you want to add in Slot 1 and Place the repair material in Slot 2."));
										par5EntityPlayer.addChatMessage(new ChatComponentText("Putting 3 items in Slot 2 will make it take 3 items to fully repair an almost destroyed tool."));
										par5EntityPlayer.addChatMessage(new ChatComponentText(""));
									} else {
										par5EntityPlayer.addChatMessage(new ChatComponentText("=====How To Use===="));
										par5EntityPlayer.addChatMessage(new ChatComponentText("Place this item in the Slot 9 of your hotbar, Place the item you want to remove in Slot 1."));
										par5EntityPlayer.addChatMessage(new ChatComponentText(""));
									}

								}
								return true;
							} else {
								par5EntityPlayer
								.addChatMessage(new ChatComponentText("Have you tried coding that in?"));
								return false;
							}
						} else {
							par5EntityPlayer.addChatMessage(new ChatComponentText("This anvil will not repair that."));
							return false;
						}
					}
				}
				return false;
			}
		}
		return false;
	}


	private boolean TryAE2Intergration(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9, ItemStack heldItem) {
		if (!par1World.isRemote) {
			if (Loader.isModLoaded("appliedenergistics2")) {

				TileEntityPacasAnvil tile = getTile(par1World, par2, par3, par4);

				if (tile.HasCompatibleItemStack(anvilManager.getItemRepairMatID(heldItem))) {
					ItemStack repairItem = tile.FindCompatibleItemStack(anvilManager.getItemRepairMatID(heldItem));
					if (tile.NetworkConsumeItemStack(repairItem, par5EntityPlayer, par1World)) {
						par5EntityPlayer.addPotionEffect(new PotionEffect(2, 40, 127));
						int maxDam = par5EntityPlayer.getHeldItem()
								.getMaxDamage();
						int currDam = par5EntityPlayer.getHeldItem()
								.getItemDamage();
						int repairamount = (maxDam / (anvilManager.getItemRepairDivider(heldItem))) + 1;
						int newDam = currDam - repairamount;
						String damageword = " a little.";
						if (newDam < 0) {
							newDam = 0;
							damageword = " completely.";
						}
						par5EntityPlayer.getHeldItem().setItemDamage(newDam);
						int size = par5EntityPlayer.inventory.getSizeInventory();


						par5EntityPlayer.addChatMessage(new ChatComponentText("Repaired your " + (heldItem).getDisplayName() + damageword));
						par5EntityPlayer.inventoryContainer.detectAndSendChanges();
						return true;
					}

				}


			} else {
				return false;
			}
		}

		return false;
	}

	public TileEntityPacasAnvil getTile(World par1World, int par2, int par3,
			int par4) {
		TileEntityPacasAnvil te = (TileEntityPacasAnvil) par1World.getTileEntity( par2, par3, par4 );
		return te;
	}

	/**
	 * removed one item of specified itemID from inventory (if it is in a stack, the stack size will reduce with 1)
	 * @param heldItem 
	 * @param mainInventory 
	 */
	public boolean consumePlayerInventoryItem(ItemStack[] mainInventory, ItemStack heldItem)
	{
		ItemStack item = heldItem;
		int j = this.getPlayerInventorySlotContainItemAndDamage(Item.getIdFromItem(item.getItem()), item.getItemDamage(), mainInventory); //TODO REWRITE - NO ITEM IDS

		if (j < 0)
		{
			return false;
		}
		else
		{
			if (--mainInventory[j].stackSize <= 0)
			{
				mainInventory[j] = null;
			}

			return true;
		}
	}

	public int getPlayerInventorySlotContainItemAndDamage(int par1, int par2, ItemStack[] mainInventory)
	{
		for (int k = 0; k < mainInventory.length; ++k)
		{
			if (mainInventory[k] != null && Item.getIdFromItem(mainInventory[k].getItem()) == par1 && mainInventory[k].getItemDamage() == par2) //TODO REWRITE - NO ITEM IDS
			{
				return k;
			}
		}

		return -1;
	}


	static public boolean isPlayerOp(GameProfile profile)
	{ return MinecraftServer.getServer().getConfigurationManager().func_152596_g(profile); }







	//Copied from my basic anvil class

	private boolean performRepair(EntityPlayer par5EntityPlayer,
			ItemStack heldItem, ItemStack repairItem, World par1World) {

		par5EntityPlayer.addPotionEffect(new PotionEffect(2, 40, 127));
		int maxDam = par5EntityPlayer.getHeldItem()
				.getMaxDamage();
		int currDam = par5EntityPlayer.getHeldItem()
				.getItemDamage();
		int repairamount = (maxDam / (anvilManager.getItemRepairDivider(heldItem))) + 1;
		int newDam = currDam - repairamount;
		String damageword = " a little.";
		if (newDam < 0) {
			newDam = 0;
			damageword = " completely.";
		}
		par5EntityPlayer.getHeldItem().setItemDamage(newDam);
		int size = par5EntityPlayer.inventory.getSizeInventory();

		consumePlayerInventoryItem(par5EntityPlayer.inventory.mainInventory, repairItem);

		if (!par1World.isRemote) {
			par5EntityPlayer.addChatMessage(new ChatComponentText("Repaired your " + (heldItem).getDisplayName() + damageword));
		}
		par5EntityPlayer.inventoryContainer.detectAndSendChanges();

		return true;
	}

	private boolean performRepair(TileEntityChest chest, EntityPlayer par5EntityPlayer,
			ItemStack heldItem, ItemStack repairItem, World par1World) {

		int size = chest.getSizeInventory();
		for (int slots = 0; slots < size; ++slots) {
			if (chest.getStackInSlot(slots) != null) { 
				if (chest.getStackInSlot(slots).isItemEqual(repairItem)) {
					par5EntityPlayer.addPotionEffect(new PotionEffect(2, 40, 127));
					int maxDam = par5EntityPlayer.getHeldItem()
							.getMaxDamage();
					int currDam = par5EntityPlayer.getHeldItem()
							.getItemDamage();
					int repairamount = (maxDam / (anvilManager.getItemRepairDivider(heldItem))) + 1;
					int newDam = currDam - repairamount;
					String damageword = " a little.";
					if (newDam < 0) {
						newDam = 0;
						damageword = " completely.";
					}
					par5EntityPlayer.getHeldItem().setItemDamage(newDam);
					chest.decrStackSize(slots, 1);
					if (!par1World.isRemote) {
						par5EntityPlayer.addChatMessage(new ChatComponentText("Repaired your " + (heldItem).getDisplayName() + damageword));
					}
					par5EntityPlayer.inventoryContainer.detectAndSendChanges();
					return true;

				}
			}
		}

		return true;
	}

	private boolean hasCompatableItemStack(EntityPlayer par5EntityPlayer,
			ItemStack itemRepairMatID) {

		int[] ids = OreDictionary.getOreIDs(itemRepairMatID);
		for (int i = 0; i < ids.length; i++) {
			String name = OreDictionary.getOreName(ids[i]);
			ArrayList<ItemStack> items = OreDictionary.getOres(name);
			for (int j = 0; j < items.size(); j++) {
				ItemStack item = items.get(j);
				if (par5EntityPlayer.inventory.hasItemStack(item)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean hasCompatableItemStack(TileEntityChest chest,
			ItemStack itemRepairMatID) {

		int[] ids = OreDictionary.getOreIDs(itemRepairMatID);
		for (int i = 0; i < ids.length; i++) {
			String name = OreDictionary.getOreName(ids[i]);
			ArrayList<ItemStack> items = OreDictionary.getOres(name);
			for (int j = 0; j < items.size(); j++) {
				ItemStack item = items.get(j);
				int size = chest.getSizeInventory();
				for (int slots = 0; slots < size; ++slots) {
					if (chest.getStackInSlot(slots) != null) { 
						if (chest.getStackInSlot(slots).isItemEqual(item)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private ItemStack getCompatableItemStack(EntityPlayer par5EntityPlayer,
			ItemStack itemRepairMatID) {

		int[] ids = OreDictionary.getOreIDs(itemRepairMatID);
		for (int i = 0; i < ids.length; i++) {
			String name = OreDictionary.getOreName(ids[i]);
			ArrayList<ItemStack> items = OreDictionary.getOres(name);
			for (int j = 0; j < items.size(); j++) {
				ItemStack item = items.get(j);
				if (par5EntityPlayer.inventory.hasItemStack(item)) {
					return item;
				}
			}
		}

		return null;
	}

	private ItemStack getCompatableItemStack(TileEntityChest chest,
			ItemStack itemRepairMatID) {

		int[] ids = OreDictionary.getOreIDs(itemRepairMatID);
		for (int i = 0; i < ids.length; i++) {
			String name = OreDictionary.getOreName(ids[i]);
			ArrayList<ItemStack> items = OreDictionary.getOres(name);
			for (int j = 0; j < items.size(); j++) {
				ItemStack item = items.get(j);
				int size = chest.getSizeInventory();
				for (int slots = 0; slots < size; ++slots) {
					if (chest.getStackInSlot(slots) != null) { 
						if (chest.getStackInSlot(slots).isItemEqual(item)) {
							return item;
						}
					}
				}
			}
		}

		return null;
	}



}
