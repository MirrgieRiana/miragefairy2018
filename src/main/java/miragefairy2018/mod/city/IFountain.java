package miragefairy2018.mod.city;

import java.util.Map;

public interface IFountain
{

	public boolean exists();

	public Map<SpecialManaType, Double> getSpecialManas();

	public IDoubleValue getSpecialMana(SpecialManaType specialManaType);

	public IDoubleValue getJewel();

}
