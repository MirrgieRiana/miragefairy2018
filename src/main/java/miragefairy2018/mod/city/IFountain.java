package miragefairy2018.mod.city;

import java.util.Map;

public interface IFountain
{

	public boolean exists();

	public Map<SpecialManaType, Double> getSpecialManas();

	public double getSpecialMana(SpecialManaType specialManaType);

	public void setSpecialMana(SpecialManaType specialManaType, double value);

	public default void pushSpecialMana(SpecialManaType specialManaType, double value)
	{
		setSpecialMana(specialManaType, getSpecialMana(specialManaType) + value);
	}

	public default boolean popSpecialMana(SpecialManaType specialManaType, double value)
	{
		double now = getSpecialMana(specialManaType);
		if (now >= value) {
			now -= value;
			setSpecialMana(specialManaType, now);
			return true;
		} else {
			return false;
		}
	}

	public double getJewel();

	public void setJewel(double jewel);

	public default void pushJewel(double jewel)
	{
		setJewel(getJewel() + jewel);
	}

	public default boolean popJewel(double value)
	{
		double now = getJewel();
		if (now >= value) {
			now -= value;
			setJewel(now);
			return true;
		} else {
			return false;
		}
	}

}
