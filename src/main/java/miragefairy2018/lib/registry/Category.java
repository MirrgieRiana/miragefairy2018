package miragefairy2018.lib.registry;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

import mirrg.beryllium.struct.ImmutableArray;

public class Category<I extends CategoryItem>
{

	private TreeMap<Integer, I> map = new TreeMap<>();

	public <I2 extends I> I2 register(I2 item)
	{
		if (map.containsKey(item.id)) throw new RuntimeException("duplicate id: " + item.id);
		map.put(item.id, item);
		return item;
	}

	private ImmutableArray<Optional<I>> table;
	private boolean initialized = false;

	public ImmutableArray<Optional<I>> getTable()
	{
		if (!initialized) init();
		return table;
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
		return getTable().stream()
			.filter(Optional::isPresent)
			.map(Optional::get);
	}

	public void forEach(Consumer<I> consumer)
	{
		stream().forEach(consumer);
	}

	private void init()
	{
		ArrayList<Optional<I>> array = new ArrayList<>();

		for (int i = 0; i < map.lastKey() + 1; i++) {
			array.add(Optional.empty());
		}

		for (Entry<Integer, I> entry : map.entrySet()) {
			array.set(entry.getKey(), Optional.of(entry.getValue()));
		}

		table = new ImmutableArray<>(array);
		initialized = true;
	}

}
