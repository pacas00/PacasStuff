package net.petercashel.PacasStuff;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.petercashel.PacasStuff.DIM_Common.BlockPortalFire;
import net.petercashel.PacasStuff.DIM_Redlands.BiomeGenRedlands;
import net.petercashel.PacasStuff.DIM_Redlands.BlockRedlandsPortal;
import net.petercashel.PacasStuff.DIM_Redlands.WorldProviderRedlands;
import net.petercashel.PacasStuff.DIM_WOP.BiomeGenWOP;
import net.petercashel.PacasStuff.DIM_WOP.BlockWOPPortal;
import net.petercashel.PacasStuff.DIM_WOP.WorldProviderWOP;
import net.petercashel.PacasStuff.DIM_Common.Blocks.*;
import net.petercashel.PacasStuff.ExplosiveBlocks.items.*;
import net.petercashel.PacasStuff.ExplosiveBlocks.blocks.*;
import net.petercashel.PacasStuff.ModSpecific.AEModPlugin;
import net.petercashel.PacasStuff.anvil.BlockPacasAnvil_basic;
import net.petercashel.PacasStuff.anvil.anvilManager;
import net.petercashel.PacasStuff.command.HQMEditToggle;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = mod_PacasStuff.MOD_ID, name = "PacasStuff", version = mod_PacasStuff.VERSION, acceptedMinecraftVersions = mod_PacasStuff.MINECRAFT_VERSION, dependencies = mod_PacasStuff.DEPENDENCIES)
public class mod_PacasStuff {
	
    public static final String DEPENDENCIES = "required-after:Forge@[10.13.0.1198,);after:appliedenergistics2";
	
	@Instance(value = "mod_PacasStuff")
	public static final String MOD_ID = "mod_pacasstuff";
	public static mod_PacasStuff instance;
	
	public static final String VERSION = "@VERSION@";
	public static final String MINECRAFT_VERSION = "[1.7.10]";
	
	@SidedProxy(clientSide = "net.petercashel.PacasStuff.ClientProxy", serverSide = "net.petercashel.PacasStuff.CommonProxy")
	public static CommonProxy proxy;

	
	static int tabID = CreativeTabs.getNextID();
	public static String PacasStuff = "PacasStuff";
	public static CreativeTabs tabPacasStuff = new CreativeTabPacasStuff(tabID, "PacasStuff");
	static int tabID2 = CreativeTabs.getNextID();
	public static String PacasExplosions = "PacasExplosions";
	public static CreativeTabs tabPacasExplosions = new CreativeTabPacasExplosions(tabID2, "PacasExplosions");
	public static int PacChestBlockRendererID;
	public static Block PacasOreChest;
	public static Block PacasOreChest2;
	public static Block PacasAnvil;
	public static Block PacasAnvil_Basic;
	public static Item ItemPacasAnvilTool;
	public static Item ItemFireTool;
	
	public static Block BlockClock;
	public static Block BlockClockPillar;

	public static boolean CompatEnable;
	public static boolean CompatIC2;
	public static boolean CompatTE4;
	public static boolean anvilBadItem;
	
	public static final String CATEGORY_GENERAL = "general";

	private net.petercashel.PacasStuff.anvil.AnvilCompatibility AnvilCompat;

	private MinecraftServer server;

	private HQMEditToggle HQMEditToggleCMD;
	
	/** Dimensions **/ // Defaults set here. weird is happening
	public static int DIM_ID_WOP;
	public static int DIM_ID_Redlands;
	
	/** Biomes **/
	public static int BiomeID_WOP;
	public static BiomeGenBase WOP = null;
	
	public static int BiomeID_Redlands;
	public static BiomeGenBase Redlands = null;
	
	public static int BiomeID_Redlands_Short;
	public static BiomeGenBase Redlands_Short = null;
	
	/** Mutated **/
	public static int BiomeID_WOPM;
	public static BiomeGenBase WOPM = null;
	
	public static int BiomeID_RedlandsM;
	public static BiomeGenBase RedlandsM = null;
	
	/** BiomeGen Blocks **/
	public static Block Redlands_Dirt;
	public static Block Redlands_Grass;

	/** Portal Related Blocks **/
	public static BlockWOPPortal WOPPortal;
	public static Block WOPPortalFrame = Blocks.emerald_block;
	public static BlockPortalFire PortalFire;

	public static BlockRedlandsPortal RedlandsPortal;
	public static Block RedlandsPortalFrame = Blocks.nether_brick;
	
	
	
	/** Explosive FUN! **/
	public static Item itemExplosivePlayerSelector;
	public static Block ExplosivePressurePlate;
	public static Block ExplosivePressurePlateWood;

	public static int Redlands_GrassRendererID;

	
	public void recipes() {
		
		GameRegistry.addRecipe(new ItemStack(PacasAnvil_Basic, 1), new Object[] { "OIO", "rIr", "IpI", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('I'), Blocks.iron_block, Character.valueOf('r'), Items.redstone, Character.valueOf('p'), Blocks.sticky_piston });
    	
		GameRegistry.addRecipe(new ItemStack(ItemFireTool, 1), new Object[] {
			" T ", "WNB", " R ",
			Character.valueOf('N'), Items.nether_star,
			Character.valueOf('W'), Blocks.planks,
			Character.valueOf('R'), Items.redstone,
			Character.valueOf('T'), Blocks.redstone_torch,
			Character.valueOf('B'), Blocks.stone_button
		});	
		
		GameRegistry.addRecipe(new ItemStack(BlockClock, 1), new Object[] {
			"QRQ", "GCG", "QRQ",
			Character.valueOf('Q'), Blocks.quartz_block,
			Character.valueOf('C'), Items.clock,
			Character.valueOf('R'), Items.redstone,
			Character.valueOf('G'), Items.gold_ingot
		});	
		
		GameRegistry.addRecipe(new ItemStack(BlockClockPillar, 3), new Object[] {
			"QRQ", "QWQ", "QRQ",
			Character.valueOf('Q'), Blocks.quartz_block,
			Character.valueOf('R'), Blocks.redstone_block,
			Character.valueOf('W'), Items.nether_star
		});	
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
			
			BiomeID_WOP = cfg.get(CATEGORY_GENERAL, "BiomeID_WOP", 200).getInt(200);
			BiomeID_WOPM = cfg.get(CATEGORY_GENERAL, "BiomeID_WOPM", 201).getInt(201);
			WOP = new BiomeGenWOP(BiomeID_WOP).setBiomeName("WOP").setColor(0x66AD2D).setHeight(BiomeGenWOP.height_WOP);
			WOPM = new BiomeGenMutated(BiomeID_WOPM, WOP).setBiomeName("WOPM");
			
			BiomeID_Redlands = cfg.get(CATEGORY_GENERAL, "BiomeID_Redlands", 202).getInt(202);
			BiomeID_Redlands_Short = cfg.get(CATEGORY_GENERAL, "BiomeID_Redlands_Short", 203).getInt(203);
			BiomeID_RedlandsM = cfg.get(CATEGORY_GENERAL, "BiomeID_RedlandsM", 204).getInt(204);
			Redlands = new BiomeGenRedlands(BiomeID_Redlands).setBiomeName("Redlands").setHeight(BiomeGenRedlands.height_Redlands);
			Redlands_Short = new BiomeGenRedlands(BiomeID_Redlands_Short).setBiomeName("Redlands Low").setHeight(BiomeGenRedlands.height_Redlands_Short);
			RedlandsM = new BiomeGenMutated(BiomeID_RedlandsM, Redlands).setBiomeName("RedlandsM");
			
			
			DIM_ID_WOP = cfg.get(CATEGORY_GENERAL, "DIM_ID_WOP", -110).getInt(-110);
			DIM_ID_Redlands = cfg.get(CATEGORY_GENERAL, "DIM_ID_Redlands", -111).getInt(-111);


			
		} catch (Exception e) {
			System.out.println("[PacasStuff] Error Loading Config");
		} finally {
			cfg.save();
		}
		
		BiomeManager.addVillageBiome(this.WOP, true);
		BiomeManager.addVillageBiome(this.WOPM, true);
		BiomeManager.addVillageBiome(this.Redlands, true);
		BiomeManager.addVillageBiome(this.RedlandsM, true);
				
		/**Register WorldProvider for Dimension **/
		DimensionManager.registerProviderType(this.DIM_ID_WOP, WorldProviderWOP.class, false);
		DimensionManager.registerDimension(this.DIM_ID_WOP, this.DIM_ID_WOP);
		
		DimensionManager.registerProviderType(this.DIM_ID_Redlands, WorldProviderRedlands.class, false);
		DimensionManager.registerDimension(this.DIM_ID_Redlands, this.DIM_ID_Redlands);
		
		Redlands_Dirt = (new Redlands_Dirt()).setHardness(0.5F).setStepSound(Block.soundTypeGravel).setBlockName("dirt").setBlockTextureName("pacas_stuff" + ":" + "dirt");
		GameRegistry.registerBlock(Redlands_Dirt, "Redlands_Dirt");
		
		Redlands_Grass = (new Redlands_Grass()).setHardness(0.6F).setStepSound(Block.soundTypeGrass).setBlockName("grass").setBlockTextureName("pacas_stuff" + ":" + "grass");
		GameRegistry.registerBlock(Redlands_Grass, "Redlands_Grass");

	}

	@EventHandler
	public void init(FMLInitializationEvent event){

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
    		AEModPlugin.blocks();
    	}
    	
    	ItemPacasAnvilTool = new net.petercashel.PacasStuff.anvil.ItemPacasAnvilTool().setMaxStackSize(1).setUnlocalizedName("ItemPacasAnvilTool");
		GameRegistry.registerItem(ItemPacasAnvilTool, "ItemPacasAnvilTool");
		
		ItemFireTool = new net.petercashel.PacasStuff.DIM_Common.ItemFireTool().setMaxStackSize(1).setUnlocalizedName("ItemFireTool");
		GameRegistry.registerItem(ItemFireTool, "ItemFireTool");

		WOPPortal = new BlockWOPPortal("WOPPortal");
		GameRegistry.registerBlock(WOPPortal, "WOPPortal");
		
		PortalFire = new BlockPortalFire("WOPPortalFire");
		GameRegistry.registerBlock(PortalFire, "WOPPortalFire");
		
		RedlandsPortal = new BlockRedlandsPortal("RedlandsPortal");
		GameRegistry.registerBlock(RedlandsPortal, "RedlandsPortal");
		
		BlockClockPillar = new net.petercashel.PacasStuff.BlockClock.BlockClockPillar(Material.rock).setBlockTextureName("BlockClockPillar").setBlockName("BlockClockPillar");
		GameRegistry.registerBlock(BlockClockPillar, "BlockClockPillar");
		
		BlockClock = new net.petercashel.PacasStuff.BlockClock.BlockClock(Material.iron).setBlockTextureName("BlockClock").setBlockName("BlockClock");
		GameRegistry.registerBlock(BlockClock, "BlockClock");
		GameRegistry.registerTileEntity(net.petercashel.PacasStuff.BlockClock.TileEntityBlockClock.class, "TileEntityBlockClock");
		
		itemExplosivePlayerSelector = new ItemExplosivePlayerSelector().setMaxStackSize(1).setUnlocalizedName("ItemExplosivePlayerSelector");
		GameRegistry.registerItem(itemExplosivePlayerSelector, "ItemExplosivePlayerSelector");
		
		ExplosivePressurePlate = new ExplosivePressurePlate("stone", Material.rock, BlockPressurePlate.Sensitivity.players).setHardness(50.0F).setStepSound(Block.soundTypePiston).setBlockName("pressurePlate");
		GameRegistry.registerBlock(ExplosivePressurePlate, "ExplosivePressurePlate");
		ExplosivePressurePlateWood = new ExplosivePressurePlate("planks_oak", Material.wood, BlockPressurePlate.Sensitivity.players).setHardness(50.0F).setStepSound(Block.soundTypePiston).setBlockName("pressurePlate");
		GameRegistry.registerBlock(ExplosivePressurePlateWood, "ExplosivePressurePlateWood");
		
		anvilManager.Load();

		addToAnvilManager();
		
		proxy.prepareTileEntityInformation();
		proxy.initRenderingAndTextures();
		proxy.registerTileEntities();
		System.out.println("[PacasStuff] Loaded.");
		FMLLog.log("PacasStuff", Level.INFO, "Mod Has Loaded [PacasStuff]");
		
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
		recipes();
		if (Loader.isModLoaded("appliedenergistics2")) {
    		AEModPlugin.recipes();
    	}
		System.out.println("[PacasStuff] Anvil Compatiblity Checks Complete.");
		
		HashMap<String, Fluid> map = Maps.newHashMap();
		map.putAll(FluidRegistry.getRegisteredFluids());
		Iterator i = map.keySet().iterator();
		while (i.hasNext()) {
			String str = (String) i.next();
			System.out.println(str);
			i.remove();
		}
	}
	
	@EventHandler
	public void ServerStarting(FMLServerStartingEvent event) 
	{
		server = MinecraftServer.getServer();
		ServerCommandManager commands = (ServerCommandManager) server.getCommandManager();
		HQMEditToggleCMD = new HQMEditToggle();
		commands.registerCommand(HQMEditToggleCMD);
	}
}