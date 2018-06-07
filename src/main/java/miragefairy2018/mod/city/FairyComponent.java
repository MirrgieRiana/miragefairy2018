package miragefairy2018.mod.city;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class FairyComponent
{

	public FairyComponent()
	{

	}

	public abstract void reset();

	public abstract void readFromNBT(NBTTagCompound compound);

	public NBTTagCompound createTagCompound()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return tag;
	}

	public abstract void writeToNBT(NBTTagCompound compound);

	public abstract void update(IBlockAccess blockAccess, BlockPos pos);

}
