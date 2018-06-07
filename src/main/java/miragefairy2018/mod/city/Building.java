package miragefairy2018.mod.city;

import java.util.function.BiFunction;

import miragefairy2018.lib.category.CategoryItem;
import miragefairy2018.mod.city.buildingentity.BuildingEntity;

public class Building extends CategoryItem<Building>
{

	private BiFunction<TileEntityBuilding, Building, BuildingEntity> fBuildingEntity;

	public Building(int id, String name, String resourceName, BiFunction<TileEntityBuilding, Building, BuildingEntity> fBuildingEntity)
	{
		super(id, name, resourceName);
		this.fBuildingEntity = fBuildingEntity;
	}

	public BuildingEntity createBuildingEntity(TileEntityBuilding tileEntity)
	{
		return fBuildingEntity.apply(tileEntity, this);
	}

}
