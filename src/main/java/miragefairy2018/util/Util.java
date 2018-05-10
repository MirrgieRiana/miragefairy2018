package miragefairy2018.util;

import java.util.Random;

// TODO mirrg.beryllium
public class Util
{

	public static int randomInt(double d, Random random)
	{
		int i = (int) Math.floor(d);
		double mod = d - i;
		if (random.nextDouble() < mod) i++;
		return i;
	}

}
