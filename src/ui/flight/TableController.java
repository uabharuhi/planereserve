package ui.flight;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Flight;
import ui_model.FlightUI;

public class TableController {
	private ObservableList<FlightUI> flightData = FXCollections.observableArrayList();
	@FXML
	private TableView<FlightUI> table;
	
	@FXML
	private TableColumn<FlightUI,String> flight_id;
	
	@FXML
	private TableColumn<FlightUI,String> plane_id;
	
	@FXML 
	private TableColumn<FlightUI,String> price;
	
	@FXML 
	private TableColumn<FlightUI,String> date;
	
	@FXML 
	private TableColumn<FlightUI,String> time;
	
	@FXML 
	private TableColumn<FlightUI,String> departure;
	
	@FXML 
	private TableColumn<FlightUI,String> destination;
	
	@FXML 
	private TableColumn<FlightUI,String> num_state;
	
	public void refresh_table(List<Flight> list) {
		Collections.sort(list, new  Comparator<Flight>() {
			@Override
			public int compare(Flight o1, Flight o2) {
				// TODO Auto-generated method stub
				return o1.getDate().compareTo(o2.getDate());
			}
			
		});
		System.out.println("table controller refresh table");
		flightData.clear();
		for(Flight flight:list) {
			System.out.println(flight.getId());
			flightData.add(new FlightUI(flight));
		}
		assert table!=null;
		table.setItems(flightData);
		
		flight_id.setCellValueFactory(d->d.getValue().getId());
		plane_id.setCellValueFactory(d->d.getValue().getPlane_id());
		price.setCellValueFactory(c->c.getValue().getPrice());
		date.setCellValueFactory(c->c.getValue().getDate());
		time.setCellValueFactory(c->c.getValue().getTime());
		departure.setCellValueFactory(c->c.getValue().getDeparture());
		destination.setCellValueFactory(c->c.getValue().getDestination());	
		num_state.setCellValueFactory(c->c.getValue().getPeopleNumState());
	}
}
