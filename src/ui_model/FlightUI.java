package ui_model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Flight;
import model.PlaneMeta;

public class FlightUI {
	
	private Flight flight;


	
	private  StringProperty id;
	private  StringProperty plane_id;
	private  StringProperty price;
	private  StringProperty date;
	private  StringProperty time;
	private  StringProperty departure;
	private  StringProperty destination;
	private  StringProperty peopleNumState;

	
	public FlightUI(Flight flight) {
	  this.flight =  flight;
	  id = new SimpleStringProperty(String.format("%d",flight.getId()));
	  plane_id = new SimpleStringProperty(String.format("%d",flight.getPlane().getId()));
	  price = new SimpleStringProperty(String.format("%d",flight.getPrice()));
	  date = new SimpleStringProperty(flight.getDate().toString());
	  time = new SimpleStringProperty(flight.getTime().toString());
	  departure = new SimpleStringProperty(flight.getDeparture().toString());
	  destination = new SimpleStringProperty(flight.getDestination().toString());
	  peopleNumState = new SimpleStringProperty(String.format("%d/%d",
			  			flight.getTickets().size(),
			  			//flight.getPlane().getFlights().size(),
			  			flight.getPlane().getMax_num_people()));
	  
	}


	public Flight getFlight() {
		return flight;
	}


	public StringProperty getId() {
		return id;
	}


	public StringProperty getPlane_id() {
		return plane_id;
	}


	public StringProperty getPrice() {
		return price;
	}


	public StringProperty getDate() {
		return date;
	}


	public StringProperty getTime() {
		return time;
	}


	public StringProperty getDeparture() {
		return departure;
	}


	public StringProperty getDestination() {
		return destination;
	}


	public StringProperty getPeopleNumState() {
		return peopleNumState;
	}

	
}
