package customer;

public class Visitors extends Client implements CostCalc {
	
		private int price  = 15;
		
	public Visitors(String email, String password) {
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

}
