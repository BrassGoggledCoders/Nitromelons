package xyz.brassgoggledcoders.nitromelons;

import javax.annotation.Nullable;

import net.minecraft.block.BlockStem;
import net.minecraft.item.Item;

public class BlockNitromelonStem extends BlockStem {

	public BlockNitromelonStem() {
		super(Nitromelons.nitroMelon);
	}

	@Nullable
	protected Item getSeedItem()
	{
	  return Nitromelons.nitroMelonSeed;
	}

}
