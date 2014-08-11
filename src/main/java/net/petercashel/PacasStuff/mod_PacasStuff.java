package net.petercashel.PacasStuff;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = "mod_pacasstuff", name = "PacasStuff")
public class mod_PacasStuff {
	
	@Instance("mod_PacasStuff")
	public static mod_PacasStuff instance;
	
	@SidedProxy(clientSide = "net.petercashel.PacasStuff.ClientProxy", serverSide = "net.petercashel.PacasStuff.CommonProxy")
	public static CommonProxy proxy;

	
	static int tabID = CreativeTabs.getNextID();
	public static String PacasStuff = "PacasStuff";
	public static CreativeTabs tabPacasStuff = new CreativeTabPacasStuff(tabID, "PacasStuff");
	public static int PacChestBlockRendererID;
	public static Block PacasOreChest;
	public static Block PacasOreChest2;
    
	
	public static final String CATEGORY_GENERAL = "general";
	
	@EventHandler
	public void load(FMLInitializationEvent event) 
	{
		proxy.prepareTileEntityInformation();
		proxy.initRenderingAndTextures();
		proxy.registerTileEntities();
		System.out.println("[PacasStuff] Loaded.");
		FMLLog.log("PacasStuff", Level.INFO, "Mod Has Loaded [PacasStuff]");
	}


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();

			
		} catch (Exception e) {
			System.out.println("[PacasStuff] Error Loading Config");
		} finally {
			cfg.save();
		}
        PacasOreChest = new net.petercashel.PacasStuff.pacChest.BlockPacChest(0).setBlockName("PacasOreChest").setBlockTextureName("PacasOreChest").setHardness(3.0F).setResistance(5.0F);
    	GameRegistry.registerBlock(PacasOreChest, ItemBlock.class, "BlockPacasOreChest");
    	GameRegistry.registerTileEntity(net.petercashel.PacasStuff.pacChest.TileEntityPacChest.class, "TileEntityPacChest");
        
    	PacasOreChest2 = new net.petercashel.PacasStuff.pacChest2.BlockPacChest2(0).setBlockName("PacasOreChest2").setBlockTextureName("PacasOreChest2").setHardness(3.0F).setResistance(5.0F);
    	GameRegistry.registerBlock(PacasOreChest2, ItemBlock.class, "BlockPacasBottemlessChest");
    	GameRegistry.registerTileEntity(net.petercashel.PacasStuff.pacChest2.TileEntityPacChest2.class, "TileEntityPacChest2");
        
    	

	}

	@EventHandler
	public void init(FMLInitializationEvent event){

	}


}