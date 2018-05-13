package miragefairy2018;

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

	public ItemBuilder(String modid, Side side)
	{
		this.modid = modid;
		this.side = side;
	}

	public class Builder<I extends Item>
	{

		public final I item;
		public final String oreName;
		public final String resourceName;

		public Builder(I item, String oreName, String resourceName)
		{
			this.item = item;
			this.oreName = oreName;
			this.resourceName = resourceName;
		}

		public Builder<I> register()
		{
			ForgeRegistries.ITEMS.register(item);
			return this;
		}

		public Builder<I> setCreativeTab(CreativeTabs creativeTab)
		{
			item.setCreativeTab(creativeTab);
			return this;
		}

		public Builder<I> setUnlocalizedName()
		{
			item.setUnlocalizedName(resourceName);
			return this;
		}

		public Builder<I> setCustomModelResourceLocation()
		{
			if (side.isClient()) ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(modid + ":" + resourceName));
			return this;
		}

		public Builder<I> setRegistryName()
		{
			item.setRegistryName(modid, resourceName);
			return this;
		}

		public ItemStack getItemStack()
		{
			return new ItemStack(item);
		}

	}

}
