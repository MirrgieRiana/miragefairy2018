package miragefairy2018.mod.city.buildingentity;

import java.util.Hashtable;

import miragefairy2018.lib.DoubleValue;
import miragefairy2018.lib.IDoubleValue;
import miragefairy2018.mod.city.FairyComponent;
import miragefairy2018.mod.city.SpecialManaType;
import mirrg.beryllium.event2.EventProviderRunnable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class FairyFountain extends FairyComponent
{

	public static final int WATER_SUPPLY_INTERVAL = 100;
	public static final int WATERWAY_UPDATE_TICK = 95;
	public static final int WATERWAY_FORGET_TICK = 105;

	//

	@Override
	public void reset()
	{
		i = 0;
		specialManas.clear();
		jewel.set(0);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		reset();

		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		// TODO 自動生成されたメソッド・スタブ
	}

	//

	public final EventProviderRunnable waterSupply = new EventProviderRunnable();

	private int i = 0;

	@Override
	public void update(IBlockAccess blockAccess, BlockPos pos)
	{
		i++;
		if (i > WATER_SUPPLY_INTERVAL) {
			i -= WATER_SUPPLY_INTERVAL;

			waterSupply.run();

		}
	}

	//

	public Hashtable<SpecialManaType, Double> specialManas = new Hashtable<>();

	public IDoubleValue getSpecialMana(SpecialManaType specialManaType)
	{
		return new IDoubleValue() {
			@Override
			public void set(double value)
			{
				if (value == 0) {
					specialManas.remove(specialManaType);
				} else {
					specialManas.put(specialManaType, value);
				}
			}

			@Override
			public double get()
			{
				Double value = specialManas.get(specialManaType);
				if (value != null) {
					return value;
				} else {
					return 0;
				}
			}
		};
	}

	//

	public DoubleValue jewel = new DoubleValue(0);

}
