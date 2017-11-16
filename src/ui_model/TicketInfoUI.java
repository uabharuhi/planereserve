package ui_model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Flight;
import model.Ticket;

public class TicketInfoUI {
	private Ticket ticket;

	
	private  StringProperty ticketId;
	private  StringProperty planeId;
	private  StringProperty passengerNum;
	private  StringProperty date;
	private  StringProperty time;
	private  StringProperty departure;
	private  StringProperty destination;


	
	public TicketInfoUI(Ticket ticket) {
	  this.ticket =  ticket;
	  ticketId = new SimpleStringProperty(String.format("%d",ticket.getId()));
	  planeId = new SimpleStringProperty(String.format("%d",ticket.getFlight().getPlane().getId()));
	  
	  int passenger_max_num = ticket.getFlight().getPlane().getMax_num_people();
	  int passenger_num = ticket.getFlight().getTickets().size();
	  passengerNum = new SimpleStringProperty(String.format("%d/%d",passenger_num,passenger_max_num));
	  
	  date  = new SimpleStringProperty(ticket.getFlight().getDate().toString());
	  time  = new SimpleStringProperty(ticket.getFlight().getTime().toString());
	  departure = new SimpleStringProperty(ticket.getFlight().getDeparture());
	  destination = new SimpleStringProperty(ticket.getFlight().getDeparture());
	  
	}


	public Ticket getTicket() {
		return ticket;
	}


	public StringProperty getTicketId() {
		return ticketId;
	}


	public StringProperty getPlaneId() {
		return planeId;
	}


	public StringProperty getPassengerNum() {
		return passengerNum;
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

}
