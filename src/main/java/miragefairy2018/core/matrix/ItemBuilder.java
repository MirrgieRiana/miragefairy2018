package miragefairy2018.core.matrix;

import java.util.function.Consumer;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

public class ItemBuilder
{

	public final String modid;
	public final Side side;
	public final CreativeTabs creativeTab;

	public ItemBuilder(String modid, Side side, CreativeTabs creativeTab)
	{
		this.modid = modid;
		this.side = side;
		this.creativeTab = creativeTab;
	}

	public <I extends Item> Builder<I> builder(I item, String resourceName)
	{
		return new Builder<I>(item, resourceName);
	}

	public class Builder<I extends Item>
	{

		public final I item;
		public final String resourceName;

		public Builder(I item, String resourceName)
		{
			this.item = item;
			this.resourceName = resourceName;
		}

		public Builder<I> setCreativeTab()
		{
			item.setCreativeTab(creativeTab);
			return this;
		}

		public Builder<I> setUnlocalizedName()
		{
			item.setUnlocalizedName(resourceName);
			return this;
		}

		public Builder<I> setRegistryName()
		{
			item.setRegistryName(modid, resourceName);
			return this;
		}

		public Builder<I> register()
		{
			ForgeRegistries.ITEMS.register(item);
			return this;
		}

		public Builder<I> setCustomModelResourceLocation()
		{
			return setCustomModelResourceLocation(0, resourceName);
		}

		public Builder<I> setCustomModelResourceLocation(int meta, String resourceName)
		{
			if (side.isClient()) ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(modid + ":" + resourceName));
			return this;
		}

		public ItemStack getItemStack()
		{
			return new ItemStack(item);
		}

		public Builder<I> process(Consumer<? super Builder<I>> consumer)
		{
			consumer.accept(this);
			return this;
		}

	}

}