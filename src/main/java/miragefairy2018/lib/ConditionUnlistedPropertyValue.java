package miragefairy2018.lib;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.multipart.ICondition;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ConditionUnlistedPropertyValue implements ICondition
{

	private static final Splitter SPLITTER = Splitter.on('|').omitEmptyStrings();
	private final String key;
	private final String value;

	public ConditionUnlistedPropertyValue(String keyIn, String valueIn)
	{
		this.key = keyIn;
		this.value = valueIn;
	}

	protected static interface IPropertyWrapper<T>
	{

		public Optional<Predicate<IBlockState>> parse(String value);

	}

	@SideOnly(Side.CLIENT)
	protected static class PropertyWrapper<T extends Comparable<T>> implements IPropertyWrapper<T>
	{

		private IProperty<T> property;

		public PropertyWrapper(IProperty<T> property)
		{
			this.property = property;
		}

		@Override
		public Optional<Predicate<IBlockState>> parse(String value)
		{
			return property.parseValue(value).transform(t -> {
				return state -> {
					return state.getValue(property).equals(t);
				};
			});
		}

	}

	@SideOnly(Side.CLIENT)
	protected static class UnlistedPropertyWrapper<T> implements IPropertyWrapper<T>
	{

		private IUnlistedProperty<T> property;

		public UnlistedPropertyWrapper(IUnlistedProperty<T> property)
		{
			this.property = property;
		}

		@Override
		public Optional<Predicate<IBlockState>> parse(String value)
		{
			return Optional.of(state -> {
				T v = ((IExtendedBlockState) state).getValue(property);
				return String.valueOf(v).equals(value);
			});
		}

	}

	@Override
	public Predicate<IBlockState> getPredicate(BlockStateContainer blockState)
	{
		IPropertyWrapper<?> propertyWrapper;
		a:
		{
			{
				IProperty<?> property = blockState.getProperty(key);
				if (property != null) {
					propertyWrapper = new PropertyWrapper<>(property);
					break a;
				}
			}
			{
				IUnlistedProperty<?> property = ((ExtendedBlockState) blockState).getUnlistedProperties()
					.stream()
					.filter(n -> n.getName().equals(key))
					.findFirst()
					.orElse(null);
				if (property != null) {
					propertyWrapper = new UnlistedPropertyWrapper<>(property);
					break a;
				}
			}
			throw new RuntimeException(this.toString() + ": Definition: " + blockState + " has no property: " + this.key);
		}

		String s = this.value;
		boolean flag = !s.isEmpty() && s.charAt(0) == '!';

		if (flag) {
			s = s.substring(1);
		}

		List<String> list = SPLITTER.splitToList(s);

		if (list.isEmpty()) {
			throw new RuntimeException(this.toString() + ": has an empty value: " + this.value);
		} else {
			Predicate<IBlockState> predicate;

			if (list.size() == 1) {
				predicate = this.makePredicate(propertyWrapper, s);
			} else {
				predicate = Predicates.or(Iterables.transform(list, new Function<String, Predicate<IBlockState>>() {
					@Override
					@Nullable
					public Predicate<IBlockState> apply(@Nullable String p_apply_1_)
					{
						return ConditionUnlistedPropertyValue.this.makePredicate(propertyWrapper, p_apply_1_);
					}
				}));
			}

			return flag ? Predicates.not(predicate) : predicate;
		}
	}

	private Predicate<IBlockState> makePredicate(IPropertyWrapper<?> propertyWrapper, String valueIn)
	{
		Optional<Predicate<IBlockState>> oPredicate = propertyWrapper.parse(valueIn);
		if (!oPredicate.isPresent()) {
			throw new RuntimeException(this.toString() + ": has an unknown value: " + this.value);
		} else {
			return new Predicate<IBlockState>() {
				@Override
				public boolean apply(@Nullable IBlockState p_apply_1_)
				{
					return p_apply_1_ != null && oPredicate.get().apply(p_apply_1_);
				}
			};
		}
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this).add("key", this.key).add("value", this.value).toString();
	}

}
