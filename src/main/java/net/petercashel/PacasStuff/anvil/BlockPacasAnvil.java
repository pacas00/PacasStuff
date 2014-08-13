package net.petercashel.PacasStuff.anvil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class BlockPacasAnvil extends BlockContainer {

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


}
