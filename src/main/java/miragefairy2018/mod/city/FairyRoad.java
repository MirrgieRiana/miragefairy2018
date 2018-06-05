package miragefairy2018.mod.city;

import java.util.ArrayList;
import java.util.Optional;

import miragefairy2018.mod.fairy.EnumManaType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class FairyRoad extends FairyComponentBase
{

	public double light;
	public double air;
	public double water;
	public double darkness;
	public double earth;
	public double fire;

	public FairyRoad(EnumFacing... facings)
	{
		super(facings);
	}

	@Override
	public void reset()
	{
		light = 0;
		air = 0;
		water = 0;
		darkness = 0;
		earth = 0;
		fire = 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		reset();
		light = compound.getDouble("light");
		air = compound.getDouble("air");
		water = compound.getDouble("water");
		darkness = compound.getDouble("darkness");
		earth = compound.getDouble("earth");
		fire = compound.getDouble("fire");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setDouble("light", light);
		compound.setDouble("air", air);
		compound.setDouble("water", water);
		compound.setDouble("darkness", darkness);
		compound.setDouble("earth", earth);
		compound.setDouble("fire", fire);
	}

	public double getMana(EnumManaType manaType)
	{
		switch (manaType) {
			case light:
				return light;
			case air:
				return air;
			case water:
				return water;
			case darkness:
				return darkness;
			case earth:
				return earth;
			case fire:
				return fire;
		}
		return 0;
	}

	public void setMana(EnumManaType manaType, double value)
	{
		switch (manaType) {
			case light:
				light = value;
				break;
			case air:
				air = value;
				break;
			case water:
				water = value;
				break;
			case darkness:
				darkness = value;
				break;
			case earth:
				earth = value;
				break;
			case fire:
				fire = value;
				break;
		}
	}

	public void push(EnumManaType manaType, double value)
	{
		double now = getMana(manaType);
		now += value;
		if (now > 1000) now = 1000; // TODO 値を変更可能に
		setMana(manaType, now);
	}

	public double pop(EnumManaType manaType, int value)
	{
		double now = getMana(manaType);
		double delta;

		if (now > value) {
			delta = value;
		} else if (now > 1) {
			delta = now;
		} else {
			delta = 0;
		}

		now -= delta;
		setMana(manaType, now);

		return delta;
	}

	public void update(IBlockAccess blockAccess, BlockPos pos)
	{

		// 隣接している建物を列挙
		ArrayList<FairyRoad> list = new ArrayList<>();
		list.add(this);
		for (EnumFacing facing : facings) {
			addList(list, blockAccess, pos, facing);
		}

		// マナレベルの平均化
		flattenMana(list, EnumManaType.light);
		flattenMana(list, EnumManaType.air);
		flattenMana(list, EnumManaType.water);
		flattenMana(list, EnumManaType.darkness);
		flattenMana(list, EnumManaType.earth);
		flattenMana(list, EnumManaType.fire);

	}

	protected void addList(ArrayList<FairyRoad> list, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing)
	{

		// その方向のTileが建物でない場合は無視
		TileEntity tileEntity2 = blockAccess.getTileEntity(pos.offset(facing));
		if (!(tileEntity2 instanceof ITileEntityBuilding)) return;
		ITileEntityBuilding tileEntityBuilding2 = (ITileEntityBuilding) tileEntity2;

		// その建物に建物情報が無い場合は無視
		Optional<IBuildingAccess> oBuildingAccess2 = tileEntityBuilding2.getBuildingAccess();
		if (!oBuildingAccess2.isPresent()) return;
		IBuildingAccess buildingAccess2 = oBuildingAccess2.get();

		// その建物がこちらに接続できない場合は無視
		EnumFacing facing2 = facing.getOpposite();
		Optional<FairyRoad> oFairyRoad2 = buildingAccess2.getFairyRoad(facing2);
		if (!oFairyRoad2.isPresent()) return;
		FairyRoad fairyRoad2 = oFairyRoad2.get();

		// 登録
		list.add(fairyRoad2);

	}

	protected void flattenMana(ArrayList<FairyRoad> list, EnumManaType manaType)
	{
		int count = list.size();
		if (count > 1) {
			double sum = 0;
			for (FairyRoad fairyRoad : list) {
				sum += fairyRoad.getMana(manaType);
			}
			double value = sum / count;
			for (FairyRoad fairyRoad : list) {
				fairyRoad.setMana(manaType, value);
			}
		}
	}

}
