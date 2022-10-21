package valueInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {

	public static void main(String args[]) {
		
	ApplicationContext context = new ClassPathXmlApplicationContext("spring_valueInjection.xml");	
	
	customer c1 = (customer)context.getBean("custom");
	
	System.out.println(c1.toString());
	
	
	}
}
