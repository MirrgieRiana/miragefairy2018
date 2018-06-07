package miragefairy2018.mod.city;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.Nullable;

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

	private int i = (int) (Math.random() * 100);

	public void update(IBlockAccess blockAccess, BlockPos pos)
	{
		i--;
		if (i < 0) {
			i += 100 + (int) (Math.random() * 5);

			if (oFountain.isPresent()) {
				if (!oFountain.get().exists()) {
					oFountain = Optional.empty();
				}
			}

			updateWaterway.run();

		}
	}

	//

	public void startWaterSupply(IBlockAccess blockAccess, BlockPos pos, long token, IFountain fountain)
	{
		doWaterSupplyImpl(blockAccess, pos, token, fountain, null);
	}

	public void onWaterSupply(IBlockAccess blockAccess, BlockPos pos, long token, IFountain fountain, EnumFacing facing)
	{
		doWaterSupplyImpl(blockAccess, pos, token, fountain, facing);
	}

	protected void doWaterSupplyImpl(IBlockAccess blockAccess, BlockPos pos, long token, IFountain fountain, @Nullable EnumFacing facing)
	{

		// 登録
		oFountain = Optional.of(fountain);
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
