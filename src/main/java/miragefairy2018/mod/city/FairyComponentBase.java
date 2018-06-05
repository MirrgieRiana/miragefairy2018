package miragefairy2018.mod.city;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class FairyComponentBase
{

	protected EnumFacing[] facings;

	public FairyComponentBase(EnumFacing... facings)
	{
		this.facings = facings;
		reset();
	}

	public boolean canConnect(EnumFacing facing)
	{
		for (EnumFacing facing2 : facings) {
			if (facing2 == facing) return true;
		}
		return false;
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

}
