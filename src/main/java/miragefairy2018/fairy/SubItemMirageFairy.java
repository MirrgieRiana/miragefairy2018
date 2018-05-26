package miragefairy2018.fairy;

import miragefairy2018.core.matrix.SubItem;

public class SubItemMirageFairy extends SubItem
{

	public final MirageFairyColorSet colorSet;

	public SubItemMirageFairy(int id, String name, String resourceName, MirageFairyColorSet colorSet)
	{
		super(id, name, resourceName);
		this.colorSet = colorSet;
	}

}
