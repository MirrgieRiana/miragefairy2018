package miragefairy2018.mod.city;

import java.util.Optional;

import miragefairy2018.mod.city.buildingentity.FairyRoad;
import miragefairy2018.mod.city.buildingentity.FairyWaterway;
import net.minecraft.util.EnumFacing;

public interface IBuildingAccess
{

	public default Optional<FairyRoad> getFairyRoad(EnumFacing facing)
	{
		return Optional.empty();
	}

	public default Optional<FairyWaterway> getFairyWaterway(EnumFacing facing)
	{
		return Optional.empty();
	}

}
