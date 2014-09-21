package net.petercashel.PacasStuff.BlockClock;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class BlockClock extends BlockContainer {

	public BlockClock(Material p_i45386_1_) {
		super(p_i45386_1_);
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileEntityBlockClock();
	}

	public void registerBlockIcons(IIconRegister iconRegister)
    {
		this.blockIcon = iconRegister.registerIcon("stonebrick_carved");
    }
	
	public int getRenderType()
	{
		return -1;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
