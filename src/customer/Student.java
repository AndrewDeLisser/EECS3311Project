package customer;

public class Student extends Client implements CostCalc,Validation{
	
	private int price = 5;
	
	private boolean Validated;
	
	public Student(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

	public int getPrice() {
		return this.price;
	}
	
	public boolean getValidation() {
		return this.Validated;
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
