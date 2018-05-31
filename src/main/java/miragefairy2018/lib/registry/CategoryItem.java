package miragefairy2018.lib.registry;

import miragefairy2018.lib.util.Util;

public class CategoryItem<I extends CategoryItem<I>> implements Comparable<I>
{

	public final int id;
	public final String name;
	public final String oreNameSuffix;
	public final String resourceName;

	public CategoryItem(int id, String name, String resourceName)
	{
		this.id = id;
		this.name = name;
		this.oreNameSuffix = Util.toUpperCaseHead(name);
		this.resourceName = resourceName;
	}

	@Override
	public int compareTo(I other)
	{
		return name.compareTo(other.name);
	}

}
