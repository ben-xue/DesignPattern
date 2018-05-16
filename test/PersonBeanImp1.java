package test;

public class PersonBeanImp1 implements PersonBean{
	String name;
	String interests;
	int value;

	public String getName()
	{
		return name;
	}
	public String getInterests()
	{ 
		return interests;
	}	
	public int getHotOrNotRating()
	{
		return value;
	}

	public void setName(String tName)
	{
		name = tName;
	}
	public void setInterests(String tInterests)
	{
		interests = tInterests;
	}
	public void setHotOrNotRating(int value)
	{
		this.value = value;
	}
}