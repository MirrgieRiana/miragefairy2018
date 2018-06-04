package miragefairy2018.mod.city;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public abstract class BuildingEntity implements IBuildingAccess
{

	public final TileEntityBuilding tileEntity;
	public final Building building;

	public BuildingEntity(TileEntityBuilding tileEntity, Building building)
	{
		this.tileEntity = tileEntity;
		this.building = building;
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		mana = compound.getDouble("mana");
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		compound.setDouble("mana", mana);
	}

	//

	public void update()
	{

		updateManaLevel();

	}

	protected void updateManaLevel()
	{

		// 隣接している建物を列挙
		ArrayList<BuildingEntity> list = new ArrayList<>();
		list.add(this);
		addList(list, EnumFacing.NORTH);
		addList(list, EnumFacing.SOUTH);
		addList(list, EnumFacing.EAST);
		addList(list, EnumFacing.WEST);

		// マナレベルの平均化
		int count = list.size();
		if (count > 1) {
			double sum = 0;
			for (BuildingEntity buildingEntity : list) {
				sum += buildingEntity.getMana();
			}
			double value = sum / count;
			for (BuildingEntity buildingEntity : list) {
				buildingEntity.setMana(value);
			}
		}

	}

	/**
	 * 指定の面に建物がある場合、リストに追加。
	 */
	protected void addList(ArrayList<BuildingEntity> list, EnumFacing facing)
	{
		TileEntity tileEntity2 = tileEntity.getWorld().getTileEntity(tileEntity.getPos().offset(facing));
		if (tileEntity2 instanceof TileEntityBuilding) {
			TileEntityBuilding tileEntityBuilding = (TileEntityBuilding) tileEntity2;
			if (tileEntityBuilding.oBuildingEntity.isPresent()) {
				list.add(tileEntityBuilding.oBuildingEntity.get());
			}
		}
	}

	//

	public double mana;

	@Override
	public double getMana()
	{
		return mana;
	}

	@Override
	public void setMana(double mana)
	{
		this.mana = mana;
	}

}
