package miragefairy2018.mod.city;

import java.util.Optional;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class BuildingEntityRoad extends BuildingEntity
{

	public FairyRoad road;

	public BuildingEntityRoad(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
		road = new FairyRoad(EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		road.readFromNBT(compound.getCompoundTag("road"));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("road", road.createTagCompound());
	}

	@Override
	public void update()
	{
		road.update(tileEntity.getWorld(), tileEntity.getPos());
	}

	@Override
	public Optional<FairyRoad> getFairyRoad(EnumFacing facing)
	{
		return road.canConnect(facing) ? Optional.of(road) : Optional.empty();
	}

}
