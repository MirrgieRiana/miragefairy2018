package miragefairy2018.mod.city.buildingentities;

import miragefairy2018.mod.city.Building;
import miragefairy2018.mod.city.TileEntityBuilding;
import miragefairy2018.mod.city.buildingentity.BuildingEntityFacilityBase;

public class BuildingEntityFarm extends BuildingEntityFacilityBase
{

	public BuildingEntityFarm(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
	}

	@Override
	public void update()
	{
		super.update();

		if (waterway.enabled) {
			double mana = road.darkness.pop(1, 100);
			if (mana > 0) {
				System.out.println(mana);
			}
		}
	}

}
