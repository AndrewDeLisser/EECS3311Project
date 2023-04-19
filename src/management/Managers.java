package management;

import customer.*;
import space_sensor.*;


public class Managers implements Observers{
	
	protected boolean supermanager;

	
	private String email;
	private String password;
	
	
	public Managers(String email,String pass) {
		
		if(email.equalsIgnoreCase("boss@yorku.ca")) {
			this.supermanager = true;
		}
		
		this.email = email;
		this.password = pass;
	}
	
	public boolean setSpace(ParkingSpace space,boolean bool) {
		space.setDisabled(bool);
		return space.getDisabled();
	}
	
	public boolean getSuper() {
		return supermanager;
	}
	public String getPassword(){
		return this.password;
	}
	public String getEmail(){
		return this.email;
	}
	
	@Override
	public void notifyObserver(Client client) {
		// TODO Auto-generated method stub
		if(client.getClass().toString().equalsIgnoreCase("faculty") 
				|| client.getClass().toString().equalsIgnoreCase("student")
				|| client.getClass().toString().equalsIgnoreCase("nonfaculty")) {
			client.IsValid();
		}
		
	}
	
	

}
