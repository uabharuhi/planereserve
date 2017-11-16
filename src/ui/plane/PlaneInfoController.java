package ui.plane;

import java.util.List;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Flight;
import model.PlaneMeta;
import ui.Repository;
import ui_model.FlightUI;
import ui_model.PlaneMetaUI;

public class PlaneInfoController {
	private ObservableList<FlightUI> flightData = FXCollections.observableArrayList();
	
	@FXML
	private TableView<FlightUI> table;
	
	@FXML
	private TableColumn<FlightUI,String> flight_id;
			
	@FXML 
	private TableColumn<FlightUI,String> date;
	
	@FXML 
	private TableColumn<FlightUI,String> time;
	
	@FXML 
	private TableColumn<FlightUI,String> departure;
	
	@FXML 
	private TableColumn<FlightUI,String> destination;
	
	@FXML 
	private Label planeid_lb;
	@FXML 
	private Label maxpeople_lb;
	@FXML 
	private Label type_lb;
	
	private PlaneMeta plane;
	
	public void setPlane(PlaneMeta plane) {
		System.out.println("plane");
		System.out.println(plane.getId());
		this.plane = plane;
	}
	

    public void init() {
		System.out.println("init... load from flight");
		assert plane!=null;
		planeid_lb.setText(String.format("%d",plane.getId()));
		maxpeople_lb.setText(String.format("%d",plane.getMax_num_people()));
		type_lb.setText(plane.getTypename());
		
		for(Flight f:plane.getFlights()) {
			flightData.add(new FlightUI(f));
		}
		table.setItems(flightData);
				
		flight_id.setCellValueFactory(d->d.getValue().getId());
		date.setCellValueFactory(c->c.getValue().getDate());
		time.setCellValueFactory(c->c.getValue().getTime());
		departure.setCellValueFactory(c->c.getValue().getDeparture());
		destination.setCellValueFactory(c->c.getValue().getDestination());		
	
    }
}
