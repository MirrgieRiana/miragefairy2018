package miragefairy2018.mod.city.buildingentities;

import miragefairy2018.mod.city.Building;
import miragefairy2018.mod.city.TileEntityBuilding;
import miragefairy2018.mod.city.buildingentity.BuildingEntityFacilityBase;

public class BuildingEntityHouse extends BuildingEntityFacilityBase
{

	public BuildingEntityHouse(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
	}

	@Override
	public void update()
	{
		super.update();

		if (waterway.enabled) {
			double jewel = waterway.oFountain.get().jewel.pop(1, 25);
			if (jewel > 0) {
				road.darkness.push(jewel * 1.3);
			}
		}
	}

}
