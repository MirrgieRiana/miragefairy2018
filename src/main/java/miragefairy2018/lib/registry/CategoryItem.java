package miragefairy2018.lib.registry;

import miragefairy2018.lib.util.Util;

public class CategoryItem<I extends CategoryItem<I>> implements Comparable<I>
{

	public final int id;

	/**
	 * 鉱石辞書名と同じ。<br>
	 * 例：platePigIron
	 */
	public final String name;

	/**
	 * {@link #name} の先頭を大文字にしたもの。<br>
	 * 例：PlatePigIron
	 */
	public final String oreNameSuffix;

	/**
	 * リソース名。<br>
	 * 例：pig_iron_plate
	 */
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
