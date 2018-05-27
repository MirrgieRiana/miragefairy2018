package miragefairy2018.mod;

import miragefairy2018.lib.util.Builder;
import mirrg.beryllium.event2.EventProviderConsumer;
import mirrg.beryllium.event2.EventProviderRunnable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class EventRegistryMod
{

	public final EventProviderConsumer<FMLPreInitializationEvent> preInit = new EventProviderConsumer<>();

	public final EventProviderConsumer<Builder> registerItem = new EventProviderConsumer<>();

	public final EventProviderConsumer<Builder> registerBlock = new EventProviderConsumer<>();

	public final EventProviderConsumer<Builder> createItemStack = new EventProviderConsumer<>();

	public final EventProviderRunnable decorate = new EventProviderRunnable();

	//

	public final EventProviderConsumer<FMLInitializationEvent> init = new EventProviderConsumer<>();

	public final EventProviderRunnable recipe = new EventProviderRunnable();

	public final EventProviderRunnable registerItemColorHandler = new EventProviderRunnable();

	//

	public final EventProviderConsumer<FMLPostInitializationEvent> postInit = new EventProviderConsumer<>();

}
