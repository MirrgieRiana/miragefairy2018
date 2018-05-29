package miragefairy2018.mod.fairy;

import miragefairy2018.lib.multi.Subtype;

public class SubtypeMirageFairy extends Subtype
{

	public final EnumMirageFairy mirageFairy;

	public SubtypeMirageFairy(int id, String name, String resourceName, EnumMirageFairy mirageFairy)
	{
		super(id, name, resourceName);
		this.mirageFairy = mirageFairy;
	}

}
