package net.petercashel.PacasStuff.DIM_Common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class ItemFireTool extends Item {
	
	public ItemFireTool()
	{
		super();
		this.maxStackSize = 1;
		setMaxDamage(64);
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
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
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	return par1ItemStack;
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
		{
			par5--;
		}
		if (par7 == 1)
		{
			par5++;
		}
		if (par7 == 2)
		{
			par6--;
		}
		if (par7 == 3)
		{
			par6++;
		}
		if (par7 == 4)
		{
			par4--;
		}
		if (par7 == 5)
		{
			par4++;
		}
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		Block i1 = par3World.getBlock(par4, par5, par6);
		if (i1.isAir(par3World, par4, par5, par6))
		{
			par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			/** replace with your fire block **/
			par3World.setBlock(par4, par5, par6, mod_PacasStuff.PortalFire);
		}
		par1ItemStack.damageItem(1, par2EntityPlayer);
		return true;
	}

}
