package miragefairy2018.mod.dreamyflower;

import miragefairy2018.lib.BiomeDecoratorFlowers;
import miragefairy2018.lib.WorldGenBush;
import miragefairy2018.mod.EventRegistryMod;
import miragefairy2018.mod.ModMirageFairy2018;
import miragefairy2018.mod.ModuleMain;
import mirrg.beryllium.lang.LambdaUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModuleDreamyFlower
{

	public static BlockDreamyFlower blockDreamyFlower;
	public static ItemSeedDreamyFlower itemDreamyFlowerSeeds;

	public static ItemStack itemStackDreamyFlowerSeeds;

	public static void init(EventRegistryMod erMod)
	{
		erMod.registerBlock.register(b -> {

			// ブロック
			blockDreamyFlower = new BlockDreamyFlower();
			blockDreamyFlower.setRegistryName(ModMirageFairy2018.MODID, "dreamy_flower");
			blockDreamyFlower.setCreativeTab(ModuleMain.creativeTab);
			ForgeRegistries.BLOCKS.register(blockDreamyFlower);

		});
		erMod.registerItem.register(b -> {

			// 種
			itemDreamyFlowerSeeds = b.new ItemBuilder<>(new ItemSeedDreamyFlower(), "dreamy_flower_seeds")
				.setRegistryName().setCreativeTab().setUnlocalizedName().register().setCustomModelResourceLocation().get();

		});
		erMod.createItemStack.register(b -> {

			// 種
			itemStackDreamyFlowerSeeds = new ItemStack(itemDreamyFlowerSeeds);

		});
		erMod.decorate.register(() -> {

			// 地形生成

			BiomeDecoratorFlowers decoratorDreamyFlower = new BiomeDecoratorFlowers(
				LambdaUtil.process(new WorldGenBush(blockDreamyFlower, blockDreamyFlower.getState(3)), worldGenerator1 -> {
					worldGenerator1.blockCountMin = 1;
					worldGenerator1.blockCountMax = 3;
				}),
				0.025);
			BiomeDecoratorFlowers decoratorDreamyFlowerMountain = new BiomeDecoratorFlowers(
				LambdaUtil.process(new WorldGenBush(blockDreamyFlower, blockDreamyFlower.getState(3)), worldGenerator2 -> {
					worldGenerator2.blockCountMin = 1;
					worldGenerator2.blockCountMax = 5;
				}),
				0.25) {
				@Override
				protected boolean canGenerate(Biome biome)
				{
					return super.canGenerate(biome) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN);
				}
			};

			MinecraftForge.EVENT_BUS.register(new Object() {
				@SubscribeEvent
				public void onDecorateBiomeEventPost(DecorateBiomeEvent.Post event)
				{
					decoratorDreamyFlower.decorate(event);
					decoratorDreamyFlowerMountain.decorate(event);
				}
			});

		});
	}

}
