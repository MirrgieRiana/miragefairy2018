package miragefairy2018.lib.matrix;

import miragefairy2018.lib.multi.Subtype;
import miragefairy2018.lib.registry.CategoryItem;

public class SubtypeMatrix<I1 extends CategoryItem<I1>, I2 extends CategoryItem<I2>> extends Subtype<SubtypeMatrix<I1, I2>>
{

	public final I1 item1;
	public final I2 item2;

	public SubtypeMatrix(I1 item1, I2 item2, int offset)
	{
		super(
			item1.id * offset + item2.id,
			item1.name + item2.name,
			item2.resourceName + "_" + item1.resourceName);
		this.item1 = item1;
		this.item2 = item2;
	}

}
