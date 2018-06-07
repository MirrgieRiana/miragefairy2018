package miragefairy2018.mod.city;

import net.minecraft.util.EnumFacing;

public abstract class FairyComponentGrid extends FairyComponent
{

	protected EnumFacing[] facings;

	public FairyComponentGrid(EnumFacing... facings)
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

}
