package miragefairy2018.mod.city;

import java.util.Optional;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class BuildingEntityWaterway extends BuildingEntity
{

	public FairyWaterway waterway;

	public BuildingEntityWaterway(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
		waterway = new FairyWaterway(EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		waterway.readFromNBT(compound.getCompoundTag("waterway"));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("waterway", waterway.createTagCompound());
	}

	@Override
	public void update()
	{
		waterway.update(tileEntity.getWorld(), tileEntity.getPos());
	}

	@Override
	public Optional<FairyWaterway> getFairyWaterway(EnumFacing facing)
	{
		return waterway.canConnect(facing) ? Optional.of(waterway) : Optional.empty();
	}

}
