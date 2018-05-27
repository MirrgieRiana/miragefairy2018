package miragefairy2018.mod.ore;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOre extends ItemBlock
{

	public ItemOre(BlockOre block)
	{
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return BlockOre.EnumType.meta(meta);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "tile." + BlockOre.EnumType.type(stack.getMetadata()).resourceName + "_ore";
	}

}
