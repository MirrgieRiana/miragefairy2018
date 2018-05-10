package miragefairy2018;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemMatrix extends Item
{

	public ItemMatrix()
	{
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		int meta = stack.getMetadata();
		int shape = meta / 64;
		int material = meta % 64;
		String unlocalizedName = "item.";

		if (shape == 0) unlocalizedName += "dust";
		if (shape == 1) unlocalizedName += "ingot";

		if (material == 0) unlocalizedName += "Mirage";

		return unlocalizedName;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		items.add(new ItemStack(this, 1, 0 * 64 + 0));
		items.add(new ItemStack(this, 1, 1 * 64 + 0));
	}
}
