package miragefairy2018;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import mirrg.beryllium.lang.LambdaUtil;

public class SubItem extends CategoryItem
{

	public final Shape shape;
	public final Material material;

	public SubItem(Shape shape, Material material)
	{
		super(shape.id * 64 + material.id, shape.name + material.name);
		this.shape = shape;
		this.material = material;
	}

	public String getResourceName()
	{
		return LambdaUtil.reverse(Stream.of(name.split("()(?=[A-Z])", -1)))
			.map(s -> s.toLowerCase())
			.collect(Collectors.joining("_"));
	}

}
