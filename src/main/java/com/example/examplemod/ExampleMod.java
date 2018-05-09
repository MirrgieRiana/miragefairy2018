package com.example.examplemod;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{

	public static final String MODID = "miragefairy2018";
	public static final String NAME = "MirageFairy2018";
	public static final String VERSION = "0.0.1";

	private static Logger logger;

	public static CreativeTabs creativeTab;

	public static Block blockDreamyFlower;
	public static ItemBlock itemDreamyFlower;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		logger = event.getModLog();

		creativeTab = new CreativeTabs("MirageFairy2018") {
			@Override
			public ItemStack getTabIconItem()
			{
				return new ItemStack(itemDreamyFlower);
			}
		};

		blockDreamyFlower = new BlockDreamyFlower();
		blockDreamyFlower.setRegistryName(MODID, "dreamy_flower");
		blockDreamyFlower.setCreativeTab(creativeTab);
		ForgeRegistries.BLOCKS.register(blockDreamyFlower);

		itemDreamyFlower = new ItemBlock(blockDreamyFlower);
		itemDreamyFlower.setRegistryName(MODID, "dreamy_flower");
		itemDreamyFlower.setCreativeTab(creativeTab);
		itemDreamyFlower.setUnlocalizedName("dreamy_flower");
		ForgeRegistries.ITEMS.register(itemDreamyFlower);

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

}
