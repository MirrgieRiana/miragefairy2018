package miragefairy2018.contents;

import miragefairy2018.ModMirageFairy2018;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSwordMiragium extends ItemSword
{

	public ItemSwordMiragium()
	{
		super(ToolMaterial.IRON);
		setMaxDamage(10);
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
