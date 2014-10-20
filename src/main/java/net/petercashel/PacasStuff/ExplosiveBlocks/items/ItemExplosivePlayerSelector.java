package net.petercashel.PacasStuff.ExplosiveBlocks.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import java.util.Random;
import java.util.UUID;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.petercashel.PacasStuff.mod_PacasStuff;
import net.petercashel.PacasStuff.interfaces.IPlayerUUID;

public class ItemExplosivePlayerSelector extends Item {

	public ItemExplosivePlayerSelector()
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(mod_PacasStuff.tabPacasExplosions);
	}

	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("pacas_stuff:" + "ItemFireTool");
	}

	@SideOnly(Side.CLIENT)

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return itemIcon;
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		itemStack.stackTagCompound = new NBTTagCompound();
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		System.out.println("X:"+par4);
		System.out.println("Y:"+par5);
		System.out.println("Z:"+par6);
		System.out.println("FX:"+par8);
		System.out.println("FY:"+par9);
		System.out.println("FZ:"+par10);
		if (par3World.getBlock(par4, par5, par6) != null) {
			System.out.println("BLOCK!");
			Block b = par3World.getBlock(par4, par5, par6);
			if (b.isEqualTo(b, Blocks.bed)) {
				System.out.println("BED!!");
			}
			if (b instanceof IPlayerUUID) {
				System.out.println("ExplodeEnabled!!");
				if (par2EntityPlayer.isSneaking()) {
					if (par1ItemStack.stackTagCompound.getString("targetUUID") != null && par1ItemStack.stackTagCompound.getString("targetUUID").length() > 8) {
						((IPlayerUUID) b).SetPlayerUUID(UUID.fromString(par1ItemStack.stackTagCompound.getString("targetUUID")));
						System.out.println("ExplodeSet!!");
					} else {
						((IPlayerUUID) b).ClearPlayerUUID();
						System.out.println("ExplodeClear!!");
					}
					
				}
			}
		}
		return true;
	}



	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par3EntityPlayer.isSneaking()) {
			
			if (par1ItemStack.stackTagCompound != null) {
				par1ItemStack.stackTagCompound.setString("targetName", "");
				par1ItemStack.stackTagCompound.setString("targetUUID", "");
			}
		} else {
			if (par1ItemStack.stackTagCompound != null) {
				par1ItemStack.stackTagCompound.setString("targetName", par3EntityPlayer.getGameProfile().getName());
				String id = UUID.randomUUID().toString();
				if (par3EntityPlayer.getGameProfile().getId() != null) {
					id = par3EntityPlayer.getGameProfile().getId().toString();
					
				}
				par1ItemStack.stackTagCompound.setString("targetUUID", id);
			} else {
				par1ItemStack.stackTagCompound = new NBTTagCompound();
			}
		}
		
		System.out.println(par3EntityPlayer.inventory.getStackInSlot(0).getUnlocalizedName());
		System.out.println(par3EntityPlayer.inventory.getStackInSlot(0).getDisplayName());
		
		return par1ItemStack;
	}

	/**
	 * Called when the player Left Clicks (attacks) an entity.
	 * Processed before damage is done, if return value is true further processing is canceled
	 * and the entity is not attacked.
	 *
	 * @param stack The Item being used
	 * @param player The player that is attacking
	 * @param entity The entity being attacked
	 * @return True to cancel the rest of the interaction.
	 */
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (entity instanceof EntityPlayer) {
			EntityPlayer plr = (EntityPlayer) entity;
			System.out.println("ENT!! PLAYER!! " + plr.getGameProfile().getName() + " UUID:" + plr.getGameProfile().getId());

			if (stack.stackTagCompound != null) {
				//NBT for targer
				stack.stackTagCompound.setString("targetName", plr.getGameProfile().getName());
				stack.stackTagCompound.setString("targetUUID", plr.getGameProfile().getId().toString());
			}

			return true;
		}

		return false;
	}

	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {

		if (itemStack.stackTagCompound != null) {
			String targetName = itemStack.stackTagCompound.getString("targetName");
			String targetUUID = itemStack.stackTagCompound.getString("targetUUID");
			list.add("Target Name: " + targetName);
			list.add("Target UUID: " + targetUUID);

		}
	}

    

}
