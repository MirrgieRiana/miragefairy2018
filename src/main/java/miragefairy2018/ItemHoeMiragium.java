package miragefairy2018;

import net.minecraft.item.ItemHoe;

public class ItemHoeMiragium extends ItemHoe
{

	public ItemHoeMiragium()
	{
		super(ToolMaterial.IRON);
		setMaxDamage(50);
	}

	@Override
	public String getMaterialName()
	{
		return "MIRAGIUM";
	}

}
