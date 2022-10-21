package DependencyInjection_CI;

public class baseball implements Icoach{

	IFortune ifortune;
	String name;
	String e_mail;
	String team_name;
	
	
	public baseball(IFortune ifortune, String name,String e_mail,String team_name) {
		this.ifortune=ifortune;
		this.name=name;
		this.e_mail=e_mail;
		this.team_name=team_name;
		System.out.println("Inside baseball class const...");
	}
	
//	public baseball(IFortune ifortune) {
//		this.ifortune = ifortune;
//	}
	
	@Override
	public String getDailyWorkout() {
		return "Hello,this is baseball coach";
	}
	
	public String getFortune() {
		return ifortune.getFortune();
	}
	
	public String toString() {
		return "Baseball coach name is : "+name+", e-mail is : "+e_mail+" and team name is :"+team_name;
	}
}


