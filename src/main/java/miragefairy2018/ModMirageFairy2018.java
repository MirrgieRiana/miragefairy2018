package miragefairy2018;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.apache.logging.log4j.Logger;

import mirrg.beryllium.lang.LambdaUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
	public static final Shape dust = CategoryItem.register(new Shape(0, "dust"), shapes);
	public static final Shape dustSmall = CategoryItem.register(new Shape(1, "dustSmall"), shapes);
	public static final Shape dustTiny = CategoryItem.register(new Shape(2, "dustTiny"), shapes);
	public static final Shape dust72 = CategoryItem.register(new Shape(3, "dust72"), shapes);
	public static final Shape ingot = CategoryItem.register(new Shape(4, "ingot"), shapes);
	public static final Shape chunk = CategoryItem.register(new Shape(5, "chunk"), shapes);
	public static final Shape nugget = CategoryItem.register(new Shape(6, "nugget"), shapes);
	public static final Shape ingot72 = CategoryItem.register(new Shape(7, "ingot72"), shapes);
	public static final Shape gem = CategoryItem.register(new Shape(8, "gem"), shapes);
	public static final Shape sword = CategoryItem.register(new Shape(32, "sword"), shapes);
	public static final Shape pickaxe = CategoryItem.register(new Shape(33, "pickaxe"), shapes);
	public static final Shape axe = CategoryItem.register(new Shape(34, "axe"), shapes);
	public static final Shape shovel = CategoryItem.register(new Shape(35, "shovel"), shapes);
	public static final Shape hoe = CategoryItem.register(new Shape(36, "hoe"), shapes);

	public static final Category<Material> materials = new Category<>();
	public static final Material miragium = CategoryItem.register(new Material(0, "Miragium"), materials);

	public static final Category<SubItem> subItemsMaterial = new Category<>();
	public static final SubItem dustMiragium = CategoryItem.register(new SubItem(dust, miragium), subItemsMaterial);
	public static final SubItem dustTinyMiragium = CategoryItem.register(new SubItem(dustTiny, miragium), subItemsMaterial);
	public static final SubItem ingotMiragium = CategoryItem.register(new SubItem(ingot, miragium), subItemsMaterial);
	public static final SubItem swordMiragium = CategoryItem.register(new SubItem(sword, miragium), subItemsMaterial);
	public static final SubItem pickaxeMiragium = CategoryItem.register(new SubItem(pickaxe, miragium), subItemsMaterial);
	public static final SubItem axeMiragium = CategoryItem.register(new SubItem(axe, miragium), subItemsMaterial);
	public static final SubItem shovelMiragium = CategoryItem.register(new SubItem(shovel, miragium), subItemsMaterial);
	public static final SubItem hoeMiragium = CategoryItem.register(new SubItem(hoe, miragium), subItemsMaterial);

	//

	public static ItemMatrix itemMaterials;

	//

	public static ItemSwordMiragium itemSwordMiragium;
	public static ItemStack itemStackSwordMiragium;

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
			ModelLoader.setCustomModelResourceLocation(itemDreamyFlowerSeeds, 0, new ModelResourceLocation(MODID + ":dreamy_flower_seeds"));

			// スタック追加
			itemStackDreamyFlowerSeeds = new ItemStack(itemDreamyFlowerSeeds, 1, 0);

			// 地形生成
			listenersDecorateBiomeEventPost.add(
				new BiomeDecoratorFlowers(
					LambdaUtil.process(new WorldGenBush(blockDreamyFlower, blockDreamyFlower.getState(3)), worldGenerator -> {
						worldGenerator.blockCountMin = 1;
						worldGenerator.blockCountMax = 3;
					}),
					0.25) {
					@Override
					protected boolean canGenerate(Biome biome)
					{
						return super.canGenerate(biome) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN);
					}
				});

		}

		// 素材マトリクスアイテム
		{

			// 汎用素材アイテム
			itemMaterials = new ItemMatrix(subItemsMaterial);
			itemMaterials.setRegistryName(MODID, "materials");
			itemMaterials.setCreativeTab(creativeTab);
			ForgeRegistries.ITEMS.register(itemMaterials);
			subItemsMaterial.forEach(si -> {
				ModelLoader.setCustomModelResourceLocation(itemMaterials, si.id, new ModelResourceLocation(MODID + ":" + si.getResourceName()));
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
			ModelLoader.setCustomModelResourceLocation(itemSwordMiragium, 0, new ModelResourceLocation(MODID + ":miragium_sword"));

			itemStackSwordMiragium = new ItemStack(itemSwordMiragium, 1, 0);

		}

		// レシピ
		{

			GameRegistry.addSmelting(dustMiragium.getItemStack(), ingotMiragium.getItemStack(), 0);

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

	}

}
