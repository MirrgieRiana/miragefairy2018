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

	private ItemStack itemStack;

	public void setItem(Item item)
	{
		itemStack = new ItemStack(item, 1, id);
	}

	public ItemStack getItemStack()
	{
		return itemStack;
	}

	public ItemStack copyItemStack()
	{
		return getItemStack().copy();
	}

}
