package miragefairy2018.mod.tool;

import net.minecraft.item.ItemHoe;

public class ItemHoeMiragium extends ItemHoe
{

	public ItemHoeMiragium()
	{
		super(ToolMaterial.IRON);
		setMaxDamage(10);
	}

	@Override
	public String getMaterialName()
	{
		return "MIRAGIUM";
	}

}
