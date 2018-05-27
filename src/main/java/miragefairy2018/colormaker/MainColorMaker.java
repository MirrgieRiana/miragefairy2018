package miragefairy2018.colormaker;

import java.io.IOException;

import miragefairy2018.colormaker.core.ColorIdentifier;
import miragefairy2018.colormaker.core.ImageLayer;
import miragefairy2018.colormaker.core.ImageLoader;
import miragefairy2018.mod.ModMirageFairy2018;
import mirrg.beryllium.struct.ImmutableArray;

public class MainColorMaker
{

	public static void main(String[] args) throws Exception
	{
		new WindowColorMaker().setVisible(true);
	}

	public static ImmutableArray<ImmutableArray<ImageLayer>> loadImages() throws IOException
	{
		ImageLoader imageLoader = new ImageLoader(ModMirageFairy2018.class.getClassLoader(), ModMirageFairy2018.MODID);
		return new ImmutableArray<>(

			new ImmutableArray<>(

				new ImageLayer(imageLoader.loadItemImage("mirage_fairy_layer0"), new ColorIdentifier("@skin")),
				new ImageLayer(imageLoader.loadItemImage("mirage_fairy_layer1"), new ColorIdentifier("#00BE00")),
				new ImageLayer(imageLoader.loadItemImage("mirage_fairy_layer2"), new ColorIdentifier("@darker")),
				new ImageLayer(imageLoader.loadItemImage("mirage_fairy_layer3"), new ColorIdentifier("@brighter")),
				new ImageLayer(imageLoader.loadItemImage("mirage_fairy_layer4"), new ColorIdentifier("@hair"))

			),
			new ImmutableArray<>(

				new ImageLayer(imageLoader.loadItemImage("mirage_wisp_layer0"), new ColorIdentifier("@darker")),
				new ImageLayer(imageLoader.loadItemImage("mirage_wisp_layer1"), new ColorIdentifier("@skin")),
				new ImageLayer(imageLoader.loadItemImage("mirage_wisp_layer2"), new ColorIdentifier("@brighter")),
				new ImageLayer(imageLoader.loadItemImage("mirage_wisp_layer3"), new ColorIdentifier("@hair"))

			),
			new ImmutableArray<>(

				new ImageLayer(imageLoader.loadItemImage("magic_sphere_layer0"), new ColorIdentifier("@darker")),
				new ImageLayer(imageLoader.loadItemImage("magic_sphere_layer1"), new ColorIdentifier("@hair")),
				new ImageLayer(imageLoader.loadItemImage("magic_sphere_layer2"), new ColorIdentifier("@skin")),
				new ImageLayer(imageLoader.loadItemImage("magic_sphere_layer3"), new ColorIdentifier("@brighter"))

			)

		);
	}

}
