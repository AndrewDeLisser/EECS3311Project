package customer;

// Here we are implementing the Factory pattern

public class ClientMaker {
	
	public Client createClient(String type,String email,String password) {
		
		if(type.equalsIgnoreCase("faculty")) {
			return new Faculty(email,password);
		}
		else if(type.equalsIgnoreCase("nonfaculty")) {
			return new NonFaculty(email,password);
		}
		else if(type.equalsIgnoreCase("student")) {
			return new Student(email,password);
		}
		else {
			return new Visitors(email,password);
		}
	}

}
