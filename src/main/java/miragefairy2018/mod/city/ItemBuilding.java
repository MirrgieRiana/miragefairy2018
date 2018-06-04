package miragefairy2018.mod.city;

import java.util.Optional;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBuilding extends ItemBlock
{

	public final BlockBuilding blockBuilding;

	public ItemBuilding(BlockBuilding block)
	{
		super(block);
		blockBuilding = block;

		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		Optional<Building> oBuilding = ModuleCity.buildings.get(player.getHeldItem(hand).getMetadata());

		EnumActionResult result = super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);

		if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
			pos = pos.offset(facing);
		}

		if (result == EnumActionResult.SUCCESS) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof TileEntityBuilding) {
				TileEntityBuilding tileEntityBuilding = (TileEntityBuilding) tileEntity;
				tileEntityBuilding.oBuildingEntity = oBuilding.map(b -> b.createBuildingEntity(tileEntityBuilding));
			}
		}

		return result;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + ModuleCity.buildings.get(stack.getMetadata()).map(b -> b.resourceName).orElse("null");
	}

}
