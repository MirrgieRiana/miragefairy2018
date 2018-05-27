package miragefairy2018.mod.ore;

import mirrg.beryllium.lang.NumberUtil;
import mirrg.beryllium.struct.ImmutableArray;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOre extends Block
{

	public BlockOre()
	{
		super(Material.ROCK);

		// meta
		setDefaultState(blockState.getBaseState()
			.withProperty(VARIANT, EnumType.DEFAULT));

		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 2);

	}

	// state

	public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

	public static enum EnumType implements IStringSerializable
	{
		MIRAGIUM("Miragium", "miragium"),
		MIRAGONITE("Miragonite", "miragonite");

		//

		public static final EnumType DEFAULT = MIRAGIUM;
		public static final ImmutableArray<EnumType> values = new ImmutableArray<>(values());
		public static final int length = values.length();

		public static EnumType type(int meta)
		{
			return NumberUtil.contains(meta, 0, length - 1) > 0 ? values.get(meta) : DEFAULT;
		}

		public static EnumType type(EnumType type)
		{
			return type != null ? type : DEFAULT;
		}

		public static int meta(EnumType type)
		{
			return type != null ? type.ordinal() : DEFAULT.ordinal();
		}

		public static int meta(int meta)
		{
			return NumberUtil.contains(meta, 0, length - 1) > 0 ? meta : DEFAULT.ordinal();
		}

		//

		public final String oreName;
		public final String resourceName;

		private EnumType(String oreName, String resourceName)
		{
			this.oreName = oreName;
			this.resourceName = resourceName;
		}

		@Override
		public String getName()
		{
			return resourceName;
		}

	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return EnumType.meta(state.getValue(VARIANT));
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(VARIANT, EnumType.type(meta));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	public IBlockState getState(EnumType type)
	{
		return getDefaultState().withProperty(VARIANT, type);
	}

	public ItemStack createItemStack(EnumType type)
	{
		return new ItemStack(this, 1, EnumType.meta(type));
	}

	// behaviour

	@Override
	public int damageDropped(IBlockState state)
	{
		return EnumType.meta(state.getValue(VARIANT));
	}

	// style

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	//

	@Override
	public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> items)
	{
		for (EnumType type : EnumType.values) {
			items.add(createItemStack(type));
		}
	}

}
