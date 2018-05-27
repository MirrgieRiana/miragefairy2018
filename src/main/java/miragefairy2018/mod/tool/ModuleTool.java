package miragefairy2018.mod.tool;

import miragefairy2018.mod.EventRegistryMod;
import net.minecraft.item.ItemStack;

public class ModuleTool
{

	public static ItemSwordMiragium itemSwordMiragium;
	public static ItemPickaxeMiragium itemPickaxeMiragium;
	public static ItemAxeMiragium itemAxeMiragium;
	public static ItemShovelMiragium itemShovelMiragium;
	public static ItemHoeMiragium itemHoeMiragium;

	public static ItemStack itemStackSwordMiragium;
	public static ItemStack itemStackPickaxeMiragium;
	public static ItemStack itemStackAxeMiragium;
	public static ItemStack itemStackShovelMiragium;
	public static ItemStack itemStackHoeMiragium;

	public static void init(EventRegistryMod erMod)
	{
		erMod.registerItem.register(b -> {

			itemSwordMiragium = b.new ItemBuilder<>(new ItemSwordMiragium(), "miragium_sword")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().get();

			itemPickaxeMiragium = b.new ItemBuilder<>(new ItemPickaxeMiragium(), "miragium_pickaxe")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().get();

			itemAxeMiragium = b.new ItemBuilder<>(new ItemAxeMiragium(), "miragium_axe")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().get();

			itemShovelMiragium = b.new ItemBuilder<>(new ItemShovelMiragium(), "miragium_shovel")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().get();

			itemHoeMiragium = b.new ItemBuilder<>(new ItemHoeMiragium(), "miragium_hoe")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().get();

		});
		erMod.createItemStack.register(b -> {

			itemStackSwordMiragium = new ItemStack(itemSwordMiragium);
			itemStackPickaxeMiragium = new ItemStack(itemPickaxeMiragium);
			itemStackAxeMiragium = new ItemStack(itemAxeMiragium);
			itemStackShovelMiragium = new ItemStack(itemShovelMiragium);
			itemStackHoeMiragium = new ItemStack(itemHoeMiragium);

		});
	}

}
