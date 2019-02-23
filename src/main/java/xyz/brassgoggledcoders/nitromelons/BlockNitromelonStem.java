package xyz.brassgoggledcoders.nitromelons;

import javax.annotation.Nullable;

import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockNitromelonStem extends BlockStem {

	public BlockNitromelonStem() {
		super(Nitromelons.nitroMelon);
		this.setRegistryName(new ResourceLocation(Nitromelons.MODID, "nitromelon_stem"));
	}

	@Nullable
	protected Item getSeedItem() {
		return Nitromelons.nitroMelonSeed;
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock() == Blocks.MAGMA;
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			net.minecraftforge.common.IPlantable plantable) {
		return Blocks.MAGMA == state.getBlock();
	}
}
