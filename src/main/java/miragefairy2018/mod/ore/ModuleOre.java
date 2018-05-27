package miragefairy2018.mod.ore;

import miragefairy2018.mod.EventRegistryMod;
import miragefairy2018.mod.ModMirageFairy2018;
import miragefairy2018.mod.ModuleMain;
import miragefairy2018.mod.material.ModuleMaterial;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleOre
{

	public static BlockOre blockOre;
	public static ItemBlock itemOre;

	public static ItemStack blockOreMiragium;
	public static ItemStack blockOreMiragonite;

	public static void init(EventRegistryMod erMod)
	{
		erMod.registerBlock.register(b -> {

			// ブロック
			blockOre = new BlockOre();
			blockOre.setUnlocalizedName("ore");
			blockOre.setRegistryName(ModMirageFairy2018.MODID, "ore");
			blockOre.setCreativeTab(ModuleMain.creativeTab);
			ForgeRegistries.BLOCKS.register(blockOre);
			if (ModuleMain.side.isClient()) {
				ModelLoader.setCustomStateMapper(
					blockOre,
					new StateMap.Builder().withName(BlockOre.VARIANT).withSuffix("_ore").build());
			}

		});
		erMod.registerItem.register(b -> {

			// アイテム
			itemOre = b.new ItemBuilder<>(new ItemOre(blockOre), "ore")
				.setRegistryName().register().process(b2 -> {
					BlockOre.EnumType.values.forEach(t -> {
						b2.setCustomModelResourceLocation(t.ordinal(), t.resourceName + "_" + b2.resourceName);
						OreDictionary.registerOre(b2.resourceName + t.oreName, blockOre.createItemStack(t));
					});
				}).get();

		});
		erMod.createItemStack.register(b -> {

			blockOreMiragium = blockOre.createItemStack(BlockOre.EnumType.MIRAGIUM);
			blockOreMiragonite = blockOre.createItemStack(BlockOre.EnumType.MIRAGONITE);

		});
		erMod.recipe.register(() -> {

			// 製錬レシピ
			GameRegistry.addSmelting(blockOreMiragium, ModuleMaterial.dustMiragium.getItemStack(), 5);
			GameRegistry.addSmelting(blockOreMiragonite, ModuleMaterial.gemMiragonite.getItemStack(), 0.7f);

		});
	}

}
