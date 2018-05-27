package miragefairy2018.mod.materials;

import miragefairy2018.lib.matrix.CategoryMatrix;
import miragefairy2018.lib.multi.ItemMulti;
import miragefairy2018.lib.multi.Subtype;
import miragefairy2018.lib.registry.Category;
import miragefairy2018.mod.EventRegistryMod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleMaterials
{

	public static final Category<Shape> shapes = new Category<>();
	public static final Shape dust = shapes.register(new Shape(0, "dust", "dust"));
	public static final Shape dustSmall = shapes.register(new Shape(1, "dustSmall", "small_dust"));
	public static final Shape dustTiny = shapes.register(new Shape(2, "dustTiny", "tiny_dust"));
	public static final Shape dust72 = shapes.register(new Shape(3, "dust72", "dust72"));
	public static final Shape ingot = shapes.register(new Shape(4, "ingot", "ingot"));
	public static final Shape chunk = shapes.register(new Shape(5, "chunk", "chunk"));
	public static final Shape nugget = shapes.register(new Shape(6, "nugget", "nugget"));
	public static final Shape ingot72 = shapes.register(new Shape(7, "ingot72", "ingot72"));
	public static final Shape gem = shapes.register(new Shape(8, "gem", "gem"));

	public static final Category<Material> materials = new Category<>();
	public static final Material miragium = materials.register(new Material(0, "Miragium", "miragium"));
	public static final Material miragonite = materials.register(new Material(1, "Miragonite", "miragonite"));

	public static final CategoryMatrix<Shape, Material> subtypesMaterial = new CategoryMatrix<>(64);
	public static final Subtype dustMiragium = subtypesMaterial.register(dust, miragium);
	public static final Subtype dustTinyMiragium = subtypesMaterial.register(dustTiny, miragium);
	public static final Subtype ingotMiragium = subtypesMaterial.register(ingot, miragium);
	public static final Subtype gemMiragonite = subtypesMaterial.register(gem, miragonite);

	public static ItemMulti itemMaterials;

	//

	public static final Category<Subtype> subItemsMiddleMaterials = new Category<>();
	public static final Subtype elementalEmblemLight = subItemsMiddleMaterials.register(new Subtype(0, "elementalEmblemLight", "light_elemental_emblem"));
	public static final Subtype elementalEmblemWind = subItemsMiddleMaterials.register(new Subtype(1, "elementalEmblemWind", "wind_elemental_emblem"));
	public static final Subtype elementalEmblemWater = subItemsMiddleMaterials.register(new Subtype(2, "elementalEmblemWater", "water_elemental_emblem"));
	public static final Subtype elementalEmblemShadow = subItemsMiddleMaterials.register(new Subtype(3, "elementalEmblemShadow", "shadow_elemental_emblem"));
	public static final Subtype elementalEmblemEarth = subItemsMiddleMaterials.register(new Subtype(4, "elementalEmblemEarth", "earth_elemental_emblem"));
	public static final Subtype elementalEmblemFire = subItemsMiddleMaterials.register(new Subtype(5, "elementalEmblemFire", "fire_elemental_emblem"));

	public static ItemMulti itemMiddleMaterials;

	//

	public static void init(EventRegistryMod erMod)
	{
		erMod.registerItem.register(b -> {

			// 素材アイテム
			itemMaterials = b.new ItemBuilder<>(new ItemMulti(subtypesMaterial), "materials")
				.setRegistryName().setCreativeTab().register().process(b2 -> {
					subtypesMaterial.forEach(s -> {
						b2.setCustomModelResourceLocation(s.id, s.resourceName);
						OreDictionary.registerOre(s.name, s.getItemStack());
					});
				}).get();

			// 中間素材アイテム
			itemMiddleMaterials = b.new ItemBuilder<>(new ItemMulti(subItemsMiddleMaterials), "middle_materials")
				.setRegistryName().setCreativeTab().register().process(b2 -> {
					subItemsMiddleMaterials.forEach(s -> {
						b2.setCustomModelResourceLocation(s.id, s.resourceName);
						OreDictionary.registerOre(s.name, s.getItemStack());
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
