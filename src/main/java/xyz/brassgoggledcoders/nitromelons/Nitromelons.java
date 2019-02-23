package xyz.brassgoggledcoders.nitromelons;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
@Mod(modid = Nitromelons.MODID, name = Nitromelons.NAME, version = "@VERSION@")
public class Nitromelons {

	public static final String MODID = "nitromelons";
	public static final String NAME = "Nitrogyclerine Melons!";

	@Instance("nitromelons")
	public static Nitromelons instance;

	public static Block nitroMelon, nitroMelonStem;
	public static Item nitroMelonSeed;

	public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MODID, "nitro_seeds");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LootTableList.register(LOOT_TABLE);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		nitroMelon = new BlockNitroMelon();
		event.getRegistry().register(nitroMelon);
		nitroMelonStem = new BlockNitromelonStem();
		event.getRegistry().register(nitroMelonStem);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		nitroMelonSeed = new ItemNitromelonSeeds(nitroMelonStem, Blocks.MAGMA);
		event.getRegistry().register(nitroMelonSeed);
		event.getRegistry().register(new ItemBlock(nitroMelon).setRegistryName(nitroMelon.getRegistryName()));
		// Why is registering item models the most complex part of a small mod now
		// MOYAAAAAAAAAAAAAAAANG
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(nitroMelonSeed.getRegistryName(),
				"inventory");
		ModelBakery.registerItemVariants(nitroMelonSeed, fullModelLocation);
		ModelLoader.setCustomMeshDefinition(nitroMelonSeed, stack -> fullModelLocation);
	}

	@SubscribeEvent
	public static void lootLoad(LootTableLoadEvent evt) {
		if(evt.getName().toString().contains("chests")) {
			LootEntry entry = new LootEntryTable(LOOT_TABLE, 1, 80, null, "nitro_seeds");
			LootPool pool = new LootPool(new LootEntry[] { entry }, new LootCondition[0], new RandomValueRange(1),
					new RandomValueRange(0, 2), "nitro_seeds"); // Other params set as you wish.
			evt.getTable().addPool(pool);
		}
	}
}
