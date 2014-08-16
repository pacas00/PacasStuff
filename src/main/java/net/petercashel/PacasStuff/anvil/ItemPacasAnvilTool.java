package net.petercashel.PacasStuff.anvil;



import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.*;
import net.petercashel.PacasStuff.mod_PacasStuff;


public class ItemPacasAnvilTool extends Item
{
	public IIcon itemIconMeta1;
	public IIcon itemIconMeta2;

	public ItemPacasAnvilTool()
	{
		super();
		this.setCreativeTab(mod_PacasStuff.tabPacasStuff);
	}

	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("pacas_stuff:" + "AnvilConfig0");
		itemIconMeta1 = iconRegister.registerIcon("pacas_stuff:" + "AnvilConfig1");
		itemIconMeta2 = iconRegister.registerIcon("pacas_stuff:" + "AnvilConfig2");
	}

	@SideOnly(Side.CLIENT)

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(ItemStack stack, int pass)
    {
    	if (stack.getItemDamage() == 1) {
			return itemIconMeta1;
		} else if (stack.getItemDamage() == 2) {
			return itemIconMeta2;
		} else {
			return itemIcon;
		}
    }
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if (par3EntityPlayer.isSneaking()) {
    		if (par1ItemStack.getItemDamage() == 1) {
    			par1ItemStack.setItemDamage(2);
    		} else {
    			par1ItemStack.setItemDamage(1);
    		}
    	}
        return par1ItemStack;
    }
}
