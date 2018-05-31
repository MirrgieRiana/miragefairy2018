package miragefairy2018.lib.registry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

import mirrg.beryllium.lang.LambdaUtil;
import mirrg.beryllium.struct.ImmutableArray;

public class Category<I extends CategoryItem> implements Iterable<I>
{

	private boolean initialized = false;

	//

	private TreeMap<Integer, I> mapId = new TreeMap<>();
	private TreeMap<String, I> mapName = new TreeMap<>();

	public <I2 extends I> I2 register(I2 item)
	{
		if (initialized) throw new IllegalStateException();
		if (mapId.containsKey(item.id)) throw new RuntimeException("duplicate id: " + item.id);
		if (mapName.containsKey(item.name)) throw new RuntimeException("duplicate name: " + item.name);
		mapId.put(item.id, item);
		mapName.put(item.name, item);
		return item;
	}

	//

	private ImmutableArray<Optional<I>> table;

	private void init()
	{
		if (initialized) throw new IllegalStateException();
		initialized = true;

		ArrayList<Optional<I>> array = new ArrayList<>();

		for (int i = 0; i < mapId.lastKey() + 1; i++) {
			array.add(Optional.empty());
		}

		for (Entry<Integer, I> entry : mapId.entrySet()) {
			array.set(entry.getKey(), Optional.of(entry.getValue()));
		}

		table = new ImmutableArray<>(array);
	}

	//

	public ImmutableArray<Optional<I>> getTable()
	{
		if (!initialized) init();
		return table;
	}

	public Optional<I> get(String name)
	{
		if (!initialized) init();
		return Optional.ofNullable(mapName.get(name));
	}

	public Optional<I> get(int id)
	{
		if (!initialized) init();
		if (id < 0) return Optional.empty();
		if (id >= table.length()) return Optional.empty();
		return table.get(id);
	}

	public int length()
	{
		return getTable().length();
	}

	public Stream<I> stream()
	{
		return LambdaUtil.filterPresent(getTable().stream());
	}

	@Override
	public void forEach(Consumer<? super I> consumer)
	{
		stream().forEach(consumer);
	}

	@Override
	public Iterator<I> iterator()
	{
		return stream().iterator();
	}

}
