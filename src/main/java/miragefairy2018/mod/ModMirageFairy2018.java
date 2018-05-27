package miragefairy2018.mod;

import miragefairy2018.lib.util.Builder;
import miragefairy2018.mod.dreamyflower.ModuleDreamyFlower;
import miragefairy2018.mod.fairy.ModuleFairy;
import miragefairy2018.mod.material.ModuleMaterial;
import miragefairy2018.mod.ore.ModuleOre;
import miragefairy2018.mod.tool.ModuleTool;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModMirageFairy2018.MODID, name = ModMirageFairy2018.NAME, version = ModMirageFairy2018.VERSION)
public class ModMirageFairy2018
{

	public static final String MODID = "miragefairy2018";
	public static final String NAME = "MirageFairy2018";
	public static final String VERSION = "0.0.1";

	public EventRegistryMod erMod = new EventRegistryMod();

	public ModMirageFairy2018()
	{
		ModuleMain.init(erMod);
		ModuleMaterial.init(erMod);
		ModuleOre.init(erMod);
		ModuleTool.init(erMod);
		ModuleDreamyFlower.init(erMod);
		ModuleFairy.init(erMod);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		erMod.preInit.accept(event);

		Builder builder = new Builder(MODID, event.getSide(), ModuleMain.creativeTab);

		erMod.registerItem.accept(builder);

		erMod.registerBlock.accept(builder);

		erMod.createItemStack.accept(builder);

		erMod.decorate.run();

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

		erMod.init.accept(event);

		erMod.recipe.run();

		if (event.getSide().isClient()) erMod.registerItemColorHandler.run();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

		erMod.postInit.accept(event);

	}

}
