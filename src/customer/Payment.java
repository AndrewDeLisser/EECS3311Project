package customer;

public class Payment {
	
	public void DoPayment(String Ptype, Client client) {
		
		double duration = client.EndTime - client.StartTime;
		
		int cost = client.totalCost((int)duration);
		
		if(Ptype.equalsIgnoreCase("debit")) {
			System.out.println("Thankyou for paying with Debit and cost is" + cost);
		}
		else if(Ptype.equalsIgnoreCase("credit")) {
			System.out.println("Thankyou for paying with Crebit and cost is" + cost);
		}
		else if(Ptype.equalsIgnoreCase("mobile")) {
			System.out.println("Thankyou for paying with Mobile Payment and cost is" + cost);

		}
		else {
			System.out.println("Wrong payment type.");
		}
	}

}
