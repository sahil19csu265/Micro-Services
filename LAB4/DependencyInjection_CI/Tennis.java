package DependencyInjection_CI;

public class Tennis implements Icoach {

	private IFortune fortune;
	
	public Tennis(IFortune fortune) {
		this.fortune = fortune;
	}
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "practice 15hrs daily";
	}

	@Override
	public String getFortune() {
		return fortune.getFortune();
	}
	
}
