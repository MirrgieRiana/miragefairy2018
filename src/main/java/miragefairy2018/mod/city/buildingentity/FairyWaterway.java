package miragefairy2018.mod.city.buildingentity;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.Nullable;

import miragefairy2018.mod.city.FairyComponentGrid;
import miragefairy2018.mod.city.IBuildingAccess;
import miragefairy2018.mod.city.ITileEntityBuilding;
import mirrg.beryllium.event2.EventProviderRunnable;
import mirrg.beryllium.struct.Tuple;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class FairyWaterway extends FairyComponentGrid
{

	public Optional<IFountain> oFountain;
	public long token;

	public FairyWaterway(EnumFacing... facings)
	{
		super(facings);
	}

	@Override
	public void reset()
	{
		oFountain = Optional.empty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		reset();
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{

	}

	//

	public EventProviderRunnable updateWaterway = new EventProviderRunnable();
	public EventProviderRunnable forgetWaterway = new EventProviderRunnable();

	private int i = FairyFountain.WATERWAY_UPDATE_TICK;
	public boolean enabled = false;;

	public void update(IBlockAccess blockAccess, BlockPos pos)
	{
		i++;
		if (i == FairyFountain.WATERWAY_UPDATE_TICK) {

			enabled = true;
			updateWaterway.run();

		} else if (i == FairyFountain.WATERWAY_FORGET_TICK) {

			oFountain = Optional.empty();
			enabled = false;
			forgetWaterway.run();

		}
	}

	//

	public void startWaterSupply(IBlockAccess blockAccess, BlockPos pos, long token, FairyFountain fountain)
	{
		doWaterSupplyImpl(blockAccess, pos, token, fountain, null);
	}

	public void onWaterSupply(IBlockAccess blockAccess, BlockPos pos, long token, FairyFountain fountain, EnumFacing facing)
	{
		doWaterSupplyImpl(blockAccess, pos, token, fountain, facing);
	}

	protected void doWaterSupplyImpl(IBlockAccess blockAccess, BlockPos pos, long token, FairyFountain fountain, @Nullable EnumFacing facing)
	{

		// 登録
		oFountain = Optional.of(fountain);
		i = 0;
		this.token = token;

		// 隣接している建物を列挙
		ArrayList<Tuple<EnumFacing, FairyWaterway>> list = new ArrayList<>();
		for (EnumFacing facing2 : facings) {
			if (facing2 != facing) {
				addList(list, blockAccess, pos, facing2);
			}
		}

		// 伝搬
		for (Tuple<EnumFacing, FairyWaterway> tuple : list) {
			if (tuple.y.token != token) {
				tuple.y.onWaterSupply(blockAccess, pos.offset(tuple.x), token, fountain, facing);
			}
		}

	}

	protected void addList(ArrayList<Tuple<EnumFacing, FairyWaterway>> list, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing)
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
		Optional<FairyWaterway> oFairyWaterway2 = buildingAccess2.getFairyWaterway(facing2);
		if (!oFairyWaterway2.isPresent()) return;
		FairyWaterway fairyWaterway2 = oFairyWaterway2.get();

		// 登録
		list.add(new Tuple<>(facing, fairyWaterway2));

	}

}
