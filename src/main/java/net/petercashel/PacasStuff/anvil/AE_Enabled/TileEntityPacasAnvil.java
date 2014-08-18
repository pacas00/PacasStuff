package net.petercashel.PacasStuff.anvil.AE_Enabled;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.ArrayList;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import appeng.api.config.Actionable;
import appeng.api.networking.GridFlags;
import appeng.api.networking.IGridNode;
import appeng.api.networking.events.MENetworkChannelsChanged;
import appeng.api.networking.events.MENetworkEventSubscribe;
import appeng.api.networking.events.MENetworkPowerStatusChange;
import appeng.api.networking.security.MachineSource;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;
import appeng.api.util.AECableType;
import appeng.api.util.AEColor;
import appeng.me.GridAccessException;
import appeng.tile.events.AETileEventHandler;
import appeng.tile.events.TileEventType;
import appeng.tile.grid.AENetworkTile;
import appeng.tile.networking.TileCableBus;
import appeng.util.item.AEItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class TileEntityPacasAnvil extends AnvilAENetBaseTile {
	
	public TileEntityPacasAnvil() {
	}
	
	public boolean NetworkHasItemStack(ItemStack itemstack) {
				if( this.getItemMonitor() )
				{
					IAEItemStack request = AEItemStack.create(itemstack);
					IAEItemStack ItemStack = this.monitor.extractItems( request, Actionable.SIMULATE, new MachineSource( this ) );
					if( ItemStack == null )
					{
						return false;
					}
					if (ItemStack.getStackSize() != 0) {
						return true;	
					}
				}
				return false;
	}

	public boolean NetworkConsumeItemStack(ItemStack itemstack, EntityPlayer Player, World world) {
		return extractItemFromNetwork(itemstack, world, Player);
	}

	public ItemStack FindCompatibleItemStack(ItemStack itemstack) {

			int[] ids = OreDictionary.getOreIDs(itemstack);
			for (int i = 0; i < ids.length; i++) {
				String name = OreDictionary.getOreName(ids[i]);
				ArrayList<ItemStack> items = OreDictionary.getOres(name);
				for (int j = 0; j < items.size(); j++) {
					ItemStack item = items.get(j);
					if (NetworkHasItemStack(item)) {
						return item;
					}
				}
			}

			return null;
	}
	
	public boolean HasCompatibleItemStack(ItemStack itemstack) {

		int[] ids = OreDictionary.getOreIDs(itemstack);
		for (int i = 0; i < ids.length; i++) {
			String name = OreDictionary.getOreName(ids[i]);
			ArrayList<ItemStack> items = OreDictionary.getOres(name);
			for (int j = 0; j < items.size(); j++) {
				ItemStack item = items.get(j);
				if (NetworkHasItemStack(item)) {
					return true;
				}
			}
		}

		return false;
}

	
}
