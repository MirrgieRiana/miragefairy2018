package miragefairy2018.lib.matrix;

public class CategoryItem
{

	public final int id;
	public final String name;
	public final String resourceName;

	public CategoryItem(int id, String name, String resourceName)
	{
		this.id = id;
		this.name = name;
		this.resourceName = resourceName;
	}

	public static <I extends CategoryItem> I register(I item, Category<I> metaCategory)
	{
		metaCategory.register(item.id, item);
		return item;
	}

}
