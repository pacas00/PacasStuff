package net.petercashel.PacasStuff;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.petercashel.PacasStuff.BlockClock.ItemRendererBlockClock;
import net.petercashel.PacasStuff.BlockClock.TileEntityBlockClock;
import net.petercashel.PacasStuff.BlockClock.TileEntityBlockClockRenderer;
import net.petercashel.PacasStuff.DIM_Common.Render.Redlands_Grass_Renderer;
import net.petercashel.PacasStuff.anvil.ItemPacasAnvilRenderer_Basic;
import net.petercashel.PacasStuff.anvil.TileEntityPacasAnvilRenderer_Basic;
import net.petercashel.PacasStuff.anvil.TileEntityPacasAnvil_Basic;
import net.petercashel.PacasStuff.anvil.AE_Enabled.ItemPacasAnvilRenderer;
import net.petercashel.PacasStuff.anvil.AE_Enabled.TileEntityPacasAnvil;
import net.petercashel.PacasStuff.anvil.AE_Enabled.TileEntityPacasAnvilRenderer;
import net.petercashel.PacasStuff.pacChest.TileEntityPacChest;
import net.petercashel.PacasStuff.pacChest2.TileEntityPacChest2;
import net.petercashel.PacasStuff.pacChest2.TileEntityPacChest2Renderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
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
		mod_PacasStuff.Redlands_GrassRendererID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new Redlands_Grass_Renderer());

	}


	@Override
	public void registerTileEntities() {

		TileEntitySpecialRenderer render_Chest = new net.petercashel.PacasStuff.pacChest.TileEntityPacChestRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(net.petercashel.PacasStuff.pacChest.TileEntityPacChest.class, render_Chest);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_PacasStuff.PacasOreChest), new ItemPacasAnvilRenderer_Basic(render_Chest, new TileEntityPacChest()));
		
		TileEntitySpecialRenderer render_Chest2 = new TileEntityPacChest2Renderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPacChest2.class, render_Chest2);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_PacasStuff.PacasOreChest2), new ItemPacasAnvilRenderer_Basic(render_Chest2, new TileEntityPacChest2()));
				
		TileEntitySpecialRenderer render_Basic = new TileEntityPacasAnvilRenderer_Basic();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPacasAnvil_Basic.class, render_Basic);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_PacasStuff.PacasAnvil_Basic), new ItemPacasAnvilRenderer_Basic(render_Basic, new TileEntityPacasAnvil_Basic()));

		if (Loader.isModLoaded("appliedenergistics2")) {
			TileEntitySpecialRenderer render = new TileEntityPacasAnvilRenderer();
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPacasAnvil.class, render);
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_PacasStuff.PacasAnvil), new ItemPacasAnvilRenderer(render, new TileEntityPacasAnvil()));

		}
		
		TileEntitySpecialRenderer blockClock = new TileEntityBlockClockRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockClock.class, blockClock);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(mod_PacasStuff.BlockClock), new ItemRendererBlockClock(blockClock, new TileEntityBlockClock()));

	}
}
