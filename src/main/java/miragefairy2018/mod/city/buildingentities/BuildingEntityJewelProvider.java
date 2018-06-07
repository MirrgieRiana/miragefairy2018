package miragefairy2018.mod.city.buildingentities;

import miragefairy2018.mod.city.Building;
import miragefairy2018.mod.city.TileEntityBuilding;
import miragefairy2018.mod.city.buildingentity.BuildingEntityFacilityBase;

public class BuildingEntityJewelProvider extends BuildingEntityFacilityBase
{

	public BuildingEntityJewelProvider(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
	}

	@Override
	public void update()
	{
		super.update();

		if (waterway.enabled) waterway.oFountain.get().jewel.push(100);
	}

}
