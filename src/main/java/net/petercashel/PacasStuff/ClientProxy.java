package net.petercashel.PacasStuff;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.petercashel.PacasStuff.pacChest2.TileEntityPacChest2;
import net.petercashel.PacasStuff.pacChest2.TileEntityPacChest2Renderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;

public class ClientProxy extends CommonProxy{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}
	
	public void load(){
	}
	
	
	@Override
	public void prepareTileEntityInformation()
	{
	mod_PacasStuff.PacChestBlockRendererID = RenderingRegistry.getNextAvailableRenderId();
	}


	@Override
	public void initRenderingAndTextures() {
	mod_PacasStuff.PacChestBlockRendererID = RenderingRegistry.getNextAvailableRenderId();

	}


	@Override
	public void registerTileEntities() {

	ClientRegistry.bindTileEntitySpecialRenderer(net.petercashel.PacasStuff.pacChest.TileEntityPacChest.class, new net.petercashel.PacasStuff.pacChest.TileEntityPacChestRenderer());
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPacChest2.class, new TileEntityPacChest2Renderer());
	}
}
