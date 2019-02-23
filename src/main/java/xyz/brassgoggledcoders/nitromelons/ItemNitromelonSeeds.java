package xyz.brassgoggledcoders.nitromelons;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.ResourceLocation;

public class ItemNitromelonSeeds extends ItemSeeds {

	public ItemNitromelonSeeds(Block crops, Block soil) {
		super(crops, soil);
		this.setCreativeTab(CreativeTabs.MISC);
		this.setRegistryName(new ResourceLocation(Nitromelons.MODID, "nitromelon_seeds"));
		this.setTranslationKey("nitromelon_seeds");
	}

}
