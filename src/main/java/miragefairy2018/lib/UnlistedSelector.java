package miragefairy2018.lib;

import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.client.renderer.block.model.VariantList;
import net.minecraft.client.renderer.block.model.multipart.ConditionAnd;
import net.minecraft.client.renderer.block.model.multipart.ConditionOr;
import net.minecraft.client.renderer.block.model.multipart.ICondition;
import net.minecraft.client.renderer.block.model.multipart.Selector;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UnlistedSelector extends Selector
{

	public UnlistedSelector(ICondition conditionIn, VariantList variantListIn)
	{
		super(conditionIn, variantListIn);
	}

	@SideOnly(Side.CLIENT)
	public static class Deserializer implements JsonDeserializer<Selector>
	{

		private static final Function<JsonElement, ICondition> FUNCTION_OR_AND = new Function<JsonElement, ICondition>() {
			@Override
			@Nullable
			public ICondition apply(@Nullable JsonElement p_apply_1_)
			{
				return p_apply_1_ == null ? null : getOrAndCondition(p_apply_1_.getAsJsonObject());
			}
		};
		private static final Function<Entry<String, JsonElement>, ICondition> FUNCTION_PROPERTY_VALUE = new Function<Entry<String, JsonElement>, ICondition>() {
			@Override
			@Nullable
			public ICondition apply(@Nullable Entry<String, JsonElement> p_apply_1_)
			{
				return p_apply_1_ == null ? null : makePropertyValue(p_apply_1_);
			}
		};

		@Override
		public Selector deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException
		{
			JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
			return new Selector(this.getWhenCondition(jsonobject), (VariantList) p_deserialize_3_.deserialize(jsonobject.get("apply"), VariantList.class));
		}

		private ICondition getWhenCondition(JsonObject json)
		{
			return json.has("when") ? getOrAndCondition(JsonUtils.getJsonObject(json, "when")) : ICondition.TRUE;
		}

		@VisibleForTesting
		static ICondition getOrAndCondition(JsonObject json)
		{
			Set<Entry<String, JsonElement>> set = json.entrySet();

			if (set.isEmpty()) {
				throw new JsonParseException("No elements found in selector");
			} else if (set.size() == 1) {
				if (json.has("OR")) {
					return new ConditionOr(Iterables.transform(JsonUtils.getJsonArray(json, "OR"), FUNCTION_OR_AND));
				} else {
					return json.has("AND") ? new ConditionAnd(Iterables.transform(JsonUtils.getJsonArray(json, "AND"), FUNCTION_OR_AND)) : makePropertyValue(set.iterator().next());
				}
			} else {
				return new ConditionAnd(Iterables.transform(set, FUNCTION_PROPERTY_VALUE));
			}
		}

		private static ConditionUnlistedPropertyValue makePropertyValue(Entry<String, JsonElement> entry)
		{
			return new ConditionUnlistedPropertyValue(entry.getKey(), entry.getValue().getAsString());
		}

	}

}
