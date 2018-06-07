package miragefairy2018.mod.city;

import java.util.Optional;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityBuilding extends TileEntity implements ITickable, ITileEntityBuilding
{

	public Optional<BuildingEntity> oBuildingEntity = Optional.empty();

	@Override
	public void update()
	{
		if (oBuildingEntity.isPresent()) {
			oBuildingEntity.get().update();
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		if (oBuildingEntity.isPresent()) {
			NBTTagCompound tagBuilding = new NBTTagCompound();
			tagBuilding.setString("name", oBuildingEntity.get().building.name);
			tagBuilding.setTag("entity", oBuildingEntity.get().createTagCompound());
			compound.setTag("building", tagBuilding);
		}
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("building", NBT.TAG_COMPOUND)) {
			NBTTagCompound tagBuilding = compound.getCompoundTag("building");

			if (tagBuilding.hasKey("name", NBT.TAG_STRING)) {
				String name = tagBuilding.getString("name");
				Optional<Building> oBuilding = ModuleCity.buildings.get(name);

				if (oBuilding.isPresent()) {

					if (tagBuilding.hasKey("entity", NBT.TAG_COMPOUND)) {
						NBTTagCompound tagBuildingEntity = tagBuilding.getCompoundTag("entity");

						BuildingEntity buildingEntity = oBuilding.get().createBuildingEntity(this);
						buildingEntity.readFromNBT(tagBuildingEntity);
						oBuildingEntity = Optional.of(buildingEntity);

					}
				}
			}
		}
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public Optional<IBuildingAccess> getBuildingAccess()
	{
		return oBuildingEntity.map(be -> be);
	}

}
