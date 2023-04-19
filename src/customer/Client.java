package customer;

public abstract class Client {
	
	private String UniqueEmail;
	private String StrongPw;
	private String Car;
	
	private String Plate;
	
	private int spaceId;
	
	protected boolean deposit = false;
	double StartTime;
	double EndTime;
	
	public Client(String email,String password) {
		this.UniqueEmail = email;
		this.StrongPw = password;
	}
	
	public void setTimes(double start, double end) {
		this.StartTime = start;
		this.EndTime = end;
	}
	
	public String getPlate() {
		return this.Plate;
	}
	
	public String getCar() {
		return this.Car;
	}
	public void setCar(String car) {
		this.Car = car;
	}
	
	public void setPlate(String plate) {
		this.Plate = plate;
	}
	
	public String getEmail() {
		return this.UniqueEmail;
	}
	
	public String getPassword() {
		return this.StrongPw;
	}
	public int getPrice(){
		return 0;
	}
	
	public void setPassword(String password) {
		this.StrongPw = password ;
	}
	
	public boolean setDeposit() {
		this.deposit = true;
		return deposit;
	}
	
	public boolean Cancel(double current) {
		if(current < this.StartTime) {
			this.StartTime= 0;
			this.EndTime = 0;
			return true;
		}
		return false;
	}
	
	public double Edit(double current,double start,double end) {
		if(current < this.StartTime ) {
			this.StartTime = start;
			this.EndTime = end;
			return end;
		}
		return this.EndTime;
		
	}
	
	public double Extend(double current, double end) {
		if(current< this.EndTime) {
			this.EndTime = end;
			return this.EndTime;
		}
		return this.EndTime;
	}
	
	public int totalCost(int duration) {return 0; }
	
	public boolean IsValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getSpaceId() {
		return spaceId;
	}
	
	//TO use in Book method
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

}
