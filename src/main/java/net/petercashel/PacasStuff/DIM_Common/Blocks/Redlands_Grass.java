package net.petercashel.PacasStuff.DIM_Common.Blocks;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.petercashel.PacasStuff.mod_PacasStuff;
import net.petercashel.PacasStuff.DIM_Common.Render.Redlands_Grass_Renderer;

//Clone of BlockGrass
public class Redlands_Grass extends Block implements IGrowable{

	private static final Logger logger = LogManager.getLogger();
	@SideOnly(Side.CLIENT)
	public static IIcon field_149991_b;
	@SideOnly(Side.CLIENT)
	public static IIcon field_149993_M;
	@SideOnly(Side.CLIENT)
	public static IIcon field_149994_N;

	public Redlands_Grass()
	{
		super(Material.grass);
		this.setTickRandomly(true);
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return p_149691_1_ == 1 ? this.field_149991_b : (p_149691_1_ == 0 ? ((Redlands_Dirt)mod_PacasStuff.Redlands_Dirt).getBlockTextureFromSide(p_149691_1_) : this.blockIcon);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		//From BlockGrass
		if (!world.isRemote)
		{
			if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
			{
				world.setBlock(x, y, z, ((Redlands_Dirt)mod_PacasStuff.Redlands_Dirt));
			}
			else if (world.getBlockLightValue(x, y + 1, z) >= 9)
			{
				for (int l = 0; l < 4; ++l)
				{
					int i1 = x + rand.nextInt(3) - 1;
					int j1 = y + rand.nextInt(5) - 3;
					int k1 = z + rand.nextInt(3) - 1;
					Block block = world.getBlock(i1, j1 + 1, k1);

					if (world.getBlock(i1, j1, k1) == ((Redlands_Dirt)mod_PacasStuff.Redlands_Dirt) && world.getBlockMetadata(i1, j1, k1) == 0 && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
					{
						world.setBlock(i1, j1, k1, ((Redlands_Grass)mod_PacasStuff.Redlands_Grass));
					}
					
					if (world.getBlock(i1, j1, k1) == (Blocks.dirt) && world.getBlockMetadata(i1, j1, k1) == 0 && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
					{
						world.setBlock(i1, j1, k1, (Blocks.grass));
					}
				}
			}
		}
		
		//From BlockFarmland
        if (!this.func_149821_m(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z))
        {
            int l = world.getBlockMetadata(x, y, z);

            if (l > 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, l - 1, 2);
            }
        }
        else
        {
            world.setBlockMetadataWithNotify(x, y, z, 7, 2);
        }
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return ((Redlands_Dirt)mod_PacasStuff.Redlands_Dirt).getItemDropped(0, p_149650_2_, p_149650_3_);
	}

	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
	{
		return true;
	}

	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
	{
		if (p_149673_5_ == 1)
		{
			return this.field_149991_b;
		}
		else if (p_149673_5_ == 0)
		{
			return ((Redlands_Dirt)mod_PacasStuff.Redlands_Dirt).getBlockTextureFromSide(p_149673_5_);
		}
		else
		{
			Material material = p_149673_1_.getBlock(p_149673_2_, p_149673_3_ + 1, p_149673_4_).getMaterial();
			return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.field_149993_M;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
		this.field_149991_b = p_149651_1_.registerIcon(this.getTextureName() + "_top");
		this.field_149993_M = p_149651_1_.registerIcon(this.getTextureName() + "_side_snowed");
		this.field_149994_N = p_149651_1_.registerIcon(this.getTextureName() + "_side_overlay");
	}

	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerGrass.getGrassColor(d0, d1);
	}

	/**
	 * Returns the color this block should be rendered. Used by leaves.
	 */
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int p_149741_1_)
	{
		return this.getBlockColor();
	}

	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
	 * when first determining what to render.
	 */
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		int l = 0;
		int i1 = 0;
		int j1 = 0;

		for (int k1 = -1; k1 <= 1; ++k1)
		{
			for (int l1 = -1; l1 <= 1; ++l1)
			{
				int i2 = p_149720_1_.getBiomeGenForCoords(p_149720_2_ + l1, p_149720_4_ + k1).getBiomeGrassColor(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
				l += (i2 & 16711680) >> 16;
			i1 += (i2 & 65280) >> 8;
					j1 += i2 & 255;
			}
		}

		return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
	}

	@SideOnly(Side.CLIENT)
	public static IIcon getIconSideOverlay()
	{
		return ((Redlands_Grass)mod_PacasStuff.Redlands_Grass).field_149994_N;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return mod_PacasStuff.Redlands_GrassRendererID;
	}

	//From BlockGrass
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
	{
		int l = 0;

		while (l < 128)
		{
			int i1 = p_149853_3_;
			int j1 = p_149853_4_ + 1;
			int k1 = p_149853_5_;
			int l1 = 0;

			while (true)
			{
				if (l1 < l / 16)
				{
					i1 += p_149853_2_.nextInt(3) - 1;
					j1 += (p_149853_2_.nextInt(3) - 1) * p_149853_2_.nextInt(3) / 2;
					k1 += p_149853_2_.nextInt(3) - 1;

					if (p_149853_1_.getBlock(i1, j1 - 1, k1) == mod_PacasStuff.Redlands_Grass && !p_149853_1_.getBlock(i1, j1, k1).isNormalCube())
					{
						++l1;
						continue;
					}
				}
				else if (p_149853_1_.getBlock(i1, j1, k1).getMaterial() == Material.air)
				{
					if (p_149853_2_.nextInt(8) != 0)
					{
						if (Blocks.tallgrass.canBlockStay(p_149853_1_, i1, j1, k1))
						{
							p_149853_1_.setBlock(i1, j1, k1, Blocks.tallgrass, 1, 3);
						}
					}
					else
					{
						p_149853_1_.getBiomeGenForCoords(i1, k1).plantFlower(p_149853_1_, p_149853_2_, i1, j1, k1);
					}
				}

				++l;
				break;
			}
		}
	}

	// From Block
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		Block plant = plantable.getPlant(world, x, y + 1, z);
		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

		switch (plantType)
		{
		case Desert: return this == mod_PacasStuff.Redlands_Grass; //Sand, see beach for fix
        case Crop:   return this == mod_PacasStuff.Redlands_Grass; //Farmland
        case Plains: return this == mod_PacasStuff.Redlands_Grass || this == mod_PacasStuff.Redlands_Dirt || this == Blocks.farmland;
        case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
        case Beach:
            boolean isBeach = this == mod_PacasStuff.Redlands_Grass || this == mod_PacasStuff.Redlands_Dirt || this.isEqualTo(this, Blocks.sand) ;
            boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                                world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                                world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                                world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
            return isBeach && hasWater;
		}

		return super.canSustainPlant(world, x, y, z, direction, plantable);
	}
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	if(!p_149727_1_.isRemote) {
    		if (p_149727_5_ instanceof EntityPlayerMP) {
    			EntityPlayerMP plr = ((EntityPlayerMP)p_149727_5_);
    			if (plr.inventory.getCurrentItem() != null) {
    				ItemStack st = plr.inventory.getCurrentItem();
    				if (st.getItem() instanceof ItemHoe) {
    					p_149727_1_.setBlock(p_149727_2_, p_149727_3_, p_149727_4_, Blocks.farmland);
    					p_149727_1_.playSoundEffect((double)((float)p_149727_7_ + 0.5F), (double)((float)p_149727_8_ + 0.5F), (double)((float)p_149727_9_ + 0.5F), this.stepSound.getStepResourcePath(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
    					return true;
    				}
    			}
    			
    		}
    	}    	
        return false;   	
    }
    
    
    
    // from farmland
    private boolean func_149822_e(World world, int x, int y, int z)
    {
        byte b0 = 0;

        for (int l = x - b0; l <= x + b0; ++l)
        {
            for (int i1 = z - b0; i1 <= z + b0; ++i1)
            {
                Block block = world.getBlock(l, y + 1, i1);

                if (block instanceof IPlantable && canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable)block))
                {
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean func_149821_m(World world, int x, int y, int z)
    {
        for (int l = x - 4; l <= x + 4; ++l)
        {
            for (int i1 = y; i1 <= y + 1; ++i1)
            {
                for (int j1 = z - 4; j1 <= z + 4; ++j1)
                {
                    if (world.getBlock(l, i1, j1).getMaterial() == Material.water)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
