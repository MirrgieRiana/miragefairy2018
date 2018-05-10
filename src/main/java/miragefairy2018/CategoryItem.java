package miragefairy2018;

public class CategoryItem
{

	public final int id;
	public final String name;

	public CategoryItem(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public static <I extends CategoryItem> I register(I item, Category<I> metaCategory)
	{
		metaCategory.register(item.id, item);
		return item;
	}

}
