package miragefairy2018.mod.city;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import miragefairy2018.lib.UnlistedSelector;
import miragefairy2018.lib.category.Category;
import miragefairy2018.mod.EventRegistryMod;
import miragefairy2018.mod.ModMirageFairy2018;
import miragefairy2018.mod.ModuleMain;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantList;
import net.minecraft.client.renderer.block.model.multipart.Multipart;
import net.minecraft.client.renderer.block.model.multipart.Selector;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleCity
{

	public static final Category<Building> buildings = new Category<>();
	public static final Building buildingNothing = buildings.register(new Building(0, "nothing", "nothing", BuildingEntityNothing::new));
	public static final Building buildingRoad = buildings.register(new Building(4, "road", "road", BuildingEntityRoad::new));

	public static BlockBuilding blockBuilding;
	public static ItemBuilding itemBuilding;

	public static ItemStack itemStackFountain;
	public static ItemStack itemStackHouse;
	public static ItemStack itemStackFarm;

	public static void init(EventRegistryMod erMod)
	{
		erMod.preInit.register(event -> {

			if (ModuleMain.side.isClient()) {

				// デフォルトのblockstateファイルはUnlistedプロパティに対応していないので強引に対応させる
				Gson gson = (new GsonBuilder())
					.registerTypeAdapter(ModelBlockDefinition.class, new ModelBlockDefinition.Deserializer())
					.registerTypeAdapter(Variant.class, new Variant.Deserializer())
					.registerTypeAdapter(VariantList.class, new VariantList.Deserializer())
					.registerTypeAdapter(Multipart.class, new Multipart.Deserializer())
					.registerTypeAdapter(Selector.class, new UnlistedSelector.Deserializer())
					.create();
				try {
					// 魔法でprivate static finalフィールドにgsonオブジェクトをねじ込む
					Field fieldGson = ModelBlockDefinition.class.getDeclaredField("GSON");
					Field fieldModifiers = Field.class.getDeclaredField("modifiers");
					fieldModifiers.setAccessible(true);
					fieldModifiers.setInt(fieldGson, fieldGson.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL | Modifier.PUBLIC);
					fieldGson.set(null, gson);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}

		});
		erMod.registerBlock.register(b -> {

			blockBuilding = new BlockBuilding(Material.WOOD);
			blockBuilding.setUnlocalizedName("building");
			blockBuilding.setRegistryName(ModMirageFairy2018.MODID, "building");
			blockBuilding.setCreativeTab(ModuleMain.creativeTab);
			ForgeRegistries.BLOCKS.register(blockBuilding);
			if (ModuleMain.side.isClient()) {
				ModelLoader.setCustomStateMapper(
					blockBuilding,
					new StateMapperBase() {
						@Override
						protected ModelResourceLocation getModelResourceLocation(IBlockState state)
						{
							return new ModelResourceLocation(ModMirageFairy2018.MODID + ":" + "building." + state.getValue(BlockBuilding.VARIANT).resourceName);
						}
					});
			}

		});
		erMod.registerItem.register(b -> {

			itemBuilding = b.new ItemBuilder<>(new ItemBuilding(blockBuilding), "building")
				.setRegistryName().register().process(builder -> {
					buildings.forEach(building -> {
						builder.setCustomModelResourceLocation(building.id, builder.resourceName + "." + building.resourceName);
						OreDictionary.registerOre("mirageBuilding" + building.oreNameSuffix, blockBuilding.createItemStack(building));
					});
				}).get();

		});
		erMod.createItemStack.register(b -> {

			itemStackFountain = new ItemStack(itemBuilding, 1, buildingFountain.id);
			itemStackHouse = new ItemStack(itemBuilding, 1, buildingHouse.id);
			itemStackFarm = new ItemStack(itemBuilding, 1, buildingFarm.id);

		});
		erMod.registerTileEntity.register(() -> {

			TileEntity.register("miragefairy2018:building", TileEntityBuilding.class);

		});
	}

}
