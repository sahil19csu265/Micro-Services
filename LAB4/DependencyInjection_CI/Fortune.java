package DependencyInjection_CI;

public class Fortune implements IFortune {

	public String getFortune() {
		return "you have a good fortune today";
	}
	
	public String healthService() {
		return "This is Health Service of the Fortune class";
	}
}
