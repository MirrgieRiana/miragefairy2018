package miragefairy2018.lib.matrix;

public class SubItemMatrix extends SubItem
{

	public final Shape shape;
	public final Material material;

	public SubItemMatrix(Shape shape, Material material)
	{
		this(shape, material, 64);
	}

	public SubItemMatrix(Shape shape, Material material, int shapeOffset)
	{
		super(
			shape.id * shapeOffset + material.id,
			shape.name + material.name,
			material.resourceName + "_" + shape.resourceName);
		this.shape = shape;
		this.material = material;
	}

}
