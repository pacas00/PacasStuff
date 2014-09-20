package net.petercashel.PacasStuff.DIM_Redlands;

import net.minecraft.block.Block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class BlockRedlandsPortal extends BlockBreakable {

	public static final int[][] field_150001_a = new int[][] {new int[0], {3, 1}, {2, 0}};

	public BlockRedlandsPortal(String blockName)
	{
		super("pacas_stuff" + ":" + "RedlandsPortal", Material.portal, false);
		this.setTickRandomly(true);
		this.setHardness(-1.0F);
		this.setStepSound(soundTypeGlass);
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
		this.setBlockName(blockName);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */

	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if (par1World.provider.isSurfaceWorld() && par1World.getGameRules().getGameRuleBooleanValue("doMobSpawning") && par5Random.nextInt(2000) < par1World.difficultySetting.getDifficultyId())
		{
			int l;
			for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par1World, par2, 1, par4) && l > 0; --l)
			{
				;
			}
			if (l > 0 && !par1World.getBlock(par2, l + 1, par4).isNormalCube())
			{
				Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57, (double)par2 + 0.5D, (double)l + 1.1D, (double)par4 + 0.5D);
				if (entity != null)
				{
					entity.timeUntilPortal = entity.getPortalCooldown();
				}
			}
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		float f;
		float f1;
		if (par1IBlockAccess.getBlock(par2 - 1, par3, par4) != this && par1IBlockAccess.getBlock(par2 + 1, par3, par4) != this)
		{
			f = 0.125F;
			f1 = 0.5F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
		else
		{
			f = 0.5F;
			f1 = 0.125F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
	}

	/**
	 * Is this block (a) opaque and (B) a full 1m cube? This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */

	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

//	/**
//	 * Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z
//	 */
//	public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
//	{
//		byte b0 = 0;
//		byte b1 = 0;
//		if (par1World.getBlock(par2 - 1, par3, par4) == mod_PacasStuff.RedlandsPortalFrame || par1World.getBlock(par2 + 1, par3, par4) == mod_PacasStuff.RedlandsPortalFrame)
//		{
//			b0 = 1;
//		}
//		if (par1World.getBlock(par2, par3, par4 - 1) == mod_PacasStuff.RedlandsPortalFrame || par1World.getBlock(par2, par3, par4 + 1) == mod_PacasStuff.RedlandsPortalFrame)
//		{
//			b1 = 1;
//		}
//		if (b0 == b1)
//		{
//			return false;
//		}
//		else
//		{
//			if (par1World.getBlock(par2 - b0, par3, par4 - b1) == null)
//			{
//				par2 -= b0;
//				par4 -= b1;
//			}
//			int l;
//			int i1;
//			for (l = -1; l <= 2; ++l)
//			{
//				for (i1 = -1; i1 <= 3; ++i1)
//				{
//					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
//					if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
//					{
//						Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
//						if (flag)
//						{
//							if (j1 != mod_PacasStuff.RedlandsPortalFrame)
//							{
//								return false;
//							}
//						}
//						else if (j1 != null && j1 != mod_PacasStuff.RedlandsPortalFire)
//						{
//							return false;
//						}
//					}
//				}
//			}
//			for (l = 0; l < 2; ++l)
//			{
//				for (i1 = 0; i1 < 3; ++i1)
//				{
//					par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, mod_PacasStuff.RedlandsPortal, 0, 2);
//				}
//			}
//			return true;
//		}
//	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5_)
	{
		byte b0 = 0;
		byte b1 = 1;
		if (par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this)
		{
			b0 = 1;
			b1 = 0;
		}
		int i1;
		for (i1 = par3; par1World.getBlock(par2, i1 - 1, par4) == this; --i1)
		{
			;
		}
		if (par1World.getBlock(par2, i1 - 1, par4) != mod_PacasStuff.RedlandsPortalFrame)
		{
			par1World.setBlockToAir(par2, par3, par4);
		}
		else
		{
			int j1;
			for (j1 = 1; j1 < 4 && par1World.getBlock(par2, i1 + j1, par4) == this; ++j1)
			{
				;
			}
			if (j1 == 3 && par1World.getBlock(par2, i1 + j1, par4) == mod_PacasStuff.RedlandsPortalFrame)
			{
				boolean flag = par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this;
				boolean flag1 = par1World.getBlock(par2, par3, par4 - 1) == this || par1World.getBlock(par2, par3, par4 + 1) == this;
				if (flag && flag1)
				{
					par1World.setBlockToAir(par2, par3, par4);
				}
				else
				{
					if ((par1World.getBlock(par2 + b0, par3, par4 + b1) != mod_PacasStuff.RedlandsPortalFrame || par1World.getBlock(par2 - b0, par3, par4 - b1) != this) && (par1World.getBlock(par2 - b0, par3, par4 - b1) != mod_PacasStuff.RedlandsPortalFrame || par1World.getBlock(par2 + b0, par3, par4 + b1) != this))
					{
						par1World.setBlockToAir(par2, par3, par4);
					}
				}
			}
			else
			{
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates. Args: blockAccess, x, y, z, side
	 */

	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par1IBlockAccess.getBlock(par2, par3, par4) == this)
		{
			return false;
		}
		else
		{
			boolean flag = par1IBlockAccess.getBlock(par2 - 1, par3, par4) == this && par1IBlockAccess.getBlock(par2 - 2, par3, par4) != this;
			boolean flag1 = par1IBlockAccess.getBlock(par2 + 1, par3, par4) == this && par1IBlockAccess.getBlock(par2 + 2, par3, par4) != this;
			boolean flag2 = par1IBlockAccess.getBlock(par2, par3, par4 - 1) == this && par1IBlockAccess.getBlock(par2, par3, par4 - 2) != this;
			boolean flag3 = par1IBlockAccess.getBlock(par2, par3, par4 + 1) == this && par1IBlockAccess.getBlock(par2, par3, par4 + 2) != this;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return flag4 && par5 == 4 ? true : (flag4 && par5 == 5 ? true : (flag5 && par5 == 2 ? true : flag5 && par5 == 3));
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */

	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP thePlayer = (EntityPlayerMP)par5Entity;
			if (thePlayer.timeUntilPortal > 0)
			{
				thePlayer.timeUntilPortal = 10;
			} else if (thePlayer.dimension != mod_PacasStuff.DIM_ID_Redlands)
			{
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, mod_PacasStuff.DIM_ID_Redlands, new RedlandsTeleporter(thePlayer.mcServer.worldServerForDimension(mod_PacasStuff.DIM_ID_Redlands)));
			}
			else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new RedlandsTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	public int getRenderBlockPass()
	{
		return 1;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par5Random.nextInt(100) == 0)
		{
			par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
		}
		for (int l = 0; l < 4; ++l)
		{
			double d0 = (double)((float)par2 + par5Random.nextFloat());
			double d1 = (double)((float)par3 + par5Random.nextFloat());
			double d2 = (double)((float)par4 + par5Random.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = par5Random.nextInt(2) * 2 - 1;
			d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
			d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
			d5 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
			if (par1World.getBlock(par2 - 1, par3, par4) != this && par1World.getBlock(par2 + 1, par3, par4) != this)
			{
				d0 = (double)par2 + 0.5D + 0.25D * (double)i1;
				d3 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
			}
			else
			{
				d2 = (double)par4 + 0.5D + 0.25D * (double)i1;
				d5 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
			}
			par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return 0;
	}

	public boolean getSize(World par1World, int par2, int par3, int par4) {
		return false;
	}

	public boolean func_150000_e(World p_150000_1_, int p_150000_2_, int p_150000_3_, int p_150000_4_)
	{
		BlockRedlandsPortal.Size size = new BlockRedlandsPortal.Size(p_150000_1_, p_150000_2_, p_150000_3_, p_150000_4_, 1);
		BlockRedlandsPortal.Size size1 = new BlockRedlandsPortal.Size(p_150000_1_, p_150000_2_, p_150000_3_, p_150000_4_, 2);

		if (size.func_150860_b() && size.field_150864_e == 0)
		{
			size.func_150859_c();
			return true;
		}
		else if (size1.func_150860_b() && size.field_150864_e == 0)
		{
			size1.func_150859_c();
			return true;
		}
		else
		{
			return false;
		}
	}

	public static class Size
	{
		private final World field_150867_a;
		private final int field_150865_b;
		private final int field_150866_c;
		private final int field_150863_d;
		public int field_150864_e = 0;
		private ChunkCoordinates field_150861_f;
		private int field_150862_g;
		private int field_150868_h;
		private static final String __OBFID = "CL_00000285";

		public Size(World p_i45415_1_, int p_i45415_2_, int p_i45415_3_, int p_i45415_4_, int p_i45415_5_)
		{
			this.field_150867_a = p_i45415_1_;
			this.field_150865_b = p_i45415_5_;
			this.field_150863_d = BlockRedlandsPortal.field_150001_a[p_i45415_5_][0];
			this.field_150866_c = BlockRedlandsPortal.field_150001_a[p_i45415_5_][1];

			for (int i1 = p_i45415_3_; p_i45415_3_ > i1 - 21 && p_i45415_3_ > 0 && this.func_150857_a(p_i45415_1_.getBlock(p_i45415_2_, p_i45415_3_ - 1, p_i45415_4_)); --p_i45415_3_)
			{
				;
			}

			int j1 = this.func_150853_a(p_i45415_2_, p_i45415_3_, p_i45415_4_, this.field_150863_d) - 1;

			if (j1 >= 0)
			{
				this.field_150861_f = new ChunkCoordinates(p_i45415_2_ + j1 * Direction.offsetX[this.field_150863_d], p_i45415_3_, p_i45415_4_ + j1 * Direction.offsetZ[this.field_150863_d]);
				this.field_150868_h = this.func_150853_a(this.field_150861_f.posX, this.field_150861_f.posY, this.field_150861_f.posZ, this.field_150866_c);

				if (this.field_150868_h < 2 || this.field_150868_h > 21)
				{
					this.field_150861_f = null;
					this.field_150868_h = 0;
				}
			}

			if (this.field_150861_f != null)
			{
				this.field_150862_g = this.func_150858_a();
			}
		}

		protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_)
		{
			int j1 = Direction.offsetX[p_150853_4_];
			int k1 = Direction.offsetZ[p_150853_4_];
			int i1;
			Block block;

			for (i1 = 0; i1 < 22; ++i1)
			{
				block = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);

				if (!this.func_150857_a(block))
				{
					break;
				}

				Block block1 = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);

				if (block1 != mod_PacasStuff.RedlandsPortalFrame) //FRAME
				{
					break;
				}
			}

			block = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
			return block == mod_PacasStuff.RedlandsPortalFrame ? i1 : 0;  //FRAME
		}

		protected int func_150858_a()
		{
			int i;
			int j;
			int k;
			int l;
			label56:

				for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g)
				{
					i = this.field_150861_f.posY + this.field_150862_g;

					for (j = 0; j < this.field_150868_h; ++j)
					{
						k = this.field_150861_f.posX + j * Direction.offsetX[BlockRedlandsPortal.field_150001_a[this.field_150865_b][1]];
						l = this.field_150861_f.posZ + j * Direction.offsetZ[BlockRedlandsPortal.field_150001_a[this.field_150865_b][1]];
						Block block = this.field_150867_a.getBlock(k, i, l);

						if (!this.func_150857_a(block))
						{
							break label56;
						}

						if (block == mod_PacasStuff.RedlandsPortal)  //PORTAL
						{
							++this.field_150864_e;
						}

						if (j == 0)
						{
							block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockRedlandsPortal.field_150001_a[this.field_150865_b][0]], i, l + Direction.offsetZ[BlockRedlandsPortal.field_150001_a[this.field_150865_b][0]]);

							if (block != mod_PacasStuff.RedlandsPortalFrame)  //FRAME
							{
								break label56;
							}
						}
						else if (j == this.field_150868_h - 1)
						{
							block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockRedlandsPortal.field_150001_a[this.field_150865_b][1]], i, l + Direction.offsetZ[BlockRedlandsPortal.field_150001_a[this.field_150865_b][1]]);

							if (block != mod_PacasStuff.RedlandsPortalFrame)  //FRAME
							{
								break label56;
							}
						}
					}
				}

			for (i = 0; i < this.field_150868_h; ++i)
			{
				j = this.field_150861_f.posX + i * Direction.offsetX[BlockRedlandsPortal.field_150001_a[this.field_150865_b][1]];
				k = this.field_150861_f.posY + this.field_150862_g;
				l = this.field_150861_f.posZ + i * Direction.offsetZ[BlockRedlandsPortal.field_150001_a[this.field_150865_b][1]];

				if (this.field_150867_a.getBlock(j, k, l) != mod_PacasStuff.RedlandsPortalFrame)  //FRAME
				{
					this.field_150862_g = 0;
					break;
				}
			}

			if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
			{
				return this.field_150862_g;
			}
			else
			{
				this.field_150861_f = null;
				this.field_150868_h = 0;
				this.field_150862_g = 0;
				return 0;
			}
		}

		protected boolean func_150857_a(Block p_150857_1_)
		{
			return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == mod_PacasStuff.PortalFire || p_150857_1_ == mod_PacasStuff.RedlandsPortal; 
		}

		public boolean func_150860_b()
		{
			return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
		}

		public void func_150859_c()
		{
			for (int i = 0; i < this.field_150868_h; ++i)
			{
				int j = this.field_150861_f.posX + Direction.offsetX[this.field_150866_c] * i;
				int k = this.field_150861_f.posZ + Direction.offsetZ[this.field_150866_c] * i;

				for (int l = 0; l < this.field_150862_g; ++l)
				{
					int i1 = this.field_150861_f.posY + l;
					this.field_150867_a.setBlock(j, i1, k, mod_PacasStuff.RedlandsPortal, this.field_150865_b, 2);  //FRAME
				}
			}
		}
	}

}
