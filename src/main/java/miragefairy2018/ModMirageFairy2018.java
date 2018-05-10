package miragefairy2018;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.apache.logging.log4j.Logger;

import mirrg.beryllium.lang.LambdaUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ModMirageFairy2018.MODID, name = ModMirageFairy2018.NAME, version = ModMirageFairy2018.VERSION)
public class ModMirageFairy2018
{

	public static final String MODID = "miragefairy2018";
	public static final String NAME = "MirageFairy2018";
	public static final String VERSION = "0.0.1";

	public static Logger logger;

	public static CreativeTabs creativeTab;

	public static BlockDreamyFlower blockDreamyFlower;
	public static Item itemDreamyFlowerSeeds;

	public static ItemStack itemStackDreamyFlowerSeeds;

	public static ArrayList<Consumer<DecorateBiomeEvent.Post>> listenersDecorateBiomeEventPost = new ArrayList<>();

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

			// 種登録
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
