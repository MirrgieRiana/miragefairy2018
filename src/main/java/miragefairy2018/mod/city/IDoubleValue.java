package miragefairy2018.mod.city;

public interface IDoubleValue
{

	public void set(double value);

	public double get();

	public default void push(double value)
	{
		set(get() + value);
	}

	public default double pop(double min, double max)
	{
		double now = get();
		if (now < min) {
			return 0;
		} else if (now <= max) {
			set(0);
			return now;
		} else {
			set(now - max);
			return max;
		}
	}

}
