package string.test;

import java.util.HashMap;
import java.util.Map;

import org.googlecode.tmock.util.StringReflectionBuilder;



public class ToString {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		Pojo p1=new Pojo();
		p1.setBook("sd");
		p1.setMe(2);
		Pojo p2=new Pojo();
		p2.setBook("sd");
		p2.setMe(2);
		p1.getP3().setLi("haha");

		myPojo p4=new myPojo();
		p4.setLi("aal");
		Map<String,myPojo> list=new HashMap<String,myPojo>();
		Map<String,myPojo> list2=new HashMap<String,myPojo>();
		list.put("pp",p4);
		p1.setXl(list);
		System.out.println(StringReflectionBuilder.toString(p1));
		list2.put("pp",p4);
		p1.setXl(list2);
		System.out.println(StringReflectionBuilder.toString(p1));
		
	}

}
