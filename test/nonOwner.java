package test;
import java.lang.reflect.*;

public class nonOwner implements InvocationHandler{
	PersonBean person;

	public nonOwner(PersonBean person)
	{
		this.person = person;
	}
 	public void dis()
	{
		System.out.println(person.getName());
		System.out.println(person.getInterests());
		System.out.println(person.getHotOrNotRating());
	}

	public Object invoke(Object proxy,Method method,Object[] args) throws IllegalAccessException
	{
		try{
			if(method.getName().startsWith("get")){
				return method.invoke(person,args);
			}else if(method.getName().equals("setHotOrNotRating")){
				return method.invoke(person,args);
			}else if(method.getName().startsWith("set")){
				throw new IllegalAccessException();
			}
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}
}