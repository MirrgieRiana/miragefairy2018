package miragefairy2018.mod.city.buildingentities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Optional;

import miragefairy2018.mod.city.Building;
import miragefairy2018.mod.city.SpecialManaType;
import miragefairy2018.mod.city.TileEntityBuilding;
import miragefairy2018.mod.city.buildingentity.BuildingEntity;
import miragefairy2018.mod.city.buildingentity.FairyFountain;
import miragefairy2018.mod.city.buildingentity.FairyWaterway;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class BuildingEntityFountain extends BuildingEntity
{

	public FairyWaterway waterway;
	public FairyFountain fountain;

	public BuildingEntityFountain(TileEntityBuilding tileEntity, Building building)
	{
		super(tileEntity, building);
		waterway = new FairyWaterway(EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST);
		fountain = new FairyFountain();

		// 噴水の更新イベントで水路に水を流す
		fountain.waterSupply.register(new Runnable() {

			private int token = 0;

			@Override
			public void run()
			{

				// 水路に水を流す
				waterway.startWaterSupply(tileEntity.getWorld(), tileEntity.getPos(), token, fountain);
				token++;

				// 全ての特化労働力を10%減衰させる
				{
					Hashtable<SpecialManaType, Double> specialManas = fountain.specialManas;

					ArrayList<SpecialManaType> removeList = new ArrayList<>();
					for (Entry<SpecialManaType, Double> entry : specialManas.entrySet()) {
						double value = entry.getValue();
						value *= 0.9;
						if (value < 1) {
							value = 0;
							removeList.add(entry.getKey());
						} else {
							entry.setValue(value);
						}

						// TODO
						System.out.println("" + entry.getKey() + ": " + String.format("%.1f", value));

					}
					for (SpecialManaType specialManaType : removeList) {
						specialManas.remove(specialManaType);
					}
				}

				// TODO
				System.out.println("JEWEL: " + String.format("%.1f", fountain.jewel.get()));

			}

		});

	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		waterway.readFromNBT(compound.getCompoundTag("waterway"));
		fountain.readFromNBT(compound.getCompoundTag("fountain"));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("waterway", waterway.createTagCompound());
		compound.setTag("fountain", fountain.createTagCompound());
	}

	@Override
	public void update()
	{
		waterway.update(tileEntity.getWorld(), tileEntity.getPos());
		fountain.update(tileEntity.getWorld(), tileEntity.getPos());
	}

	@Override
	public Optional<FairyWaterway> getFairyWaterway(EnumFacing facing)
	{
		return waterway.canConnect(facing) ? Optional.of(waterway) : Optional.empty();
	}

}
