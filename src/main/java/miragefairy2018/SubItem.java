package miragefairy2018;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SubItem extends CategoryItem
{

	public SubItem(int id, String name, String resourceName)
	{
		super(id, name, resourceName);
	}

	//

	private Item item;

	public void setItem(Item item)
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
