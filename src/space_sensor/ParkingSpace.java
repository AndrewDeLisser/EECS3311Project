package space_sensor;
import customer.*;

public class ParkingSpace {
	
	private Integer number;
	private boolean sensor = false;
	private boolean disabled = false;
	
	private Integer lot;
	
	public ParkingSpace(int id) {
		this.number = id;
	}
	
	public boolean getSensor() {
		return sensor;
	}
	
	public boolean getDisabled() {
		return disabled;
	}
	public void setNumber(int num) {
		this.number = num;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setDisabled(boolean value) {
		this.disabled = value;
	}
	
	public void setSensor(boolean value) {
		this.sensor = value;
	}
	
	public int getLot() {
		return lot;
	}
	
	public void setLot(int LOT) {
		this.lot = LOT;
	}
	
	public String getCarInfo(Client client) {
		if(sensor) {
		return client.getCar();}
		return null;
	}

}
