
import test.*;
import java.lang.reflect.*;
import java.util.*;

public class MachineMakingTestDrive{

	HashMap<String,PersonBean> map;

	public void initializeDateBase()
	{
		PersonBean p1 = new PersonBeanImp1();
		p1.setName("joe");
		p1.setInterests("Go,Swimming");
		p1.setHotOrNotRating(10);
		map.put(p1.getName(),p1);

		PersonBean p2 = new PersonBeanImp1();
		p2.setName("chandler");
		p2.setInterests("Sexy,Talk");
		p2.setHotOrNotRating(50);
		map.put(p2.getName(),p2);
	}

	public PersonBean getPersonFromDatabase(String name)
	{
		PersonBean ret = map.get(name);
		if(ret == null){
			System.out.println("error");
		}
		return ret;
	}

	public MachineMakingTestDrive()
	{
		map = new HashMap<String,PersonBean>();
		initializeDateBase();
	}

	public PersonBean getOwnerProxy(PersonBean person)
	{
		return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),person.getClass().getInterfaces(),new Owner(person));
	}

	public PersonBean getNonOwnerProxy(PersonBean person)
	{
		//return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),person.getClass().getInterfaces(),new Owner(person));
		return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),person.getClass().getInterfaces(),new nonOwner(person));
	}

	// public void dis()
	// {
	// 	System.out.println(ownerProxy.getName());
	// }

	public void drive()
	{
		PersonBean joe = getPersonFromDatabase("joe");
		PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Nmae is " + ownerProxy.getName());
		ownerProxy.setInterests("sing,dance");
		try{
			ownerProxy.setHotOrNotRating(99);
		}catch(Exception e){
			System.out.println("error: permission denied");
		}
		ownerProxy.dis();

		System.out.println("-------------------------");
		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		try{
			nonOwnerProxy.setInterests("walk");
		}catch(Exception e){
			System.out.println("error: permission denied");
		}
		nonOwnerProxy.setHotOrNotRating(36);
		System.out.println("22222222");
		nonOwnerProxy.dis();
		System.out.println("33333333");
	}

	public static void main(String[] args){
		MachineMakingTestDrive test = new MachineMakingTestDrive();
		test.drive();
	}
}