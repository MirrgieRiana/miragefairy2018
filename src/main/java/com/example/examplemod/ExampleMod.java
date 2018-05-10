package com.example.examplemod;

import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{

	public static final String MODID = "miragefairy2018";
	public static final String NAME = "MirageFairy2018";
	public static final String VERSION = "0.0.1";

	public static Logger logger;

	public static CreativeTabs creativeTab;

	public static BlockDreamyFlower blockDreamyFlower;
	public static Item itemDreamyFlowerSeeds;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		logger = event.getModLog();

		creativeTab = new CreativeTabs("MirageFairy2018") {
			@Override
			@SideOnly(Side.CLIENT)
			public ItemStack getTabIconItem()
			{
				return new ItemStack(itemDreamyFlowerSeeds);
			}
		};

		blockDreamyFlower = new BlockDreamyFlower();
		blockDreamyFlower.setRegistryName(MODID, "dreamy_flower");
		blockDreamyFlower.setCreativeTab(creativeTab);
		ForgeRegistries.BLOCKS.register(blockDreamyFlower);

		itemDreamyFlowerSeeds = new ItemSeedDreamyFlower();
		itemDreamyFlowerSeeds.setRegistryName(MODID, "dreamy_flower_seeds");
		itemDreamyFlowerSeeds.setCreativeTab(creativeTab);
		itemDreamyFlowerSeeds.setUnlocalizedName("dreamy_flower_seeds");
		ForgeRegistries.ITEMS.register(itemDreamyFlowerSeeds);
		ModelLoader.setCustomModelResourceLocation(itemDreamyFlowerSeeds, 0, new ModelResourceLocation(MODID + ":dreamy_flower_seeds"));

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

}
