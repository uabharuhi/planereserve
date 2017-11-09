package model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id 
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence",  
	pkColumnName="name", valueColumnName="seq",
	pkColumnValue="ticket",initialValue=0, allocationSize=1)
	private int id;

	
	//@ManyToOne(targetEntity = PlaneMeta.class,cascade = CascadeType.ALL)
	@ManyToOne(targetEntity = Flight.class)
	private Flight flight;
	

	@Column(name = "username",nullable=false)
	private String username;

	public Ticket(Flight flight, String username) {
		this.flight = flight;
		this.username = username;
	}
	
	
	
	public Ticket() {
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Flight getFlight() {
		return flight;
	}



	public void setFlight(Flight flight) {
		this.flight = flight;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}
	
}