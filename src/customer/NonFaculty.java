package customer;

public class NonFaculty extends Client implements CostCalc,Validation{
	
	private int price = 10;

	private boolean Validated;
	
	public NonFaculty(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

	public int getPrice() {
		return this.price;
	}

	@Override
	public int totalCost(int duration) {
		// TODO Auto-generated method stub
		if(this.deposit) {
			return price*duration - price;
		}
		return price*duration;
	}

	
	public boolean IsValid() {
		// TODO Auto-generated method stub
		this.Validated = true;
		return this.Validated;
	}
}
