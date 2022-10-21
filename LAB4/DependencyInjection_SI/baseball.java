package DependencyInjection_SI;

public class baseball implements Icoach{

	IFortune ifortune;
	String name;
	String e_mail;
	String team_name;
	address add;
	
	public String toString() {
		return "Baseball Coach ["+name+" "+e_mail+" "+team_name+" ]"+add+" ]";
	}
	
	public address getAdd() {
		return add;
	}

	public void setAdd(address add) {
		this.add = add;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public IFortune getIfortune() {
		return ifortune;
	}

	public void setIfortune(IFortune ifortune) {
		System.out.println("Setting Fortune in Baseball class...");
		this.ifortune = ifortune;
	}

	@Override
	public String getDailyWorkout() {
		return "Hello,this is baseball coach";
	}
	
	public String getFortune() {
		return ifortune.getDailyFortune();
	}
}


