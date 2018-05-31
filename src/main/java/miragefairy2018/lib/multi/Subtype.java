package miragefairy2018.lib.multi;

import miragefairy2018.lib.registry.CategoryItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Subtype<I extends Subtype<I>> extends CategoryItem<I>
{

	public Subtype(int id, String name, String resourceName)
	{
		super(id, name, resourceName);
	}

	//

	private Item item;

	void setItem(Item item)
	{
		this.item = item;
	}

	//

	private ItemStack itemStack;

	public ItemStack getItemStack()
	{
		if (itemStack == null) itemStack = createItemStack();
		return itemStack;
	}

	public ItemStack createItemStack()
	{
		return createItemStack(1);
	}

	public ItemStack createItemStack(int amount)
	{
		return new ItemStack(item, amount, id);
	}

}
