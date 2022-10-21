package DependencyInjection_CI;

public class football implements Icoach{

	IFortune fortune;
	String name;
	String e_mail;
	String team_name;
	
	public football(IFortune ifortune,String name,String e_mail,String team_name) {
		this.fortune=ifortune;
		this.name=name;
		this.e_mail=e_mail;
		this.team_name=team_name;
		System.out.println("Inside football class const...");
	}
	public football(IFortune fortune) {
		this.fortune = fortune;
	}
	@Override
	public String getDailyWorkout() {
		return "Hello,this is football coach";
	}
	@Override
	public String getFortune() {
		return fortune.getFortune();
	}

	public String toString() {
		return "Football coach name is : "+name+", e-mail is : "+e_mail+" and team name is :"+team_name;
	}

	
	

}
