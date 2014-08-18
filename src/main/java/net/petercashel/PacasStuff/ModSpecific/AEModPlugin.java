package net.petercashel.PacasStuff.ModSpecific;

import appeng.api.util.AEColor;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import net.petercashel.PacasStuff.mod_PacasStuff;

public class AEModPlugin {

	public static void blocks() { // runs on pre-init. move to init?
		mod_PacasStuff.PacasAnvil = new net.petercashel.PacasStuff.anvil.AE_Enabled.BlockPacasAnvil().setBlockName("PacasAnvil").setBlockTextureName("PacasAnvil").setHardness(3.0F).setResistance(5.0F);
    	GameRegistry.registerBlock(mod_PacasStuff.PacasAnvil, ItemBlock.class, "BlockPacasAnvil");
    	GameRegistry.registerTileEntity(net.petercashel.PacasStuff.anvil.AE_Enabled.TileEntityPacasAnvil.class, "TileEntityPacasAnvil");
		
	}
	public static void recipes() { // Done on post-init
		GameRegistry.addRecipe(new ItemStack(mod_PacasStuff.PacasAnvil, 1), new Object[] {
			" f ", "qaq", " c ",
			Character.valueOf('f'), appeng.api.AEApi.instance().materials().materialFormationCore.stack(1),
			Character.valueOf('q'), appeng.api.AEApi.instance().materials().materialFluixCrystal.stack(1),
			Character.valueOf('a'), mod_PacasStuff.PacasAnvil_Basic,
			Character.valueOf('c'), appeng.api.AEApi.instance().parts().partCableGlass.stack(AEColor.Transparent, 1)
			});
	}

	

}
