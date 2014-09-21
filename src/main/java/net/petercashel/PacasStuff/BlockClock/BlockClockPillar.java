package net.petercashel.PacasStuff.BlockClock;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class BlockClockPillar extends Block {
	public BlockClockPillar(Material par1) {
		super(par1);
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
		float var5 = 0.2F;
		this.setBlockBounds(var5, 0.0F, var5, 1.0F - var5, 1.0F, 1.0F - var5);
	}

	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("quartz_block_side");
	}
	private boolean isFiring = false;

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}	

	private int whichSidePowered(World par1World, int par1, int par2, int par3) {
		if (par1World.getIndirectPowerLevelTo(par1, par2 - 1, par3, 0) == 1) {
			return 0;
		}
		;
		if (par1World.getIndirectPowerOutput(par1, par2 + 1, par3, 1) == true) {
			return 1;
		}
		;
		if (par1World.getIndirectPowerOutput(par1, par2, par3 - 1, 2) == true) {
			return 2;
		}
		;
		if (par1World.getIndirectPowerOutput(par1, par2, par3 + 1, 3) == true) {
			return 3;
		}
		;
		if (par1World.getIndirectPowerOutput(par1 - 1, par2, par3, 4) == true) {
			return 4;
		}
		;
		if (par1World.getIndirectPowerOutput(par1 + 1, par2, par3, 5) == true) {
			return 5;
		}
		;
		return 0;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, Block par5) {
		if (!par1World.isRemote) {
			this.updateState(par1World, par2, par3, par4);
		}
	}

	private void updateState(World par1World, int par2, int par3, int par4) {
		if (!isFiring) {

			System.out.println("Updating State");
			if (!par1World.isRemote) {
				Block varblock = par1World.getBlock(par2, par3 + 1, par4);
				if (varblock.isEqualTo(varblock, mod_PacasStuff.BlockClock)) {

					int var5 = par1World.getBlockMetadata(par2, par3, par4);
					int var1 = this.whichSidePowered(par1World, par2, par3, par4);
					int var2 = 1;

					System.out.println("Ive run whichSidePowered and returned " + var1);
					if (var1 == 2) {
						var2 = 6000;
					}
					if (var1 == 3) {
						var2 = 18000;
					}
					if (var1 == 4) {
						var2 = 12000;
					}
					if (var1 == 5) {
						var2 = 0;
					}
					if (var1 == 6) {
						var2 = 1;
					}
					if (var1 == 1) {
						var2 = 1;
					}
					if (var1 == 0) {
						var2 = 1;
					}
					if (var2 != 1 && var2 != par1World.getWorldTime() && var1 != 0) {
						String timename = "";
						if (var1 == 2) {
							timename = "Midday";
						}
						if (var1 == 3) {
							timename = "Midnight";
						}
						if (var1 == 4) {
							timename = "Evening";
						}
						if (var1 == 5) {
							timename = "Morning";
						}
						MinecraftServer.getServer().getConfigurationManager().sendChatMsg(
								(new ChatComponentText(
										"Server: The time is now " + timename + "." )));
						for (int var3 = 0; var3 < MinecraftServer.getServer().worldServers.length; ++var3) {
							WorldServer var4 = MinecraftServer.getServer().worldServers[var3];
							if (par1World.isRemote = false) {
								par1World.setWorldTime((long) var2);
								var4.setWorldTime((long) var2);
							} else {
								par1World.setWorldTime((long) var2);
								var4.setWorldTime((long) var2);
							}
						}
					}

				}
			}
		}

		this.isFiring = false;

	}

	/**
	 * Determine if this block can make a redstone connection on the side provided,
	 * Useful to control which sides are inputs and outputs for redstone wires.
	 *
	 * Side:
	 *  -1: UP
	 *   0: NORTH
	 *   1: EAST
	 *   2: SOUTH
	 *   3: WEST
	 *
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z Position
	 * @param side The side that is trying to make the connection
	 * @return True to make the connection
	 */
	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
	{
		/**
		 * Can this block provide power. Only wire currently seems to have this change based on its state.
		 */
		return true;
	}

	/**
	 * Determines if a torch can be placed on the top surface of this block.
	 * Useful for creating your own block that torches can be on, such as fences.
	 *
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z Position
	 * @return True to allow the torch to be placed
	 */
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
	{
		return true;
	}

	/**
	 * Returns true if redstone wire can connect to the specified block. Params: World, X, Y, Z, side (not a normal
	 * notch-side, this can be 0, 1, 2, 3 or -1)
	 */
	public static boolean isPowerProviderOrWire(IBlockAccess p_150174_0_, int p_150174_1_, int p_150174_2_, int p_150174_3_, int p_150174_4_)
	{
		return true;
	}

}
