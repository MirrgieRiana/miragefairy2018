package miragefairy2018.mod.tool;

import miragefairy2018.mod.material.ModuleMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemAxeMiragium extends ItemAxe
{

	public ItemAxeMiragium()
	{
		super(ToolMaterial.IRON);
		setMaxDamage(10);
		efficiency = 1;
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
		return OreDictionary.itemMatches(ModuleMaterial.ingotMiragium.getItemStack(), repair, false);
	}

}
