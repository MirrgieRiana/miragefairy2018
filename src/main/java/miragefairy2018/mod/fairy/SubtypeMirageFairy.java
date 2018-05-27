package miragefairy2018.mod.fairy;

import miragefairy2018.lib.multi.Subtype;

public class SubtypeMirageFairy extends Subtype
{

	public final MirageFairyColorSet colorSet;

	public SubtypeMirageFairy(int id, String name, String resourceName, MirageFairyColorSet colorSet)
	{
		super(id, name, resourceName);
		this.colorSet = colorSet;
	}

}
