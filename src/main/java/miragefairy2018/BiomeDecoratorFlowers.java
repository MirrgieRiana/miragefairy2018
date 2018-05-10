package miragefairy2018;

import java.util.function.Consumer;

import miragefairy2018.util.Util;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class BiomeDecoratorFlowers implements Consumer<DecorateBiomeEvent.Post>
{

	private WorldGenerator worldGenerator;
	private double trialsPerChunk;

	public BiomeDecoratorFlowers(WorldGenerator worldGenerator, double trialsPerChunk)
	{
		this.worldGenerator = worldGenerator;
		this.trialsPerChunk = trialsPerChunk;
	}

	@Override
	public void accept(DecorateBiomeEvent.Post event)
	{
		int trialCount = Util.randomInt(trialsPerChunk, event.getRand());

		for (int i = 0; i < trialCount; ++i) {
			int offsetX = event.getRand().nextInt(16) + 8;
			int offsetZ = event.getRand().nextInt(16) + 8;
			int yMax = event.getWorld().getHeight(event.getPos().add(offsetX, 0, offsetZ)).getY() + 32;
			if (yMax > 0) {

				if (canGenerate(event.getWorld().getBiome(event.getPos()))) {
					worldGenerator.generate(
						event.getWorld(),
						event.getRand(),
						event.getPos().add(offsetX, event.getRand().nextInt(yMax), offsetZ));
				}

			}
		}
	}

	protected boolean canGenerate(Biome biome)
	{
		return true;
	}

}
