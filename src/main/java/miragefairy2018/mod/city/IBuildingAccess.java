package miragefairy2018.mod.city;

import java.util.Optional;

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
