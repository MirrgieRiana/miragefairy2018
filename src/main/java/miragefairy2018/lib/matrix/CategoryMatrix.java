package miragefairy2018.lib.matrix;

import miragefairy2018.lib.registry.Category;
import miragefairy2018.lib.registry.CategoryItem;

public class CategoryMatrix<I1 extends CategoryItem, I2 extends CategoryItem> extends Category<SubtypeMatrix<I1, I2>>
{

	private int offset;

	public CategoryMatrix(int offset)
	{
		this.offset = offset;
	}

	public SubtypeMatrix<I1, I2> register(I1 item1, I2 item2)
	{
		return super.register(new SubtypeMatrix<>(item1, item2, offset));
	}

}