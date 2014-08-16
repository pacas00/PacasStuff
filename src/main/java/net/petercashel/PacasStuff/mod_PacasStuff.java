package net.petercashel.PacasStuff;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.petercashel.PacasStuff.anvil.BlockPacasAnvil_basic;
import net.petercashel.PacasStuff.anvil.anvilManager;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
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
	public static Block PacasAnvil;
	public static Block PacasAnvil_Basic;
	public static Item ItemPacasAnvilTool;

	public static boolean CompatEnable;
	public static boolean CompatIC2;
	public static boolean CompatTE4;
	public static boolean anvilBadItem;
	
	public static final String CATEGORY_GENERAL = "general";

	private net.petercashel.PacasStuff.anvil.AnvilCompatibility AnvilCompat;
	
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
			
			anvilBadItem = cfg.get(CATEGORY_GENERAL, "anvilInvalidItemChat", false).getBoolean(false);

			CompatEnable = cfg.get(CATEGORY_GENERAL, "CompatEnable", true).getBoolean(true);
			CompatIC2 = cfg.get(CATEGORY_GENERAL, "CompatIC2", true).getBoolean(true);
			CompatTE4 = cfg.get(CATEGORY_GENERAL, "CompatTE3", true).getBoolean(true);


			
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
        
    	PacasAnvil_Basic = new BlockPacasAnvil_basic().setBlockName("PacasAnvil_Basic").setBlockTextureName("PacasAnvil_Basic").setHardness(3.0F).setResistance(5.0F);
    	GameRegistry.registerBlock(PacasAnvil_Basic, ItemBlock.class, "BlockPacasAnvil_Basic");
    	GameRegistry.registerTileEntity(net.petercashel.PacasStuff.anvil.TileEntityPacasAnvil_Basic.class, "TileEntityPacasAnvil_Basic");
        
    	if (Loader.isModLoaded("appliedenergistics2")) {
    	PacasAnvil = new net.petercashel.PacasStuff.anvil.BlockPacasAnvil().setBlockName("PacasAnvil").setBlockTextureName("PacasAnvil").setHardness(3.0F).setResistance(5.0F);
    	GameRegistry.registerBlock(PacasAnvil, ItemBlock.class, "BlockPacasAnvil");
    	GameRegistry.registerTileEntity(net.petercashel.PacasStuff.anvil.TileEntityPacasAnvil.class, "TileEntityPacasAnvil");
    	}
    	
    	GameRegistry.addRecipe(new ItemStack(PacasAnvil, 1), new Object[] { "OIO", " i ", "iii", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('I'), Blocks.iron_block, Character.valueOf('i'), Items.iron_ingot });
    	
    	ItemPacasAnvilTool = new net.petercashel.PacasStuff.anvil.ItemPacasAnvilTool().setMaxStackSize(1).setUnlocalizedName("ItemPacasAnvilTool");
		GameRegistry.registerItem(ItemPacasAnvilTool, "ItemPacasAnvilTool");

		

	}

	@EventHandler
	public void init(FMLInitializationEvent event){

		anvilManager.Load();

		addToAnvilManager();
	}

	public void addToAnvilManager() {
		anvilManager.registerItem(new ItemStack(Items.diamond_sword), new ItemStack(Items.diamond));
		anvilManager.registerItem(new ItemStack(Items.diamond_pickaxe), new ItemStack(Items.diamond), 3);
		anvilManager.registerItem(new ItemStack(Items.diamond_shovel), new ItemStack(Items.diamond), 1);
		anvilManager.registerItem(new ItemStack(Items.diamond_hoe), new ItemStack(Items.diamond), 1);
		anvilManager.registerItem(new ItemStack(Items.diamond_axe), new ItemStack(Items.diamond), 3);

		anvilManager.registerItem(new ItemStack(Items.iron_sword), new ItemStack(Items.iron_ingot));
		anvilManager.registerItem(new ItemStack(Items.iron_pickaxe), new ItemStack(Items.iron_ingot), 3);
		anvilManager.registerItem(new ItemStack(Items.iron_shovel), new ItemStack(Items.iron_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.iron_hoe), new ItemStack(Items.iron_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.iron_axe), new ItemStack(Items.iron_ingot), 3);

		anvilManager.registerItem(new ItemStack(Items.golden_sword), new ItemStack(Items.gold_ingot));
		anvilManager.registerItem(new ItemStack(Items.golden_pickaxe), new ItemStack(Items.gold_ingot));
		anvilManager.registerItem(new ItemStack(Items.golden_shovel), new ItemStack(Items.gold_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.golden_hoe), new ItemStack(Items.gold_ingot), 1);
		anvilManager.registerItem(new ItemStack(Items.golden_axe), new ItemStack(Items.gold_ingot));

		anvilManager.registerItem(new ItemStack(Items.bow), new ItemStack(Items.stick));
		anvilManager.registerItem(new ItemStack(Items.fishing_rod), new ItemStack(Items.stick));

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		AnvilCompat = new net.petercashel.PacasStuff.anvil.AnvilCompatibility();
		if (CompatEnable) {
			AnvilCompat.init();
		}
		System.out.println("[PacasStuff] Anvil Compatiblity Checks Complete.");

	}
	
	

}