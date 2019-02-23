package xyz.brassgoggledcoders.nitromelons;

import javax.annotation.Nullable;

import net.minecraft.block.BlockStem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockNitromelonStem extends BlockStem {

	public BlockNitromelonStem() {
		super(Nitromelons.nitroMelon);
		this.setRegistryName(new ResourceLocation(Nitromelons.MODID, "nitromelon_stem"));
	}

	@Nullable
	protected Item getSeedItem() {
		return Nitromelons.nitroMelonSeed;
	}

}
