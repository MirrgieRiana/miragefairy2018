package miragefairy2018.mod.city;

public class DoubleValue implements IDoubleValue
{

	public double value;

	public DoubleValue()
	{
		this(0);
	}

	public DoubleValue(double value)
	{
		this.value = value;
	}

	@Override
	public void set(double value)
	{
		this.value = value;
	}

	@Override
	public double get()
	{
		return value;
	}

}
