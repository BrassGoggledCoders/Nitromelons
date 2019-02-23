package xyz.brassgoggledcoders.nitromelons;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockNitroMelon extends Block {

	public BlockNitroMelon() {
		super(Material.GOURD);
		this.setCreativeTab(CreativeTabs.MISC);
		this.setRegistryName(new ResourceLocation(Nitromelons.MODID, "nitromelon"));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			ItemStack heldItem = playerIn.getHeldItem(hand);
			if(!heldItem.isEmpty()
					&& (heldItem.getItem() == Items.FLINT_AND_STEEL || heldItem.getItem() == Items.FIRE_CHARGE)) {
				this.explode(worldIn, pos, state, playerIn);

				if(heldItem.getItem() == Items.FLINT_AND_STEEL) {
					heldItem.damageItem(1, playerIn);
				}
				else if(!playerIn.capabilities.isCreativeMode) {
					heldItem.shrink(1);
				}

				return true;
			}
		}
		return false;
	}

	@Override
	public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
		if(!worldIn.isRemote) {
			EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldIn, (double) ((float) pos.getX() + 0.5F),
					(double) pos.getY(), (double) ((float) pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
			entitytntprimed.setFuse(
					(short) (worldIn.rand.nextInt(entitytntprimed.getFuse() / 4) + entitytntprimed.getFuse() / 8));
			worldIn.spawnEntity(entitytntprimed);
		}
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if(!worldIn.isRemote && entityIn instanceof EntityArrow) {
			EntityArrow entityarrow = (EntityArrow) entityIn;

			if(entityarrow.isBurning()) {
				this.explode(worldIn, pos, worldIn.getBlockState(pos),
						entityarrow.shootingEntity instanceof EntityLivingBase
								? (EntityLivingBase) entityarrow.shootingEntity
								: null);
				worldIn.setBlockToAir(pos);
			}
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);

		if(worldIn.isBlockPowered(pos)) {
			this.explode(worldIn, pos, state, null);
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(worldIn.isBlockPowered(pos)) {
			this.explode(worldIn, fromPos, state, null);
		}
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}

	public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter) {
		if(!worldIn.isRemote) {
			EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldIn, (double) ((float) pos.getX() + 0.5F),
					(double) pos.getY(), (double) ((float) pos.getZ() + 0.5F), igniter);
			worldIn.spawnEntity(entitytntprimed);
			worldIn.playSound((EntityPlayer) null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ,
					SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			worldIn.setBlockToAir(pos);
		}
	}

}
