package miragefairy2018.lib.multi;

import miragefairy2018.lib.registry.Category;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemMulti extends Item
{

	public Category<? extends Subtype> subtypes;

	public ItemMulti(Category<? extends Subtype> subtypes)
	{
		this.subtypes = subtypes;

		subtypes.forEach(si -> si.setItem(this));

		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		int meta = stack.getMetadata();
		return "item." + subtypes.stream()
			.filter(si -> meta == si.id)
			.findFirst()
			.map(si -> si.resourceName)
			.orElse("null");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (!isInCreativeTab(tab)) return;
		subtypes.forEach(si -> {
			items.add(new ItemStack(this, 1, si.id));
		});
	}

}
