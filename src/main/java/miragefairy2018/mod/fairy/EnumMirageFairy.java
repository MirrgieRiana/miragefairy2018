package miragefairy2018.mod.fairy;

public enum EnumMirageFairy
{
	air(fc(0xFFBE80, 0xDEFFFF, 0xDEFFFF, 0xB0FFFF)),
	water(fc(0x5469F2, 0x172AD3, 0x5985FF, 0x2D40F4)),
	stone(fc(0x333333, 0x686868, 0x8F8F8F, 0x747474)),
	iron(fc(0xA0A0A0, 0x727272, 0xD8D8D8, 0xD8AF93)),
	diamond(fc(0x97FFE3, 0x70FFD9, 0xD1FAF3, 0x30DBBD)),
	redstone(fc(0xFF5959, 0xCD0000, 0xFF0000, 0xBA0000)),
	enderman(fc(0x000000, 0x161616, 0x161616, 0xEF84FA)),
	lilac(fc(0x63D700, 0xDC8CE6, 0xF0C9FF, 0xA22CFF)),
	torch(fc(0x957546, 0xFF5800, 0xFFC52C, 0xFFE6A5)),
	moon(fc(0xD9E4FF, 0x0C121F, 0x747D93, 0x2D4272)),
	noon(fc(0xFFE260, 0x84B5EF, 0xAACAEF, 0xffe7b2)),
	plains(fc(0x80FF00, 0x86C91C, 0xD4FF82, 0xBB5400)),

	// # 流体

	lava(fc(0xCD4208, 0xCC4108, 0xEDB54A, 0x4C1500)),

	fire(fc(0xFF6C01, 0xFF7324, 0xF9DFA4, 0xFF4000)),

	// # 環境

	sun(fc(0xff2f00, 0xff7500, 0xff972b, 0xffe7b2)),
	star(fc(0xffffff, 0x0E0E10, 0x2C2C2E, 0x191919)),

	night(fc(0xFFE260, 0x0E0E10, 0x2C2C2E, 0x2D4272)),

	sunny(fc(0xB4FFFF, 0x84B5EF, 0xAACAEF, 0xffe7b2)),
	rain(fc(0xB4FFFF, 0x4D5670, 0x4D5670, 0x2D40F4)),
	thunder(fc(0xB4FFFF, 0x4D5670, 0x4D5670, 0xFFEB00)),
	snowy(fc(0xB4FFFF, 0x4D5670, 0x4D5670, 0xffffff)),

	ocean(fc(0x80FF00, 0x1771D3, 0x59C6FF, 0x2D84F4)),
	desert(fc(0x80FF00, 0xD9C26B, 0xFFE9AC, 0xD2A741)),
	nether(fc(0x80FF00, 0x972226, 0xFE7F00, 0xCF000D)),
	end(fc(0x80FF00, 0x363636, 0xD1D175, 0x1E0D2E)),

	// # 無機物

	cobblestone(fc(0x333333, 0x4A4A4A, 0xC8C8C8, 0x747474)),
	mossycobblestone(fc(0x333333, 0x4A4A4A, 0xC8C8C8, 0x278E27)),
	bedrock(fc(0x333333, 0x1E1E1E, 0xEEEEEE, 0x747474)),
	endstone(fc(0x333333, 0xC3BD89, 0xFAFAD0, 0xF5FF75)),
	dirt(fc(0xB87440, 0x593D29, 0xB9855C, 0x914A18)),
	grass(fc(0xB87440, 0x7FBF55, 0x97C667, 0x5BC117)),
	sand(fc(0xB87440, 0xC2BC84, 0xEEE4B6, 0xD8D09B)),
	ice(fc(0xFFFFFF, 0xB0CCFF, 0xB0CCFF, 0xB0CCFF)),
	snow(fc(0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF)),
	packedice(fc(0xFFFFFF, 0xC4D3EE, 0xC4D3EE, 0xC4D3EE)),

	// 鉱物
	netherquartz(fc(0xEDE9E4, 0x5D4A3F, 0xDFD8CF, 0xCCAB90)),
	emerald(fc(0x9FF9B5, 0x17DD62, 0x81F99E, 0x008A25)),
	gold(fc(0xA0A0A0, 0xDC7613, 0xFFFF0B, 0xDEDE00)),
	obsidian(fc(0x0F0F1A, 0x09090E, 0x463A60, 0x532B76)),
	lapislazuli(fc(0xA2B7E8, 0x224BD5, 0x4064EC, 0x0A33C2)),
	glowstone(fc(0xFFFF7D, 0xA28C22, 0xFFF448, 0xECB861)),
	coal(fc(0x363636, 0x030303, 0x101010, 0x000000)),

	// # 動物

	human(fc(0xFFBE80, 0x322976, 0x00AAAA, 0x281B0B)),
	zombie(fc(0x2B4219, 0x322976, 0x00AAAA, 0x2B4219)),
	skeleton(fc(0xCACACA, 0xCFCFCF, 0xCFCFCF, 0x494949)),
	creeper(fc(0x5BAA53, 0x5EE74C, 0xD6FFCF, 0x000000)),
	spider(fc(0x494422, 0x52483F, 0x61554A, 0xA80E0E)),
	blaze(fc(0xFFE753, 0xDC8801, 0xFFFE37, 0x882900)),
	witherskeleton(fc(0x505252, 0x1C1C1C, 0x1C1C1C, 0x060606)),
	ghast(fc(0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0x565656)),
	wither(fc(0x181818, 0x141414, 0x3C3C3C, 0x557272)),
	enderdragon(fc(0x000000, 0x181818, 0x181818, 0xA500E2)),
	cow(fc(0xDE9D9D, 0x3B2E22, 0x969696, 0x000000)),
	villager(fc(0xBD8B72, 0x3C2A23, 0x71544D, 0xBD8B72)),
	golem(fc(0xDBCDC1, 0x8B7260, 0xDDC9B9, 0x46750B)),
	mooshroom(fc(0x940E0F, 0x940E0F, 0x940E0F, 0xA1A1A1)),
	feather(fc(0xFFBE80, 0xAAAAAA, 0xFFFFFF, 0x585858)),
	leather(fc(0xFFBE80, 0xC65C35, 0xC65C35, 0x542716)),
	string(fc(0xFFBE80, 0x000000, 0x8C8C8C, 0xFFFFFF)),
	bonemeal(fc(0xFFBE80, 0xEAEAEA, 0xEAEAEA, 0xB9B9CB)),
	milk(fc(0xFFBE80, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF)),
	fish(fc(0xFFBE80, 0x6B9F93, 0x6B9F93, 0xADBEDB)),
	enderpearl(fc(0xFFBE80, 0x258474, 0x8CF4E2, 0x00725F)),
	netherstar(fc(0xFFBE80, 0xCBD6D6, 0x88A4A4, 0xD2D200)),

	// # 植物・菌類

	redmushroom(fc(0x7C4042, 0xFE2A2A, 0xFFFFFF, 0x9A171C)),
	tallgrass(fc(0x168700, 0x7FBF55, 0x97C667, 0x5BC117)),
	rose(fc(0x168700, 0x910205, 0xF7070F, 0x3E4400)),
	poppy(fc(0x168700, 0xF7070F, 0xF7070F, 0x3A0102)),
	cactus(fc(0x168700, 0x128C21, 0x148D24, 0x000000)),
	wheat(fc(0x168700, 0x716125, 0xD5DA45, 0x9E8714)),
	log(fc(0x168700, 0x59472C, 0x977748, 0xBB9761)),
	apple(fc(0x168700, 0xFF1C2B, 0xFF1C2B, 0xFFAAAF)),

	// # 加工品

	// ツールアイテム系
	pickaxe(fc(0x957546, 0x9A9A9A, 0x9A9A9A, 0x333333)),
	shovel(fc(0x957546, 0x9A9A9A, 0x9A9A9A, 0xB87440)),
	axe(fc(0x957546, 0x9A9A9A, 0x9A9A9A, 0x59472C)),
	hoe(fc(0x957546, 0x9A9A9A, 0x9A9A9A, 0x168700)),
	bucket(fc(0x957546, 0x9A9A9A, 0x9A9A9A, 0x5469F2)),
	flintandsteel(fc(0x957546, 0x9A9A9A, 0x9A9A9A, 0xFF4A00)),
	shears(fc(0x957546, 0xD5D5D5, 0xD5D5D5, 0x634235)),
	compass(fc(0x957546, 0x9A9A9A, 0x9A9A9A, 0xFF1414)),
	sword(fc(0x957546, 0x70FFD9, 0xD1FAF3, 0xE60000)),
	bow(fc(0x957546, 0x493615, 0x493615, 0xFFFFFF)),
	arrow(fc(0x957546, 0x493615, 0x493615, 0x969696)),
	chestplate(fc(0x957546, 0xC1C1C1, 0xC1C1C1, 0xFF6578)),

	// 作業台
	workbench(fc(0x957546, 0xB17449, 0xB17449, 0x2B2315)),
	enchantmenttable(fc(0x957546, 0x322042, 0x2BDED6, 0x8A1512)),

	// 建材
	glass(fc(0x957546, 0xE8FAFE, 0xFFFFFF, 0xC4F7FF)),
	stair(fc(0x957546, 0xBB9761, 0xBB9761, 0x005500)),

	// 家具・赤石
	chest(fc(0x957546, 0xA76E1F, 0xA76E1F, 0x413B2F)),
	bed(fc(0x957546, 0x9C2626, 0xEBEBEB, 0x413421)),
	spawner(fc(0x957546, 0x1B2A35, 0x1B2A35, 0xFF3E00)),
	beacon(fc(0x957546, 0xC0F5FE, 0x2BDED6, 0x322042)),
	netherportal(fc(0x957546, 0x670ECC, 0x670ECC, 0x322042)),
	endportal(fc(0x957546, 0x427367, 0xF9F9C5, 0x258474)),
	dispenser(fc(0x957546, 0x616161, 0xC5C5C5, 0x000000)),
	tnt(fc(0x957546, 0xDB441A, 0xCECECE, 0x000000)),

	// 素材
	book(fc(0x957546, 0x654B17, 0xFFFFFF, 0x000000)),
	sugar(fc(0xCC850C, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF)),

	// 食べ物
	bread(fc(0xCC850C, 0x654B17, 0x9E7325, 0x3F2E0E)),
	cake(fc(0xCC850C, 0xB85D27, 0xEAE9EB, 0xEA1D1D)),
	goldenapple(fc(0xCC850C, 0xDBA213, 0xEAEE57, 0x351705)),

	;

	static {
		air.setStatus(1, 0, 0, 10, 3, 2, 0, 0);
		water.setStatus(1, 10, 0, 0, 10, 3, 2, 0);
		stone.setStatus(1, 40, 0, 0, 0, 10, 8, 0);
		iron.setStatus(2, 70, 0, 0, 1, 4, 10, 1);
		diamond.setStatus(4, 90, 10, 8, 0, 4, 8, 4);
		redstone.setStatus(2, 60, 0, 8, 0, 0, 10, 16);
		enderman.setStatus(3, 85, 4, 14, 10, 0, 5, 11);
		lilac.setStatus(3, 50, 1, 0, 10, 1, 0, 0);
		torch.setStatus(2, 30, 0, 1, 0, 4, 10, 1);
		moon.setStatus(5, 95, 10, 18, 4, 0, 0, 25);
		noon.setStatus(1, 80, 10, 4, 7, 9, 4, 0);
		plains.setStatus(2, 20, 0, 0, 10, 14, 2, 0);
	}

	//

	public final MirageFairyColorSet colorSet;
	public final String registryName;
	public final String oreName;

	private EnumMirageFairy(MirageFairyColorSet colorset)
	{
		this.colorSet = colorset;
		this.registryName = name();
		this.oreName = name().substring(0, 1).toUpperCase() + name().substring(1);
	}

	private static MirageFairyColorSet fc(int skin, int darker, int brighter, int hair)
	{
		return new MirageFairyColorSet(skin, darker, brighter, hair);
	}

	//

	public class Potential
	{

		public final double sum;

		public final double light;
		public final double air;
		public final double water;
		public final double darkness;
		public final double earth;
		public final double fire;

		public Potential(double power, double ratioLight, double ratioWind, double ratioWater, double ratioDarkness, double ratioEarth, double ratioFire)
		{

			// ステータス合計
			sum = 10 * Math.pow(10, power / 100.0);

			// 比率合計
			double sumRatio = ratioLight + ratioWind + ratioWater + ratioDarkness + ratioEarth + ratioFire;

			// ステータス: 0 ~ power2
			light = sum * ratioLight / sumRatio;
			air = sum * ratioWind / sumRatio;
			water = sum * ratioWater / sumRatio;
			darkness = sum * ratioDarkness / sumRatio;
			earth = sum * ratioEarth / sumRatio;
			fire = sum * ratioFire / sumRatio;

		}

		public double get(EnumManaType manaType)
		{
			switch (manaType) {
				case light:
					return light;
				case air:
					return air;
				case water:
					return water;
				case darkness:
					return darkness;
				case earth:
					return earth;
				case fire:
					return fire;
				default:
					throw new RuntimeException("Illegal Mana Type: " + manaType);
			}
		}

	}

	private int rare; // 1 ~ 5
	private Potential potential;
	private double cost;

	public int getRare()
	{
		return rare;
	}

	public Potential getPotential()
	{
		return potential;
	}

	public double getCost()
	{
		return cost;
	}

	private void setStatus(int rare, double power, double ratioLight, double ratioAir, double ratioWater, double ratioDarkness, double ratioEarth, double ratioFire)
	{
		this.rare = rare;

		potential = new Potential(power, ratioLight, ratioAir, ratioWater, ratioDarkness, ratioEarth, ratioFire);

		// 集中度: 1/6 ~ 1
		double density = (potential.light * potential.light +
			potential.air * potential.air +
			potential.water * potential.water +
			potential.darkness * potential.darkness +
			potential.earth * potential.earth +
			potential.fire * potential.fire) / (potential.sum * potential.sum);

		// 効率: 1 + (1/12 ~ 0.5) + (0 ~ 0.2)
		double efficiency = 1 + density * 0.5 + (rare - 1) * 0.05;

		// コスト
		cost = potential.sum / efficiency;

	}

}
