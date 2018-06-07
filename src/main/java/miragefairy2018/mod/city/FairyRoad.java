package miragefairy2018.mod.city;

import java.util.ArrayList;
import java.util.Optional;

import miragefairy2018.mod.fairy.EnumManaType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class FairyRoad extends FairyComponentGrid
{

	public DoubleValue light = new DoubleValue(0);
	public DoubleValue air = new DoubleValue(0);
	public DoubleValue water = new DoubleValue(0);
	public DoubleValue darkness = new DoubleValue(0);
	public DoubleValue earth = new DoubleValue(0);
	public DoubleValue fire = new DoubleValue(0);

	public FairyRoad(EnumFacing... facings)
	{
		super(facings);
	}

	@Override
	public void reset()
	{
		light.set(0);
		air.set(0);
		water.set(0);
		darkness.set(0);
		earth.set(0);
		fire.set(0);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		reset();
		light.value = compound.getDouble("light");
		air.value = compound.getDouble("air");
		water.value = compound.getDouble("water");
		darkness.value = compound.getDouble("darkness");
		earth.value = compound.getDouble("earth");
		fire.value = compound.getDouble("fire");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setDouble("light", light.value);
		compound.setDouble("air", air.value);
		compound.setDouble("water", water.value);
		compound.setDouble("darkness", darkness.value);
		compound.setDouble("earth", earth.value);
		compound.setDouble("fire", fire.value);
	}

	public IDoubleValue getMana(EnumManaType manaType)
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
			default:
				throw new IllegalArgumentException("" + manaType);
		}
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
				sum += fairyRoad.getMana(manaType).get();
			}
			double value = sum / count;
			for (FairyRoad fairyRoad : list) {
				fairyRoad.getMana(manaType).set(value);
			}
		}
	}

}
