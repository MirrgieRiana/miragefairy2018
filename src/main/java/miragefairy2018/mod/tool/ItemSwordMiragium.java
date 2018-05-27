package miragefairy2018.mod.tool;

import miragefairy2018.mod.materials.ModuleMaterials;
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
		return OreDictionary.itemMatches(ModuleMaterials.ingotMiragium.getItemStack(), repair, false);
	}

}
