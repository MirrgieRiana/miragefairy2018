package miragefairy2018;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemMatrix extends Item
{

	public Category<SubItem> subItems;

	public ItemMatrix(Category<SubItem> subItems)
	{
		this.subItems = subItems;

		subItems.forEach(si -> si.setItem(this));

		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		int meta = stack.getMetadata();
		return "item." + subItems.stream()
			.filter(si -> meta == si.id)
			.findFirst()
			.map(si -> si.getResourceName())
			.orElse("null");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (!isInCreativeTab(tab)) return;
		subItems.forEach(si -> {
			items.add(new ItemStack(this, 1, si.id));
		});
	}

}
