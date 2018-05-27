package miragefairy2018.mod.fairy;

import java.util.Optional;

import miragefairy2018.lib.matrix.Category;
import miragefairy2018.lib.matrix.CategoryItem;
import miragefairy2018.lib.matrix.ItemMatrix;
import miragefairy2018.lib.matrix.SubItem;
import miragefairy2018.mod.EventRegistryMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleFairy
{

	private static SubItemMirageFairy rMF(int id, EnumMirageFairy mirageFairy)
	{
		return CategoryItem.register(new SubItemMirageFairy(
			id,
			"mirageFairy" + mirageFairy.oreName,
			mirageFairy.registryName + "_mirage_fairy",
			mirageFairy.colorSet), subItemsMirageFairy);
	}

	public static final Category<SubItemMirageFairy> subItemsMirageFairy = new Category<>();
	public static final SubItemMirageFairy mirageFairyAir = rMF(0, EnumMirageFairy.air);
	public static final SubItemMirageFairy mirageFairyWater = rMF(1, EnumMirageFairy.water);
	public static final SubItemMirageFairy mirageFairyStone = rMF(2, EnumMirageFairy.stone);
	public static final SubItemMirageFairy mirageFairyIron = rMF(3, EnumMirageFairy.iron);
	public static final SubItemMirageFairy mirageFairyDiamond = rMF(4, EnumMirageFairy.diamond);
	public static final SubItemMirageFairy mirageFairyRedstone = rMF(5, EnumMirageFairy.redstone);
	public static final SubItemMirageFairy mirageFairyEnderman = rMF(6, EnumMirageFairy.enderman);
	public static final SubItemMirageFairy mirageFairyLilac = rMF(7, EnumMirageFairy.lilac);
	public static final SubItemMirageFairy mirageFairyTorch = rMF(8, EnumMirageFairy.torch);
	public static final SubItemMirageFairy mirageFairyMoon = rMF(9, EnumMirageFairy.moon);
	public static final SubItemMirageFairy mirageFairyNoon = rMF(10, EnumMirageFairy.noon);
	public static final SubItemMirageFairy mirageFairyPlains = rMF(11, EnumMirageFairy.plains);

	public static ItemMatrix itemMirageFairy;

	public static final Category<SubItem> subItemsMirageWisp = new Category<>();
	public static final SubItem mirageWisp = CategoryItem.register(new SubItem(0, "mirageWisp", "mirage_wisp"), subItemsMirageWisp);

	public static ItemMatrix itemMirageWisp;

	public static void init(EventRegistryMod erMod)
	{
		erMod.registerItem.register(b -> {

			// 妖精
			itemMirageFairy = b.new ItemBuilder<>(new ItemMatrix(subItemsMirageFairy), "mirage_fairy")
				.setRegistryName().setCreativeTab().register().process(b2 -> {
					subItemsMirageFairy.forEach(si -> {
						b2.setCustomModelResourceLocation(si.id, b2.resourceName);
						OreDictionary.registerOre(si.name, si.getItemStack());
						OreDictionary.registerOre("mirageFairy", si.getItemStack());
					});
				}).get();

			// ウィスプ
			itemMirageWisp = b.new ItemBuilder<>(new ItemMatrix(subItemsMirageWisp), "mirage_wisp")
				.setRegistryName().setCreativeTab().register().process(b2 -> {
					subItemsMirageWisp.forEach(si -> {
						b2.setCustomModelResourceLocation(si.id, b2.resourceName);
						OreDictionary.registerOre(si.name, si.getItemStack());
					});
				}).get();

		});
		erMod.registerItemColorHandler.register(() -> {

			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex)
				{
					return subItemsMirageFairy.get(stack.getMetadata())
						.flatMap(si -> {
							if (tintIndex == 0) return Optional.of(si.colorSet.skin);
							if (tintIndex == 1) return Optional.of(0x8888ff);
							if (tintIndex == 2) return Optional.of(si.colorSet.darker);
							if (tintIndex == 3) return Optional.of(si.colorSet.brighter);
							if (tintIndex == 4) return Optional.of(si.colorSet.hair);
							return Optional.empty();
						})
						.orElse(0xffffff);
				}
			}, itemMirageFairy);

			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex)
				{
					if (stack.getMetadata() == 0) {
						if (tintIndex == 0) return 0xddddff;
						if (tintIndex == 1) return 0xddddff;
						if (tintIndex == 2) return 0xddddff;
						if (tintIndex == 3) return 0xddddff;
					}
					return 0xffffff;
				}
			}, itemMirageWisp);

		});
	}

}
