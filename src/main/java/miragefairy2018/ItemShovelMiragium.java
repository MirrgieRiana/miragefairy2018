package miragefairy2018;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemShovelMiragium extends ItemSpade
{

	public ItemShovelMiragium()
	{
		super(ToolMaterial.IRON);
		setMaxDamage(50);
		efficiency = 2;
	}

	@Override
	public int getItemEnchantability()
	{
		return 50;
	}

	@Override
	public String getToolMaterialName()
	{
		return "MIRAGIUM";
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return OreDictionary.itemMatches(ModMirageFairy2018.ingotMiragium.getItemStack(), repair, false);
	}

}
