package miragefairy2018.mod.city;

import net.minecraft.util.EnumFacing;

public interface IBuildingAccess
{

	public default boolean canConnectWaterway(EnumFacing facing)
	{
		return false;
	}

	public default boolean canConnectRoad(EnumFacing facing)
	{
		return false;
	}

	public double getMana();

	public void setMana(double mana);

}
