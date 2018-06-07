package miragefairy2018.mod.city.buildingentities;

import miragefairy2018.mod.city.Building;
import miragefairy2018.mod.city.TileEntityBuilding;
import miragefairy2018.mod.city.buildingentity.BuildingEntityFacilityBase;

public class BuildingEntityFarm extends BuildingEntityFacilityBase
{

	public double mana = 0;

	public BuildingEntityFarm(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);

		waterway.updateWaterway.register(() -> {
			System.out.println(mana);
			mana = 0;
		});
		waterway.forgetWaterway.register(() -> {
			mana = 0;
		});
	}

	@Override
	public void update()
	{
		super.update();

		if (waterway.enabled) mana += road.darkness.pop(1, 100);
	}

}
