package miragefairy2018.mod.city;

import java.util.Optional;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class BuildingEntityRoadAndWaterway extends BuildingEntity
{

	public FairyRoad road;
	public FairyWaterway waterway;

	public BuildingEntityRoadAndWaterway(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
		road = new FairyRoad(EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST);
		waterway = new FairyWaterway(EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		road.readFromNBT(compound.getCompoundTag("road"));
		waterway.readFromNBT(compound.getCompoundTag("waterway"));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("road", road.createTagCompound());
		compound.setTag("waterway", waterway.createTagCompound());
	}

	@Override
	public void update()
	{
		road.update(tileEntity.getWorld(), tileEntity.getPos());
		waterway.update(tileEntity.getWorld(), tileEntity.getPos());
	}

	@Override
	public Optional<FairyRoad> getFairyRoad(EnumFacing facing)
	{
		return road.canConnect(facing) ? Optional.of(road) : Optional.empty();
	}

	@Override
	public Optional<FairyWaterway> getFairyWaterway(EnumFacing facing)
	{
		return waterway.canConnect(facing) ? Optional.of(waterway) : Optional.empty();
	}

}
