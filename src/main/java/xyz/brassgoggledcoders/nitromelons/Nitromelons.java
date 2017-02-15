package xyz.brassgoggledcoders.nitromelons;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid=Nitromelons.MODID, name=Nitromelons.NAME, version="@VERSION@")
public class Nitromelons {
	
	public static final String MODID = "nitromelons";
	public static final String NAME = "Nitrogyclerine Melons!";
	
	@Instance("nitromelons")
	public static Nitromelons instance;
	
	public static Block nitroMelon, nitroMelonStem;
	public static Item nitroMelonSeed;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		nitroMelon = new BlockNitroMelon().setCreativeTab(CreativeTabs.MISC).setRegistryName(new ResourceLocation(MODID, "nitromelon")).setUnlocalizedName("nitromelon");
		GameRegistry.register(nitroMelon);
		nitroMelonStem = new BlockNitromelonStem().setRegistryName(new ResourceLocation(MODID, "nitromelon_stem")).setUnlocalizedName("nitromelon_stem");
		GameRegistry.register(nitroMelonStem);
		
		nitroMelonSeed = new ItemSeeds(nitroMelonStem, Blocks.FARMLAND).setCreativeTab(CreativeTabs.MISC).setRegistryName(new ResourceLocation(MODID, "nitromelon_seeds")).setUnlocalizedName("nitromelon_seeds");
		GameRegistry.register(nitroMelonSeed);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	

}
