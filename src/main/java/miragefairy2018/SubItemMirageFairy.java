package miragefairy2018;

public class SubItemMirageFairy extends SubItem
{

	public final MirageFairyColorSet colorSet;

	public SubItemMirageFairy(int id, String name, String resourceName, MirageFairyColorSet colorSet)
	{
		super(id, name, resourceName);
		this.colorSet = colorSet;
	}

}
