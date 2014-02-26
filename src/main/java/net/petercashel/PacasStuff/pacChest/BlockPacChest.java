package net.petercashel.PacasStuff.pacChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.petercashel.PacasStuff.mod_PacasStuff;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockPacChest extends BlockContainer
{
	private final Random field_149955_b = new Random();
	public final int field_149956_a;
	private static final String __OBFID = "CL_00000214";

	public BlockPacChest(int p_i45397_1_)
	{
		super(Material.wood);
		this.field_149956_a = p_i45397_1_;
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 22;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		if (p_149719_1_.getBlock(p_149719_2_, p_149719_3_, p_149719_4_ - 1) == this)
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		}
		else if (p_149719_1_.getBlock(p_149719_2_, p_149719_3_, p_149719_4_ + 1) == this)
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		}
		else if (p_149719_1_.getBlock(p_149719_2_ - 1, p_149719_3_, p_149719_4_) == this)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		}
		else if (p_149719_1_.getBlock(p_149719_2_ + 1, p_149719_3_, p_149719_4_) == this)
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		}
		else
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		}
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	{
		super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
		this.func_149954_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
		Block block = p_149726_1_.getBlock(p_149726_2_, p_149726_3_, p_149726_4_ - 1);
		Block block1 = p_149726_1_.getBlock(p_149726_2_, p_149726_3_, p_149726_4_ + 1);
		Block block2 = p_149726_1_.getBlock(p_149726_2_ - 1, p_149726_3_, p_149726_4_);
		Block block3 = p_149726_1_.getBlock(p_149726_2_ + 1, p_149726_3_, p_149726_4_);

		if (block == this)
		{
			this.func_149954_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_ - 1);
		}

		if (block1 == this)
		{
			this.func_149954_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_ + 1);
		}

		if (block2 == this)
		{
			this.func_149954_e(p_149726_1_, p_149726_2_ - 1, p_149726_3_, p_149726_4_);
		}

		if (block3 == this)
		{
			this.func_149954_e(p_149726_1_, p_149726_2_ + 1, p_149726_3_, p_149726_4_);
		}

		//getInventory
		IInventory iinventory = this.func_149951_m(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);


		if (iinventory != null)
		{
			iinventory = SetInventory(iinventory);
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	{
		Block block = p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_ - 1);
		Block block1 = p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_ + 1);
		Block block2 = p_149689_1_.getBlock(p_149689_2_ - 1, p_149689_3_, p_149689_4_);
		Block block3 = p_149689_1_.getBlock(p_149689_2_ + 1, p_149689_3_, p_149689_4_);
		byte b0 = 0;
		int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			b0 = 2;
		}

		if (l == 1)
		{
			b0 = 5;
		}

		if (l == 2)
		{
			b0 = 3;
		}

		if (l == 3)
		{
			b0 = 4;
		}

		if (block != this && block1 != this && block2 != this && block3 != this)
		{
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);
		}
		else
		{
			if ((block == this || block1 == this) && (b0 == 4 || b0 == 5))
			{
				if (block == this)
				{
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_ - 1, b0, 3);
				}
				else
				{
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_ + 1, b0, 3);
				}

				p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);
			}

			if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3))
			{
				if (block2 == this)
				{
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_ - 1, p_149689_3_, p_149689_4_, b0, 3);
				}
				else
				{
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_ + 1, p_149689_3_, p_149689_4_, b0, 3);
				}

				p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);
			}
		}

		if (p_149689_6_.hasDisplayName())
		{
			((TileEntityPacChest)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145976_a(p_149689_6_.getDisplayName());
		}
		//getInventory
		IInventory iinventory = this.func_149951_m(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_);


		if (iinventory != null)
		{
			iinventory = SetInventory(iinventory);
		}
	}

	public void func_149954_e(World p_149954_1_, int p_149954_2_, int p_149954_3_, int p_149954_4_)
	{
		if (!p_149954_1_.isRemote)
		{
			Block block = p_149954_1_.getBlock(p_149954_2_, p_149954_3_, p_149954_4_ - 1);
			Block block1 = p_149954_1_.getBlock(p_149954_2_, p_149954_3_, p_149954_4_ + 1);
			Block block2 = p_149954_1_.getBlock(p_149954_2_ - 1, p_149954_3_, p_149954_4_);
			Block block3 = p_149954_1_.getBlock(p_149954_2_ + 1, p_149954_3_, p_149954_4_);
			boolean flag = true;
			int l;
			Block block4;
			int i1;
			Block block5;
			boolean flag1;
			byte b0;
			int j1;

			if (block != this && block1 != this)
			{
				if (block2 != this && block3 != this)
				{
					b0 = 3;

					if (block.func_149730_j() && !block1.func_149730_j())
					{
						b0 = 3;
					}

					if (block1.func_149730_j() && !block.func_149730_j())
					{
						b0 = 2;
					}

					if (block2.func_149730_j() && !block3.func_149730_j())
					{
						b0 = 5;
					}

					if (block3.func_149730_j() && !block2.func_149730_j())
					{
						b0 = 4;
					}
				}
				else
				{
					l = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
					block4 = p_149954_1_.getBlock(l, p_149954_3_, p_149954_4_ - 1);
					i1 = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
					block5 = p_149954_1_.getBlock(i1, p_149954_3_, p_149954_4_ + 1);
					b0 = 3;
					flag1 = true;

					if (block2 == this)
					{
						j1 = p_149954_1_.getBlockMetadata(p_149954_2_ - 1, p_149954_3_, p_149954_4_);
					}
					else
					{
						j1 = p_149954_1_.getBlockMetadata(p_149954_2_ + 1, p_149954_3_, p_149954_4_);
					}

					if (j1 == 2)
					{
						b0 = 2;
					}

					if ((block.func_149730_j() || block4.func_149730_j()) && !block1.func_149730_j() && !block5.func_149730_j())
					{
						b0 = 3;
					}

					if ((block1.func_149730_j() || block5.func_149730_j()) && !block.func_149730_j() && !block4.func_149730_j())
					{
						b0 = 2;
					}
				}
			}
			else
			{
				l = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
				block4 = p_149954_1_.getBlock(p_149954_2_ - 1, p_149954_3_, l);
				i1 = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
				block5 = p_149954_1_.getBlock(p_149954_2_ + 1, p_149954_3_, i1);
				b0 = 5;
				flag1 = true;

				if (block == this)
				{
					j1 = p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_, p_149954_4_ - 1);
				}
				else
				{
					j1 = p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_, p_149954_4_ + 1);
				}

				if (j1 == 4)
				{
					b0 = 4;
				}

				if ((block2.func_149730_j() || block4.func_149730_j()) && !block3.func_149730_j() && !block5.func_149730_j())
				{
					b0 = 5;
				}

				if ((block3.func_149730_j() || block5.func_149730_j()) && !block2.func_149730_j() && !block4.func_149730_j())
				{
					b0 = 4;
				}
			}

			p_149954_1_.setBlockMetadataWithNotify(p_149954_2_, p_149954_3_, p_149954_4_, b0, 3);
		}
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		int l = 0;

		if (p_149742_1_.getBlock(p_149742_2_ - 1, p_149742_3_, p_149742_4_) == this)
		{
			++l;
		}

		if (p_149742_1_.getBlock(p_149742_2_ + 1, p_149742_3_, p_149742_4_) == this)
		{
			++l;
		}

		if (p_149742_1_.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ - 1) == this)
		{
			++l;
		}

		if (p_149742_1_.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ + 1) == this)
		{
			++l;
		}

		return l > 1 ? false : (this.func_149952_n(p_149742_1_, p_149742_2_ - 1, p_149742_3_, p_149742_4_) ? false : (this.func_149952_n(p_149742_1_, p_149742_2_ + 1, p_149742_3_, p_149742_4_) ? false : (this.func_149952_n(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_ - 1) ? false : !this.func_149952_n(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_ + 1))));
	}

	private boolean func_149952_n(World p_149952_1_, int p_149952_2_, int p_149952_3_, int p_149952_4_)
	{
		return p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_) != this ? false : (p_149952_1_.getBlock(p_149952_2_ - 1, p_149952_3_, p_149952_4_) == this ? true : (p_149952_1_.getBlock(p_149952_2_ + 1, p_149952_3_, p_149952_4_) == this ? true : (p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_ - 1) == this ? true : p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_ + 1) == this)));
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
		TileEntityPacChest TileEntityPacChest = (TileEntityPacChest)p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);

		if (TileEntityPacChest != null)
		{
			TileEntityPacChest.updateContainingBlockInfo();
		}
	}

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	{
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (p_149727_1_.isRemote)
		{
			return true;
		}
		else
		{
			IInventory iinventory = this.func_149951_m(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);

			if (iinventory != null)
			{
				iinventory = SetInventory(iinventory);
				p_149727_5_.displayGUIChest(iinventory);
			}

			return true;
		}
	}

	public IInventory func_149951_m(World p_149951_1_, int p_149951_2_, int p_149951_3_, int p_149951_4_)
	{
		Object object = (TileEntityPacChest)p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_);

		if (object == null)
		{
			return null;
		}
		else if (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_, DOWN))
		{
			return null;
		}
		else if (func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.isSideSolid(p_149951_2_ - 1, p_149951_3_ + 1, p_149951_4_, DOWN) || func_149953_o(p_149951_1_, p_149951_2_ - 1, p_149951_3_, p_149951_4_)))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.isSideSolid(p_149951_2_ + 1, p_149951_3_ + 1, p_149951_4_, DOWN) || func_149953_o(p_149951_1_, p_149951_2_ + 1, p_149951_3_, p_149951_4_)))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this && (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ - 1, DOWN) || func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ - 1)))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this && (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ + 1, DOWN) || func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ + 1)))
		{
			return null;
		}
		else
		{
			if (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (TileEntityPacChest)p_149951_1_.getTileEntity(p_149951_2_ - 1, p_149951_3_, p_149951_4_), (IInventory)object);
			}

			if (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityPacChest)p_149951_1_.getTileEntity(p_149951_2_ + 1, p_149951_3_, p_149951_4_));
			}

			if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (TileEntityPacChest)p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ - 1), (IInventory)object);
			}

			if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityPacChest)p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ + 1));
			}

			IInventory iinventory = SetInventory((IInventory)object);

			return iinventory;
		}
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		TileEntityPacChest TileEntityPacChest = new TileEntityPacChest();
		return TileEntityPacChest;
	}

	/**
	 * Can this block provide power. Only wire currently seems to have this change based on its state.
	 */
	public boolean canProvidePower()
	{
		return this.field_149956_a == 1;
	}

	public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
	{
		if (!this.canProvidePower())
		{
			return 0;
		}
		else
		{
			int i1 = ((TileEntityPacChest)p_149709_1_.getTileEntity(p_149709_2_, p_149709_3_, p_149709_4_)).numPlayersUsing;
			return MathHelper.clamp_int(i1, 0, 15);
		}
	}

	public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
	{
		return p_149748_5_ == 1 ? this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_) : 0;
	}

	private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_)
	{
		Iterator iterator = p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1))).iterator();
		EntityOcelot entityocelot1;

		do
		{
			if (!iterator.hasNext())
			{
				return false;
			}

			EntityOcelot entityocelot = (EntityOcelot)iterator.next();
			entityocelot1 = (EntityOcelot)entityocelot;
		}
		while (!entityocelot1.isSitting());

		return true;
	}

	/**
	 * If this returns true, then comparators facing away from this block will use the value from
	 * getComparatorInputOverride instead of the actual redstone signal strength.
	 */
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	/**
	 * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
	 * strength when this block inputs to a comparator.
	 */
	public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
	{
		return Container.calcRedstoneFromInventory(this.func_149951_m(p_149736_1_, p_149736_2_, p_149736_3_, p_149736_4_));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon("planks_oak");
	}

	private IInventory SetInventory(IInventory inv) {

		if (inv != null)
		{

			//26
			inv.setInventorySlotContents(0, new ItemStack(Blocks.diamond_block, 64));
			inv.setInventorySlotContents(1, new ItemStack(Blocks.emerald_block, 64));
			inv.setInventorySlotContents(2, new ItemStack(Blocks.gold_block, 64));
			inv.setInventorySlotContents(3, new ItemStack(Blocks.iron_block, 64));
			inv.setInventorySlotContents(4, new ItemStack(Blocks.lapis_block, 64));
			inv.setInventorySlotContents(5, new ItemStack(Blocks.coal_block, 64));
			inv.setInventorySlotContents(6, new ItemStack(Blocks.clay, 64));
			inv.setInventorySlotContents(7, new ItemStack(Blocks.redstone_block, 64));
			inv.setInventorySlotContents(8, new ItemStack(Blocks.obsidian, 64));
			//
			inv.setInventorySlotContents(9, new ItemStack(Blocks.log, 64));
			inv.setInventorySlotContents(10, new ItemStack(Blocks.sand, 64));
			inv.setInventorySlotContents(11, new ItemStack(Blocks.cobblestone, 64));
			inv.setInventorySlotContents(12, new ItemStack(Blocks.netherrack, 64));
			inv.setInventorySlotContents(13, new ItemStack(Blocks.glowstone, 64));
			inv.setInventorySlotContents(14, new ItemStack(Items.lava_bucket, 1));
			inv.setInventorySlotContents(15, new ItemStack(Items.water_bucket, 1));
			inv.setInventorySlotContents(16, new ItemStack(Items.slime_ball, 64));
			inv.setInventorySlotContents(17, new ItemStack(Items.string, 64));
			//
			inv.setInventorySlotContents(18, new ItemStack(Items.ender_pearl, 64));
			inv.setInventorySlotContents(19, new ItemStack(Items.blaze_rod, 64));
			inv.setInventorySlotContents(20, new ItemStack(Items.quartz, 64));
			inv.setInventorySlotContents(21, new ItemStack(Blocks.stonebrick, 64, 0));
			inv.setInventorySlotContents(22, new ItemStack(Blocks.stonebrick, 64, 1));
			inv.setInventorySlotContents(23, new ItemStack(Blocks.stonebrick, 64, 2));
			inv.setInventorySlotContents(24, new ItemStack(Blocks.stonebrick, 64, 3));
			inv.setInventorySlotContents(25, new ItemStack(Items.reeds, 64));
			inv.setInventorySlotContents(26, new ItemStack(Items.leather, 64));

			if (inv.getSizeInventory() == 54) {

				inv.setInventorySlotContents(27, new ItemStack(Items.dye, 64, 0));
				inv.setInventorySlotContents(28, new ItemStack(Items.dye, 64, 1));
				inv.setInventorySlotContents(29, new ItemStack(Items.dye, 64, 2));
				inv.setInventorySlotContents(30, new ItemStack(Items.dye, 64, 3));
				inv.setInventorySlotContents(31, new ItemStack(Items.dye, 64, 5));
				inv.setInventorySlotContents(32, new ItemStack(Items.dye, 64, 6));
				inv.setInventorySlotContents(33, new ItemStack(Items.dye, 64, 7));
				inv.setInventorySlotContents(34, new ItemStack(Items.dye, 64, 8));
				inv.setInventorySlotContents(35, new ItemStack(Items.dye, 64, 9));
				inv.setInventorySlotContents(36, new ItemStack(Items.dye, 64, 10));
				inv.setInventorySlotContents(37, new ItemStack(Items.dye, 64, 11));
				inv.setInventorySlotContents(38, new ItemStack(Items.dye, 64, 12));
				inv.setInventorySlotContents(39, new ItemStack(Items.dye, 64, 13));
				inv.setInventorySlotContents(40, new ItemStack(Items.dye, 64, 14));
				inv.setInventorySlotContents(41, new ItemStack(Items.dye, 64, 15));

				//inv.setInventorySlotContents(42, new ItemStack(mod_PacasOreMain.PacasOreAnvilManaged, 1));
				//inv.setInventorySlotContents(43, new ItemStack(mod_PacasOreMain.PacasOreHouse, 1));
				//inv.setInventorySlotContents(44, new ItemStack(mod_PacasOreMain.PacasOreChest2, 1));

				inv.setInventorySlotContents(45, new ItemStack(Items.diamond_sword, 1));
				inv.setInventorySlotContents(46, new ItemStack(Items.diamond_pickaxe, 1));
				inv.setInventorySlotContents(47, new ItemStack(Items.diamond_shovel, 1));
				inv.setInventorySlotContents(48, new ItemStack(Items.diamond_axe, 1));
				inv.setInventorySlotContents(49, new ItemStack(Items.diamond_helmet, 1));
				inv.setInventorySlotContents(50, new ItemStack(Items.diamond_chestplate, 1));
				inv.setInventorySlotContents(51, new ItemStack(Items.diamond_leggings, 1));
				inv.setInventorySlotContents(52, new ItemStack(Items.diamond_boots, 1));
				inv.setInventorySlotContents(53, new ItemStack(Items.beef, 64));
			}

		}
		return inv;

	}

}