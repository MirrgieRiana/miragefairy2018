package miragefairy2018;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.apache.logging.log4j.Logger;

import mirrg.beryllium.lang.LambdaUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = ModMirageFairy2018.MODID, name = ModMirageFairy2018.NAME, version = ModMirageFairy2018.VERSION)
public class ModMirageFairy2018
{

	public static final String MODID = "miragefairy2018";
	public static final String NAME = "MirageFairy2018";
	public static final String VERSION = "0.0.1";

	public static Logger logger;

	public static CreativeTabs creativeTab;

	public static ArrayList<Consumer<DecorateBiomeEvent.Post>> listenersDecorateBiomeEventPost = new ArrayList<>();

	//

	public static BlockDreamyFlower blockDreamyFlower;
	public static ItemSeedDreamyFlower itemDreamyFlowerSeeds;
	public static ItemStack itemStackDreamyFlowerSeeds;

	//

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

	//

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

	//

	public static final Category<SubItem> subItemsMirageFairy = new Category<>();
	public static final SubItem mirageFairyTorch = CategoryItem.register(new SubItem(0, "mirageFairyTorch", "torch_mirage_fairy"), subItemsMirageFairy);
	public static final SubItem mirageFairyFeather = CategoryItem.register(new SubItem(1, "mirageFairyFeather", "feather_mirage_fairy"), subItemsMirageFairy);
	public static ItemMatrix itemMirageFairy;

	public static final Category<SubItem> subItemsMirageWisp = new Category<>();
	public static final SubItem mirageWispTier1 = CategoryItem.register(new SubItem(0, "mirageWispTier1", "tier1_mirage_wisp"), subItemsMirageWisp);
	public static ItemMatrix itemMirageWisp;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		logger = event.getModLog();

		// クリエイティブタブ登録
		creativeTab = new CreativeTabs("MirageFairy2018") {
			@Override
			@SideOnly(Side.CLIENT)
			public ItemStack getTabIconItem()
			{
				return new ItemStack(itemDreamyFlowerSeeds);
			}
		};

		ItemBuilder itemBuilder = new ItemBuilder(MODID, event.getSide(), creativeTab);

		// ドリーミーフラワー関連
		{

			// ブロック
			blockDreamyFlower = new BlockDreamyFlower();
			blockDreamyFlower.setRegistryName(MODID, "dreamy_flower");
			blockDreamyFlower.setCreativeTab(creativeTab);
			ForgeRegistries.BLOCKS.register(blockDreamyFlower);

			// 種
			itemDreamyFlowerSeeds = new ItemSeedDreamyFlower();
			itemDreamyFlowerSeeds.setRegistryName(MODID, "dreamy_flower_seeds");
			itemDreamyFlowerSeeds.setCreativeTab(creativeTab);
			itemDreamyFlowerSeeds.setUnlocalizedName("dreamy_flower_seeds");
			ForgeRegistries.ITEMS.register(itemDreamyFlowerSeeds);
			if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemDreamyFlowerSeeds, 0, new ModelResourceLocation(MODID + ":dreamy_flower_seeds"));

			// スタック追加
			itemStackDreamyFlowerSeeds = new ItemStack(itemDreamyFlowerSeeds, 1, 0);

			// 地形生成
			listenersDecorateBiomeEventPost.add(
				new BiomeDecoratorFlowers(
					LambdaUtil.process(new WorldGenBush(blockDreamyFlower, blockDreamyFlower.getState(3)), worldGenerator -> {
						worldGenerator.blockCountMin = 1;
						worldGenerator.blockCountMax = 5;
					}),
					0.25) {
					@Override
					protected boolean canGenerate(Biome biome)
					{
						return super.canGenerate(biome) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN);
					}
				});
			listenersDecorateBiomeEventPost.add(
				new BiomeDecoratorFlowers(
					LambdaUtil.process(new WorldGenBush(blockDreamyFlower, blockDreamyFlower.getState(3)), worldGenerator -> {
						worldGenerator.blockCountMin = 1;
						worldGenerator.blockCountMax = 3;
					}),
					0.025));

		}

		// 素材マトリクスアイテム
		{

			// 汎用素材アイテム
			itemMaterials = new ItemMatrix(subItemsMaterial);
			itemMaterials.setRegistryName(MODID, "materials");
			itemMaterials.setCreativeTab(creativeTab);
			ForgeRegistries.ITEMS.register(itemMaterials);
			subItemsMaterial.forEach(si -> {
				if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemMaterials, si.id, new ModelResourceLocation(MODID + ":" + si.resourceName));
				OreDictionary.registerOre(si.name, si.getItemStack());
			});

		}

		// ミラージュ製ツール
		{

			itemSwordMiragium = new ItemSwordMiragium();
			itemSwordMiragium.setRegistryName(MODID, "miragium_sword");
			itemSwordMiragium.setCreativeTab(creativeTab);
			itemSwordMiragium.setUnlocalizedName("miragium_sword");
			ForgeRegistries.ITEMS.register(itemSwordMiragium);
			if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemSwordMiragium, 0, new ModelResourceLocation(MODID + ":miragium_sword"));
			itemStackSwordMiragium = new ItemStack(itemSwordMiragium, 1, 0);

			itemPickaxeMiragium = new ItemPickaxeMiragium();
			itemPickaxeMiragium.setRegistryName(MODID, "miragium_pickaxe");
			itemPickaxeMiragium.setCreativeTab(creativeTab);
			itemPickaxeMiragium.setUnlocalizedName("miragium_pickaxe");
			ForgeRegistries.ITEMS.register(itemPickaxeMiragium);
			if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemPickaxeMiragium, 0, new ModelResourceLocation(MODID + ":miragium_pickaxe"));
			itemStackPickaxeMiragium = new ItemStack(itemPickaxeMiragium, 1, 0);

			itemAxeMiragium = new ItemAxeMiragium();
			itemAxeMiragium.setRegistryName(MODID, "miragium_axe");
			itemAxeMiragium.setCreativeTab(creativeTab);
			itemAxeMiragium.setUnlocalizedName("miragium_axe");
			ForgeRegistries.ITEMS.register(itemAxeMiragium);
			if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemAxeMiragium, 0, new ModelResourceLocation(MODID + ":miragium_axe"));
			itemStackAxeMiragium = new ItemStack(itemAxeMiragium, 1, 0);

			itemShovelMiragium = new ItemShovelMiragium();
			itemShovelMiragium.setRegistryName(MODID, "miragium_shovel");
			itemShovelMiragium.setCreativeTab(creativeTab);
			itemShovelMiragium.setUnlocalizedName("miragium_shovel");
			ForgeRegistries.ITEMS.register(itemShovelMiragium);
			if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemShovelMiragium, 0, new ModelResourceLocation(MODID + ":miragium_shovel"));
			itemStackShovelMiragium = new ItemStack(itemShovelMiragium, 1, 0);

			itemHoeMiragium = new ItemHoeMiragium();
			itemHoeMiragium.setRegistryName(MODID, "miragium_hoe");
			itemHoeMiragium.setCreativeTab(creativeTab);
			itemHoeMiragium.setUnlocalizedName("miragium_hoe");
			ForgeRegistries.ITEMS.register(itemHoeMiragium);
			if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemHoeMiragium, 0, new ModelResourceLocation(MODID + ":miragium_hoe"));
			itemStackHoeMiragium = new ItemStack(itemHoeMiragium, 1, 0);

		}

		// 妖精関連
		{

			// 妖精
			itemMirageFairy = new ItemMatrix(subItemsMirageFairy);
			itemMirageFairy.setRegistryName(MODID, "mirage_fairy");
			itemMirageFairy.setCreativeTab(creativeTab);
			ForgeRegistries.ITEMS.register(itemMirageFairy);
			subItemsMirageFairy.forEach(si -> {
				if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemMirageFairy, si.id, new ModelResourceLocation(MODID + ":mirage_fairy"));
				OreDictionary.registerOre(si.name, si.getItemStack());
				OreDictionary.registerOre("mirageFairy", si.getItemStack());
			});

			// ウィスプ
			itemMirageWisp = new ItemMatrix(subItemsMirageWisp);
			itemMirageWisp.setRegistryName(MODID, "mirage_wisp");
			itemMirageWisp.setCreativeTab(creativeTab);
			ForgeRegistries.ITEMS.register(itemMirageWisp);
			subItemsMirageWisp.forEach(si -> {
				if (event.getSide().isClient()) ModelLoader.setCustomModelResourceLocation(itemMirageWisp, si.id, new ModelResourceLocation(MODID + ":mirage_wisp"));
				OreDictionary.registerOre(si.name, si.getItemStack());
			});

		}

		// レシピ
		{

			GameRegistry.addSmelting(dustMiragium.getItemStack(), ingotMiragium.getItemStack(), 0);
			GameRegistry.addSmelting(gemMiragonite.getItemStack(), dustTinyMiragium.getItemStack(), 0);

		}

		MinecraftForge.EVENT_BUS.register(new Object() {
			@SubscribeEvent
			public void onDecorateBiomeEventPost(DecorateBiomeEvent.Post event)
			{
				listenersDecorateBiomeEventPost.forEach(l -> l.accept(event));
			}
		});

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

		if (event.getSide().isClient()) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex)
				{
					if (stack.getMetadata() == 0) {
						if (tintIndex == 0) return 0x957546;
						if (tintIndex == 1) return 0x8888ff;
						if (tintIndex == 2) return 0xFFB800;
						if (tintIndex == 3) return 0xFFC42C;
						if (tintIndex == 4) return 0xFFF8C3;
					}
					if (stack.getMetadata() == 1) {
						if (tintIndex == 0) return 0xFFBE80;
						if (tintIndex == 1) return 0x8888ff;
						if (tintIndex == 2) return 0xAAAAAA;
						if (tintIndex == 3) return 0xFFFFFF;
						if (tintIndex == 4) return 0x585858;
					}
					return 0xffffff;
				}
			}, itemMirageFairy);
		}

		if (event.getSide().isClient()) {
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
		}

	}

}
