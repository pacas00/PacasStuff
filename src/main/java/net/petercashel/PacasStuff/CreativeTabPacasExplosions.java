package net.petercashel.PacasStuff;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

final class CreativeTabPacasExplosions extends CreativeTabs
{
	CreativeTabPacasExplosions(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @Override
	public Item getTabIconItem() {
		return (new ItemStack(Blocks.sponge, 1)).getItem();
	}
    
    @SideOnly(Side.CLIENT)
    public String getTabLabel()
    {
        return "PacasExplosions";
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets the translated Label.
     */
    public String getTranslatedTabLabel()
    {
        return "PacasExplosions";
    }

	

}
