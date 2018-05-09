package com.example.examplemod;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDreamyFlower extends BlockBush
{

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 3);

	public BlockDreamyFlower()
	{
		super(Material.GLASS);
		setDefaultState(blockState.getBaseState()
			.withProperty(STAGE, 3));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);

		int stage = state.getValue(STAGE);
		if (stage < 3) {
			worldIn.setBlockState(pos, getDefaultState().withProperty(STAGE, stage + 1), 2);
		}

	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(STAGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(STAGE, meta);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { STAGE });
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 1));
		items.add(new ItemStack(this, 1, 2));
		items.add(new ItemStack(this, 1, 3));
	}

}
