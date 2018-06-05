package miragefairy2018.mod.city;

import miragefairy2018.lib.util.Util;

public class SpecialManaType
{

	public final String name;
	public final String oreNameSuffix;
	public final String resourceName;

	public SpecialManaType(String name, String resourceName)
	{
		this.name = name;
		this.oreNameSuffix = Util.toUpperCaseHead(name);
		this.resourceName = resourceName;
	}

	@Override
	public String toString()
	{
		return name;
	}

}
