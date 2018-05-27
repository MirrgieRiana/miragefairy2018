package miragefairy2018.lib.util;

import java.util.function.Consumer;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

public class Builder
{

	public final String modid;
	public final Side side;
	public final CreativeTabs creativeTab;

	public Builder(String modid, Side side, CreativeTabs creativeTab)
	{
		this.modid = modid;
		this.side = side;
		this.creativeTab = creativeTab;
	}

	public class ItemBuilder<I extends Item>
	{

		public final I item;
		public final String resourceName;

		public ItemBuilder(I item, String resourceName)
		{
			this.item = item;
			this.resourceName = resourceName;
		}

		public ItemBuilder<I> setCreativeTab()
		{
			item.setCreativeTab(creativeTab);
			return this;
		}

		public ItemBuilder<I> setUnlocalizedName()
		{
			item.setUnlocalizedName(resourceName);
			return this;
		}

		public ItemBuilder<I> setRegistryName()
		{
			item.setRegistryName(modid, resourceName);
			return this;
		}

		public ItemBuilder<I> register()
		{
			ForgeRegistries.ITEMS.register(item);
			return this;
		}

		public ItemBuilder<I> setCustomModelResourceLocation()
		{
			return setCustomModelResourceLocation(0, resourceName);
		}

		public ItemBuilder<I> setCustomModelResourceLocation(int meta, String resourceName)
		{
			if (side.isClient()) ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(modid + ":" + resourceName));
			return this;
		}

		public ItemBuilder<I> process(Consumer<? super ItemBuilder<I>> consumer)
		{
			consumer.accept(this);
			return this;
		}

		public I get()
		{
			return item;
		}

	}

}
