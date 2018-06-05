package miragefairy2018.mod.city;

import net.minecraft.util.EnumFacing;

public class BuildingEntityWaterway extends BuildingEntity
{

	public BuildingEntityWaterway(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
	}

	@Override
	public boolean canConnectRoad(EnumFacing facing)
	{
		return false;
	}

	@Override
	public boolean canConnectWaterway(EnumFacing facing)
	{
		return facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH || facing == EnumFacing.WEST || facing == EnumFacing.EAST;
	}

}
