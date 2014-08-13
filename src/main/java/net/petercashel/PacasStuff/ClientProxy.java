package net.petercashel.PacasStuff;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.petercashel.PacasStuff.anvil.TileEntityPacasAnvil;
import net.petercashel.PacasStuff.anvil.TileEntityPacasAnvilRenderer;
import net.petercashel.PacasStuff.pacChest.ItemPacasAnvilRenderer;
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
	
	TileEntitySpecialRenderer render = new TileEntityPacasAnvilRenderer();
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPacasAnvil.class, render);
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_PacasStuff.PacasAnvil), new ItemPacasAnvilRenderer(render, new TileEntityPacasAnvil()));
	}
}
