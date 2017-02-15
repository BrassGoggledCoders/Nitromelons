package xyz.brassgoggledcoders.nitromelons;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNitroMelon extends Block {
	
	public static final PropertyBool PRIMED = PropertyBool.create("primed");

	public BlockNitroMelon() {
		super(Material.GOURD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(PRIMED, false));
		this.setTickRandomly(true);
	}

	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (heldItem != null && heldItem.getItem() == Items.FLINT_AND_STEEL)
        {
        	worldIn.setBlockState(pos, state.withProperty(PRIMED, true));
        	return true;
        }
		return false;
    }
	
    @Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(state.getValue(PRIMED).booleanValue()) {
        	worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3, true);
        }
    }
    
    @Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {PRIMED});
    }
    
    @Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(PRIMED, meta == 1 ? true : false);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(PRIMED).booleanValue()) ? 1 : 0;
}

}
