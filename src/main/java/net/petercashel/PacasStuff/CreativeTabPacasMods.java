package net.petercashel.PacasStuff;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

final class CreativeTabPacasStuff extends CreativeTabs
{
    CreativeTabPacasStuff(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @Override
	public Item getTabIconItem() {
		return (new ItemStack(mod_PacasStuff.PacasAnvil_Basic, 1)).getItem();
	}
    
    @SideOnly(Side.CLIENT)
    public String getTabLabel()
    {
        return "PacasStuff";
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets the translated Label.
     */
    public String getTranslatedTabLabel()
    {
        return "PacasStuff";
    }

	

}
