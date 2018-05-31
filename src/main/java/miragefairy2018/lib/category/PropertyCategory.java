package miragefairy2018.lib.category;

import java.util.Collection;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.properties.PropertyHelper;

public class PropertyCategory<I extends CategoryItem<I>> extends PropertyHelper<I>
{

	private Category<I> category;

	public PropertyCategory(String name, Class<I> valueClass, Category<I> category)
	{
		super(name, valueClass);
		this.category = category;
	}

	@Override
	public Collection<I> getAllowedValues()
	{
		return ImmutableSet.copyOf(category);
	}

	@Override
	public Optional<I> parseValue(String value)
	{
		return Optional.fromJavaUtil(category.get(value));
	}

	@Override
	public String getName(I value)
	{
		return value.name;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!super.equals(obj)) return false;
		if (!(obj instanceof PropertyCategory)) return false;
		if (!category.equals(((PropertyCategory<?>) obj).category)) return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		return result;
	}

}
