package miragefairy2018;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemSeedDreamyFlower extends Item implements IPlantable
{

	// behaviour

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack itemStack = player.getHeldItem(hand);
		IBlockState blockState = world.getBlockState(pos);
		if (facing == EnumFacing.UP
			&& player.canPlayerEdit(pos.offset(facing), facing, itemStack)
			&& blockState.getBlock().canSustainPlant(blockState, world, pos, EnumFacing.UP, this)
			&& world.isAirBlock(pos.up())) {

			world.setBlockState(pos.up(), ModMirageFairy2018.blockDreamyFlower.getDefaultState());

			if (player instanceof EntityPlayerMP) {
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos.up(), itemStack);
			}

			itemStack.shrink(1);

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
	{
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos)
	{
		return ModMirageFairy2018.blockDreamyFlower.getDefaultState();
	}

}
