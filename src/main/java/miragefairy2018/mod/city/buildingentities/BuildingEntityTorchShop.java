package miragefairy2018.mod.city.buildingentities;

import miragefairy2018.mod.city.Building;
import miragefairy2018.mod.city.SpecialManas;
import miragefairy2018.mod.city.TileEntityBuilding;
import miragefairy2018.mod.city.buildingentity.BuildingEntityFacilityBase;

public class BuildingEntityTorchShop extends BuildingEntityFacilityBase
{

	public double mana = 0;

	public BuildingEntityTorchShop(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);

		waterway.updateWaterway.register(() -> {
			waterway.oFountain.get().getSpecialMana(SpecialManas.light).push(mana / 100 * 0.1);
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
