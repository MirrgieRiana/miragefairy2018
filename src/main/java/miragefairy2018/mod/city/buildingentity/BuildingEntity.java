package miragefairy2018.mod.city.buildingentity;

import miragefairy2018.mod.city.Building;
import miragefairy2018.mod.city.IBuildingAccess;
import miragefairy2018.mod.city.TileEntityBuilding;
import net.minecraft.nbt.NBTTagCompound;

public abstract class BuildingEntity implements IBuildingAccess
{

	public final TileEntityBuilding tileEntity;
	public final Building building;

	public BuildingEntity(TileEntityBuilding tileEntity, Building building)
	{
		this.tileEntity = tileEntity;
		this.building = building;
	}

	public void readFromNBT(NBTTagCompound compound)
	{

	}

	public NBTTagCompound createTagCompound()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return tag;
	}

	public void writeToNBT(NBTTagCompound compound)
	{

	}

	public void update()
	{

	}

}
