import customer.*;
import management.*;
import space_sensor.*;

import java.util.ArrayList;
import java.util.Iterator;

import java.io.FileWriter;
import java.io.IOException;

//Here we are using the Observer pattern as well

public class YorkParkingSystem implements Observers, Iterable<ParkingSpace> {

	ArrayList<ParkingSpace> Lot1;
	String[] Lot1IDs;

	// List of managers in that will be present in the system
	// 1 super manager and 5 managers.
	ArrayList<Managers> managers;

	ArrayList<Client> customers;
	ArrayList<String> customerNames;
	protected boolean Available_Lot_1;

	private int idforspace;

	Managers SuperManager;

	public YorkParkingSystem() {
		Lot1 = new ArrayList<ParkingSpace>();
		managers = new ArrayList<Managers>();
		customers = new ArrayList<Client>();
		customerNames = new ArrayList<String>();
		ParkingSpace space;
		this.Available_Lot_1 = true;
		for (int i = 0; i < 100; i++) {
			space = new ParkingSpace(i);
			space.setLot(1);
			Lot1.add(space);
		}
		Lot1IDs = new String[Lot1.size()];
		// iterative design pattern implemented here
		for (int i = 0; i < Lot1.size(); i++) {
			Lot1IDs[i] = Lot1.get(i).getNumber().toString();
		}

		SuperManager = new Managers("boss@yorku.ca", "boss1");

		createManagers(SuperManager);
	}

	public boolean book(Client client, ParkingSpace space, double startTime, double endTime) {
		client.setTimes(startTime, endTime);
		client.setSpaceId(space.getNumber());
		space.setSensor(true);
		if (space.getSensor()) {
			return true;
		}
		return false;
	}

	public void addCustomer(String type, String email, String pass) {

		ClientMaker maker = new ClientMaker();

		Client newClient = maker.createClient(type, email, pass);

		customers.add(newClient);
		customerNames.add(newClient.getEmail());
		if (type.equalsIgnoreCase("faculty") || type.equalsIgnoreCase("nonfaculty")
				|| type.equalsIgnoreCase("student")) {
			notifyObserver(newClient);
			System.out.println("You will be notified after approval");
		}

	}

	private void createManagers(Managers man) {
		if (man.getSuper()) {
			Managers man1 = new Managers("man1@yorku.ca", "man1");
			Managers man2 = new Managers("man2@yorku.ca", "man2");
			Managers man3 = new Managers("man3@yorku.ca", "man3");
			Managers man4 = new Managers("man4@yorku.ca", "man4");
			Managers man5 = new Managers("man5@yorku.ca", "man5");
			managers.add(man1);
			managers.add(man2);
			managers.add(man3);
			managers.add(man4);
			managers.add(man5);
		}
	}

	public boolean manSetSpace(Managers man, int id, boolean bool) {
		ParkingSpace it;
		this.idforspace = id;
		int i = 0;
		while (i < Lot1.size()) {
			it = iterator().next();
			if (it != null) {
				man.setSpace(it, bool);
				return true;
			}
		}
		i++;
		return false;
	}

	public String ExtraInfo(ParkingSpace space) {
		for (Client cle : customers) {
			if (cle.getSpaceId() == space.getNumber()) {
				return space.getCarInfo(cle);
			}
		}
		return null;

	}

	@Override
	public void notifyObserver(Client client) {
		// TODO Auto-generated method stub

		for (Managers man : managers) {
			man.notifyObserver(client);
		}

	}

	public void exportDatabaseinCSV() {
		try {
			FileWriter csvWriter = new FileWriter("Database.csv");
			String[] ste = new String[] { "Email", "Password", "Type" };
			csvWriter.write(String.join(",", ste));
			for (Client cle : customers) {
				String s = cle.getEmail() + "," + cle.getPassword() + "," + cle.getClass().toString();
				csvWriter.write(s);
				csvWriter.write("\n");
			}
			csvWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// Here we are using the Iterator Pattern to iterate through the lot list.
	@Override
	public Iterator<ParkingSpace> iterator() {
		return new spaceIterator(idforspace);
	}

	private class spaceIterator implements Iterator<ParkingSpace> {

		private int localId;
		private int ind;

		public spaceIterator(int id) {

			this.localId = id;
			this.ind = -1;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			int next = ind + 1;

			while (next < Lot1.size()) {
				if (Lot1.get(next) != null) {
					return true;
				}
				next++;
			}
			return false;
		}

		@Override
		public ParkingSpace next() {
			// TODO Auto-generated method stub
			for (ind = ind + 1; ind < Lot1.size(); ind++) {
				if (Lot1.get(ind).getNumber() == localId) {
					return Lot1.get(ind);
				}
			}
			return null;
		}

	}

}
