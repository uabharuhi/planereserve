package model;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "flight")
public class Flight {
	@Id 
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence",  
	pkColumnName="name", valueColumnName="seq",
	pkColumnValue="flight",initialValue=0, allocationSize=1)
	private int id;

	
	
	@ManyToOne(targetEntity = PlaneMeta.class,cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
	private PlaneMeta plane;
	
	@OneToMany(targetEntity = Ticket.class,fetch = FetchType.EAGER,mappedBy="flight",cascade= {CascadeType.PERSIST})
	private List<Ticket> tickets = new ArrayList<>();
	
	//@Column(name = "plane_id",nullable=false)
	//private int plane_id; 
	
	@Column(name = "price",nullable=false)
	private int price; 
	
	@Column(name = "departure",nullable=false)
	private String departure;
	
	@Column(name = "destination",nullable=false)
	private String destination; 
	
	@Column(name = "date",nullable=false)
	private Date date;
	
	@Column(name = "time",nullable=false)
	private Time time;

	public Flight() {
	
	}
	
	public List<Ticket> getTickets(){
		return tickets;
	}
	
	
	public Flight( int price, String departure, String destination,String str_date, Time time) {
		
		this.price = price;
		this.departure = departure;
		this.destination = destination;
		this.date = Date.valueOf(str_date);
		this.time = time;
	}
	
	public Flight(PlaneMeta plane, int price, String departure, String destination, String str_date, Time time) {
		this(price,departure,destination,str_date,time);
		this.plane = plane;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public PlaneMeta getPlane() {
		return plane;
	}

	
	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
}
/*
stat.executeUpdate("create table flight(id integer primary key AUTOINCREMENT,"
		+ "plane_id integer not null,"
		+ "price int not null,"
		+ "departure text not null,"
		+ "destination text not null,"
		+ "date date not null,"
		+ "time not null,"
		+ "FOREIGN KEY(plane_id) REFERENCES planemeta(id));");*/