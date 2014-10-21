package net.petercashel.PacasStuff.DIM_Common.Blocks;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import appeng.items.tools.quartz.ToolQuartzHoe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class Redlands_Dirt extends Block {
	public static final String[] field_150009_a = new String[] {"default", "default", "podzol"};
    
	public Redlands_Dirt()
    {
		super(Material.ground);
        this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
    }
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.blockIcon;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
    {
        int i1 = p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_, p_149673_4_);

        if (i1 == 2)
        {
            if (p_149673_5_ != 0)
            {
                Material material = p_149673_1_.getBlock(p_149673_2_, p_149673_3_ + 1, p_149673_4_).getMaterial();

                if (material == Material.snow || material == Material.craftedSnow)
                {
                    return mod_PacasStuff.Redlands_Grass.getIcon(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, p_149673_5_);
                }
            }
        }

        return this.blockIcon;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int p_149644_1_)
    {
        if (p_149644_1_ == 1)
        {
            p_149644_1_ = 0;
        }

        return super.createStackedBlock(p_149644_1_);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(this, 1, 0));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        super.registerBlockIcons(p_149651_1_);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        int l = p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_);

        if (l == 1)
        {
            l = 0;
        }

        return l;
    }
    
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		Block plant = plantable.getPlant(world, x, y + 1, z);
		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

		switch (plantType)
		{
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

	public void onPlantGrow(World world, int x, int y, int z, int sourceX,
			int sourceY, int sourceZ) {
		world.setBlock(x, y, z, mod_PacasStuff.Redlands_Grass, 0, 2);
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
    
    
}
