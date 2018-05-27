package miragefairy2018.mod;

import org.apache.logging.log4j.Logger;

import miragefairy2018.mod.fairy.ModuleFairy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModuleMain
{

	public static Logger logger;
	public static Side side;

	public static CreativeTabs creativeTab = new CreativeTabs("MirageFairy2018") {
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return ModuleFairy.mirageFairyAir.getItemStack();
		}
	};

	public static void init(EventRegistryMod erMod)
	{
		erMod.preInit.register(e -> {
			logger = e.getModLog();
			side = e.getSide();
		});
	}

}
