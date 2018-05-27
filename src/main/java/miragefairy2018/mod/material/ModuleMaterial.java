package miragefairy2018.mod.material;

import miragefairy2018.lib.matrix.Category;
import miragefairy2018.lib.matrix.CategoryItem;
import miragefairy2018.lib.matrix.ItemMatrix;
import miragefairy2018.lib.matrix.Material;
import miragefairy2018.lib.matrix.Shape;
import miragefairy2018.lib.matrix.SubItem;
import miragefairy2018.lib.matrix.SubItemMatrix;
import miragefairy2018.mod.EventRegistryMod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleMaterial
{

	public static final Category<Shape> shapes = new Category<>();
	public static final Shape dust = CategoryItem.register(new Shape(0, "dust", "dust"), shapes);
	public static final Shape dustSmall = CategoryItem.register(new Shape(1, "dustSmall", "small_dust"), shapes);
	public static final Shape dustTiny = CategoryItem.register(new Shape(2, "dustTiny", "tiny_dust"), shapes);
	public static final Shape dust72 = CategoryItem.register(new Shape(3, "dust72", "dust72"), shapes);
	public static final Shape ingot = CategoryItem.register(new Shape(4, "ingot", "ingot"), shapes);
	public static final Shape chunk = CategoryItem.register(new Shape(5, "chunk", "chunk"), shapes);
	public static final Shape nugget = CategoryItem.register(new Shape(6, "nugget", "nugget"), shapes);
	public static final Shape ingot72 = CategoryItem.register(new Shape(7, "ingot72", "ingot72"), shapes);
	public static final Shape gem = CategoryItem.register(new Shape(8, "gem", "gem"), shapes);

	public static final Category<Material> materials = new Category<>();
	public static final Material miragium = CategoryItem.register(new Material(0, "Miragium", "miragium"), materials);
	public static final Material miragonite = CategoryItem.register(new Material(1, "Miragonite", "miragonite"), materials);

	public static final Category<SubItem> subItemsMaterial = new Category<>();
	public static final SubItem dustMiragium = CategoryItem.register(new SubItemMatrix(dust, miragium), subItemsMaterial);
	public static final SubItem dustTinyMiragium = CategoryItem.register(new SubItemMatrix(dustTiny, miragium), subItemsMaterial);
	public static final SubItem ingotMiragium = CategoryItem.register(new SubItemMatrix(ingot, miragium), subItemsMaterial);
	public static final SubItem gemMiragonite = CategoryItem.register(new SubItemMatrix(gem, miragonite), subItemsMaterial);
	public static final SubItem elementalEmblemLight = CategoryItem.register(new SubItem(64 * 64 + 0, "elementalEmblemLight", "light_elemental_emblem"), subItemsMaterial);
	public static final SubItem elementalEmblemFire = CategoryItem.register(new SubItem(64 * 64 + 1, "elementalEmblemFire", "fire_elemental_emblem"), subItemsMaterial);
	public static final SubItem elementalEmblemWind = CategoryItem.register(new SubItem(64 * 64 + 2, "elementalEmblemWind", "wind_elemental_emblem"), subItemsMaterial);
	public static final SubItem elementalEmblemEarth = CategoryItem.register(new SubItem(64 * 64 + 3, "elementalEmblemEarth", "earth_elemental_emblem"), subItemsMaterial);
	public static final SubItem elementalEmblemWater = CategoryItem.register(new SubItem(64 * 64 + 4, "elementalEmblemWater", "water_elemental_emblem"), subItemsMaterial);
	public static final SubItem elementalEmblemShadow = CategoryItem.register(new SubItem(64 * 64 + 5, "elementalEmblemShadow", "shadow_elemental_emblem"), subItemsMaterial);

	public static ItemMatrix itemMaterials;

	public static void init(EventRegistryMod erMod)
	{
		erMod.registerItem.register(b -> {

			// 汎用素材アイテム
			itemMaterials = b.new ItemBuilder<>(new ItemMatrix(subItemsMaterial), "materials")
				.setRegistryName().setCreativeTab().register().process(b2 -> {
					subItemsMaterial.forEach(si -> {
						b2.setCustomModelResourceLocation(si.id, si.resourceName);
						OreDictionary.registerOre(si.name, si.getItemStack());
					});
				}).get();

		});
		erMod.recipe.register(() -> {

			// 製錬レシピ
			GameRegistry.addSmelting(dustMiragium.getItemStack(), ingotMiragium.getItemStack(), 0);
			GameRegistry.addSmelting(gemMiragonite.getItemStack(), dustTinyMiragium.getItemStack(), 0);

		});
	}

}
