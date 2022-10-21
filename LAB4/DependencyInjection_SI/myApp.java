package DependencyInjection_SI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myApp {
	public static void main(String args[]) {
		
		//load the spring container
		ApplicationContext context = new ClassPathXmlApplicationContext("spring_SI.xml");
		
		// retrieve bean/object from spring container
		Icoach c1 = (Icoach) context.getBean("baseballCoach");
		Icoach c2 = (Icoach) context.getBean("footballCoach");
		
		// call the methods
		System.out.println();
		System.out.println(c1.getDailyWorkout());
		System.out.println(c1.getFortune());
		System.out.println(c2.getDailyWorkout());
		System.out.println(c2.getFortune());
		System.out.println();
		System.out.println(c1);
		System.out.println(c2);
		
		// closing the resources
		((ClassPathXmlApplicationContext) context).close();
				
	}
}
