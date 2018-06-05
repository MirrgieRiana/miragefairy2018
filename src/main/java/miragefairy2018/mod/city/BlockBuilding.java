package miragefairy2018.mod.city;

import java.util.Optional;

import miragefairy2018.lib.UnlistedPropertyBoolean;
import miragefairy2018.lib.category.PropertyCategory;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class BlockBuilding extends BlockContainer
{

	public BlockBuilding(Material materialIn)
	{
		super(materialIn);
		setDefaultState(((IExtendedBlockState) blockState.getBaseState())
			.withProperty(VARIANT, ModuleCity.buildingNothing));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for (Building building : ModuleCity.buildings) {
			items.add(new ItemStack(this, 1, building.id));
		}
	}

	// state

	public static final PropertyCategory<Building> VARIANT = new PropertyCategory<>("variant", Building.class, ModuleCity.buildings);

	public static final UnlistedPropertyBoolean ROAD_DOWN = new UnlistedPropertyBoolean("road_down");
	public static final UnlistedPropertyBoolean ROAD_UP = new UnlistedPropertyBoolean("road_up");
	public static final UnlistedPropertyBoolean ROAD_NORTH = new UnlistedPropertyBoolean("road_north");
	public static final UnlistedPropertyBoolean ROAD_SOUTH = new UnlistedPropertyBoolean("road_south");
	public static final UnlistedPropertyBoolean ROAD_WEST = new UnlistedPropertyBoolean("road_west");
	public static final UnlistedPropertyBoolean ROAD_EAST = new UnlistedPropertyBoolean("road_east");
	public static final UnlistedPropertyBoolean WATERWAY_DOWN = new UnlistedPropertyBoolean("waterway_down");
	public static final UnlistedPropertyBoolean WATERWAY_UP = new UnlistedPropertyBoolean("waterway_up");
	public static final UnlistedPropertyBoolean WATERWAY_NORTH = new UnlistedPropertyBoolean("waterway_north");
	public static final UnlistedPropertyBoolean WATERWAY_SOUTH = new UnlistedPropertyBoolean("waterway_south");
	public static final UnlistedPropertyBoolean WATERWAY_WEST = new UnlistedPropertyBoolean("waterway_west");
	public static final UnlistedPropertyBoolean WATERWAY_EAST = new UnlistedPropertyBoolean("waterway_east");

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(VARIANT).id;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(VARIANT, ModuleCity.buildings.get(meta).orElse(ModuleCity.buildingNothing));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new ExtendedBlockState(this,
			new IProperty<?>[] {
				VARIANT,
			},
			new IUnlistedProperty<?>[] {
				ROAD_DOWN,
				ROAD_UP,
				ROAD_NORTH,
				ROAD_SOUTH,
				ROAD_WEST,
				ROAD_EAST,
				WATERWAY_DOWN,
				WATERWAY_UP,
				WATERWAY_NORTH,
				WATERWAY_SOUTH,
				WATERWAY_WEST,
				WATERWAY_EAST,
			});
	}

	public IBlockState getState(Building building)
	{
		return getDefaultState().withProperty(VARIANT, building);
	}

	public ItemStack createItemStack(Building building)
	{
		return new ItemStack(this, 1, building.id);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		BuildingEntity center = getBuildingEntity(world, pos).orElse(null);
		if (center != null) {
			state = state.withProperty(VARIANT, center.building);
		}
		return state;
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		BuildingEntity center = getBuildingEntity(world, pos).orElse(null);
		if (center != null) {
			IExtendedBlockState state2 = (IExtendedBlockState) state;
			state2 = connect(state2, world, pos, center, EnumFacing.DOWN, ROAD_DOWN, WATERWAY_DOWN);
			state2 = connect(state2, world, pos, center, EnumFacing.UP, ROAD_UP, WATERWAY_UP);
			state2 = connect(state2, world, pos, center, EnumFacing.NORTH, ROAD_NORTH, WATERWAY_NORTH);
			state2 = connect(state2, world, pos, center, EnumFacing.SOUTH, ROAD_SOUTH, WATERWAY_SOUTH);
			state2 = connect(state2, world, pos, center, EnumFacing.EAST, ROAD_EAST, WATERWAY_EAST);
			state2 = connect(state2, world, pos, center, EnumFacing.WEST, ROAD_WEST, WATERWAY_WEST);
			state = state2;
		}
		return super.getExtendedState(state, world, pos);
	}

	protected Optional<BuildingEntity> getBuildingEntity(IBlockAccess world, BlockPos pos)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof ITileEntityBuildingEntityProvider) {
			return ((ITileEntityBuildingEntityProvider) tileEntity).getBuildingAccess();
		}
		return Optional.empty();
	}

	protected IExtendedBlockState connect(IExtendedBlockState state, IBlockAccess world, BlockPos pos, BuildingEntity center, EnumFacing facing, UnlistedPropertyBoolean waterwaySouth,
		UnlistedPropertyBoolean roadSouth)
	{
		Optional<BuildingEntity> oDown = getBuildingEntity(world, pos.offset(facing));
		if (oDown.isPresent()) {
			if (center.canConnectWaterway(facing) && oDown.get().canConnectWaterway(facing.getOpposite())) {
				state = state.withProperty(waterwaySouth, true);
			}
			if (center.canConnectRoad(facing) && oDown.get().canConnectRoad(facing.getOpposite())) {
				state = state.withProperty(roadSouth, true);
			}
		}
		return state;
	}

	// behaviour

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
	{
		if (te instanceof TileEntityBuilding) {
			Optional<BuildingEntity> oBuildingEntity = ((TileEntityBuilding) te).oBuildingEntity;
			if (oBuildingEntity.isPresent()) {
				state = state.withProperty(VARIANT, oBuildingEntity.get().building);
			}
		}
		super.harvestBlock(worldIn, player, pos, state, te, stack);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		state = getActualState(state, world, pos);
		return createItemStack(state.getValue(VARIANT));
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		drops.add(createItemStack(state.getValue(VARIANT)));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}

	// tile entity

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityBuilding();
	}

	// style

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

}
