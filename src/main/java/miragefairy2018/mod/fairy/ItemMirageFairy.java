package miragefairy2018.mod.fairy;

import static miragefairy2018.mod.fairy.EnumManaType.*;
import static net.minecraft.util.text.TextFormatting.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import miragefairy2018.lib.category.Category;
import miragefairy2018.lib.multi.ItemMulti;
import miragefairy2018.mod.fairy.EnumMirageFairy.Potential;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMirageFairy extends ItemMulti<SubtypeMirageFairy>
{

	public ItemMirageFairy(Category<SubtypeMirageFairy> subtypes)
	{
		super(subtypes);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		SubtypeMirageFairy subtype = getSubtype(stack).orElse(null);
		if (subtype == null) return;

		Potential potential = subtype.mirageFairy.getPotential();

		// TODO
		String stringRare = IntStream.range(0, subtype.mirageFairy.getRare()).mapToObj(i -> "★").collect(Collectors.joining());
		TextFormatting colorRare;
		switch (subtype.mirageFairy.getRare()) {
			case 1:
				colorRare = GRAY;
				break;
			case 2:
				colorRare = RED;
				break;
			case 3:
				colorRare = BLUE;
				break;
			case 4:
				colorRare = GREEN;
				break;
			case 5:
				colorRare = YELLOW;
				break;
			default:
				colorRare = GRAY;
				break;
		}

		tooltip.add("" + "Type: " + WHITE + subtype.mirageFairy.name() + " " + colorRare + stringRare);
		tooltip.add("      " + format(light, potential));
		tooltip.add("  " + format(fire, potential) + "      " + format(air, potential));
		tooltip.add("  " + format(earth, potential) + "      " + format(water, potential));
		tooltip.add("      " + format(darkness, potential));
		tooltip.add("" + YELLOW + "Cost: " + String.format("%.1f", subtype.mirageFairy.getCost()));

	}

	private String format(EnumManaType manaType, Potential potential)
	{
		double value = potential.get(manaType);
		int value2 = (int) value;
		if (value2 == 0 && value > 0) value2 = 1;
		return "" + manaType.color + TextFormatting.BOLD + String.format("%2d", value2);
	}

}
