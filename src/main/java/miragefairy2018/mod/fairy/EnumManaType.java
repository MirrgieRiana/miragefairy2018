package miragefairy2018.mod.fairy;

import net.minecraft.util.text.TextFormatting;

public enum EnumManaType
{
	light(TextFormatting.WHITE),
	air(TextFormatting.GREEN),
	water(TextFormatting.BLUE),
	darkness(TextFormatting.DARK_GRAY),
	earth(TextFormatting.YELLOW),
	fire(TextFormatting.RED),
	;

	public final TextFormatting color;

	private EnumManaType(TextFormatting color)
	{
		this.color = color;
	}

}
