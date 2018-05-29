package miragefairy2018.mod.fairy;

import java.util.Optional;

import miragefairy2018.lib.multi.ItemMulti;
import miragefairy2018.lib.multi.Subtype;
import miragefairy2018.lib.registry.Category;
import miragefairy2018.mod.EventRegistryMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleFairy
{

	private static SubtypeMirageFairy rMF(int id, EnumMirageFairy mirageFairy)
	{
		return subtypesMirageFairy.register(new SubtypeMirageFairy(
			id,
			"mirageFairy" + mirageFairy.oreName,
			mirageFairy.registryName + "_mirage_fairy",
			mirageFairy));
	}

	public static final Category<SubtypeMirageFairy> subtypesMirageFairy = new Category<>();
	public static final SubtypeMirageFairy mirageFairyAir = rMF(0, EnumMirageFairy.air);
	public static final SubtypeMirageFairy mirageFairyWater = rMF(1, EnumMirageFairy.water);
	public static final SubtypeMirageFairy mirageFairyStone = rMF(2, EnumMirageFairy.stone);
	public static final SubtypeMirageFairy mirageFairyIron = rMF(3, EnumMirageFairy.iron);
	public static final SubtypeMirageFairy mirageFairyDiamond = rMF(4, EnumMirageFairy.diamond);
	public static final SubtypeMirageFairy mirageFairyRedstone = rMF(5, EnumMirageFairy.redstone);
	public static final SubtypeMirageFairy mirageFairyEnderman = rMF(6, EnumMirageFairy.enderman);
	public static final SubtypeMirageFairy mirageFairyLilac = rMF(7, EnumMirageFairy.lilac);
	public static final SubtypeMirageFairy mirageFairyTorch = rMF(8, EnumMirageFairy.torch);
	public static final SubtypeMirageFairy mirageFairyMoon = rMF(9, EnumMirageFairy.moon);
	public static final SubtypeMirageFairy mirageFairyNoon = rMF(10, EnumMirageFairy.noon);
	public static final SubtypeMirageFairy mirageFairyPlains = rMF(11, EnumMirageFairy.plains);

	public static ItemMirageFairy itemMirageFairy;

	//

	public static final Category<Subtype> subtypesMirageWisp = new Category<>();
	public static final Subtype mirageWisp = subtypesMirageWisp.register(new Subtype(0, "mirageWisp", "mirage_wisp"));

	public static ItemMulti<Subtype> itemMirageWisp;

	//

	public static void init(EventRegistryMod erMod)
	{
		erMod.registerItem.register(b -> {

			// 妖精
			itemMirageFairy = b.new ItemBuilder<>(new ItemMirageFairy(subtypesMirageFairy), "mirage_fairy")
				.setRegistryName().setCreativeTab().register().process(b2 -> {
					subtypesMirageFairy.forEach(s -> {
						b2.setCustomModelResourceLocation(s.id, b2.resourceName);
						OreDictionary.registerOre(s.name, s.getItemStack());
						OreDictionary.registerOre("mirageFairy", s.getItemStack());
					});
				}).get();

			// ウィスプ
			itemMirageWisp = b.new ItemBuilder<>(new ItemMulti<>(subtypesMirageWisp), "mirage_wisp")
				.setRegistryName().setCreativeTab().register().process(b2 -> {
					subtypesMirageWisp.forEach(s -> {
						b2.setCustomModelResourceLocation(s.id, b2.resourceName);
						OreDictionary.registerOre(s.name, s.getItemStack());
					});
				}).get();

		});
		erMod.registerItemColorHandler.register(() -> {

			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex)
				{
					return subtypesMirageFairy.get(stack.getMetadata())
						.flatMap(s -> {
							if (tintIndex == 0) return Optional.of(s.mirageFairy.colorSet.skin);
							if (tintIndex == 1) return Optional.of(0x8888ff);
							if (tintIndex == 2) return Optional.of(s.mirageFairy.colorSet.darker);
							if (tintIndex == 3) return Optional.of(s.mirageFairy.colorSet.brighter);
							if (tintIndex == 4) return Optional.of(s.mirageFairy.colorSet.hair);
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
