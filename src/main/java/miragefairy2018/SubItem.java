package miragefairy2018;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import mirrg.beryllium.lang.LambdaUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SubItem extends CategoryItem
{

	public final Shape shape;
	public final Material material;

	public SubItem(Shape shape, Material material)
	{
		super(shape.id * 64 + material.id, shape.name + material.name);
		this.shape = shape;
		this.material = material;
	}

	public String getResourceName()
	{
		return LambdaUtil.reverse(Stream.of(name.split("()(?=[A-Z])", -1)))
			.map(s -> s.toLowerCase())
			.collect(Collectors.joining("_"));
	}

	//

	private Item item;

	public void setItem(Item item)
	{
		this.item = item;
	}

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
