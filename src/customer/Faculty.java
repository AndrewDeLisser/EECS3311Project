package customer;

public class Faculty extends Client implements CostCalc,Validation{
	
	private int price = 8;
	
	private boolean Validated;

	public Faculty(String email, String password) {
		super(email,password);
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

	@Override
	public boolean IsValid() {
		// TODO Auto-generated method stub
		this.Validated = true;
		return this.Validated;
	}
}
