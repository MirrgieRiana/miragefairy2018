package miragefairy2018;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.apache.logging.log4j.Logger;

import mirrg.beryllium.lang.LambdaUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
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
	public static final SubItem mirageWisp = CategoryItem.register(new SubItem(0, "mirageWisp", "mirage_wisp"), subItemsMirageWisp);
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
			itemStackDreamyFlowerSeeds = itemBuilder.builder(itemDreamyFlowerSeeds = new ItemSeedDreamyFlower(), "dreamy_flower_seeds")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().getItemStack();

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
			itemBuilder.builder(itemMaterials = new ItemMatrix(subItemsMaterial), "materials")
				.setRegistryName().setCreativeTab().register().process(b -> {
					subItemsMaterial.forEach(si -> {
						b.setCustomModelResourceLocation(si.id, si.resourceName);
						OreDictionary.registerOre(si.name, si.getItemStack());
					});
				});

		}

		// ミラージュ製ツール
		{

			itemStackSwordMiragium = itemBuilder.builder(itemSwordMiragium = new ItemSwordMiragium(), "miragium_sword")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().getItemStack();

			itemStackPickaxeMiragium = itemBuilder.builder(itemPickaxeMiragium = new ItemPickaxeMiragium(), "miragium_pickaxe")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().getItemStack();

			itemStackAxeMiragium = itemBuilder.builder(itemAxeMiragium = new ItemAxeMiragium(), "miragium_axe")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().getItemStack();

			itemStackShovelMiragium = itemBuilder.builder(itemShovelMiragium = new ItemShovelMiragium(), "miragium_shovel")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().getItemStack();

			itemStackHoeMiragium = itemBuilder.builder(itemHoeMiragium = new ItemHoeMiragium(), "miragium_hoe")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().getItemStack();

		}

		// 妖精関連
		{

			// 妖精
			itemBuilder.builder(itemMirageFairy = new ItemMatrix(subItemsMirageFairy), "mirage_fairy")
				.setRegistryName().setCreativeTab().register().process(b -> {
					subItemsMirageFairy.forEach(si -> {
						b.setCustomModelResourceLocation(si.id, b.resourceName);
						OreDictionary.registerOre(si.name, si.getItemStack());
						OreDictionary.registerOre("mirageFairy", si.getItemStack());
					});
				});

			// ウィスプ
			itemBuilder.builder(itemMirageWisp = new ItemMatrix(subItemsMirageWisp), "mirage_wisp")
				.setRegistryName().setCreativeTab().register().process(b -> {
					subItemsMirageWisp.forEach(si -> {
						b.setCustomModelResourceLocation(si.id, b.resourceName);
						OreDictionary.registerOre(si.name, si.getItemStack());
					});
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
