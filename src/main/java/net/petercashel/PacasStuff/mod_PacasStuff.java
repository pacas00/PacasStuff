package net.petercashel.PacasStuff;

import org.apache.logging.log4j.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = "mod_pacasstuff", name = "PacasStuff")
public class mod_PacasStuff {
	
	@Instance("mod_PacasStuff")
	public static mod_PacasStuff instance;
	
	@SidedProxy(clientSide = "net.petercashel.PacasStuff.ClientProxy", serverSide = "net.petercashel.PacasStuff.CommonProxy")
	public static CommonProxy proxy;

	
	static int tabID = CreativeTabs.getNextID();
	public static String PacasStuff = "PacasStuff";
	public static CreativeTabs tabPacasStuff = new CreativeTabPacasStuff(tabID, "PacasStuff");
	
	public static final String CATEGORY_GENERAL = "general";
	
	@EventHandler
	public void load(FMLInitializationEvent event) 
	{
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
        MinecraftForge.EVENT_BUS.register(new CapeEventHandler());
		

	}

	@EventHandler
	public void init(FMLInitializationEvent event){

	}


}