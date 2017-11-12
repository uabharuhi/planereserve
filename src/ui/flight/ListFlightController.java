package ui.flight;

import java.util.List;
import model.Flight;
import ui.Repository;
import ui_model.PlaneMetaUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ListFlightController {
	//private ObservableList<FlightUI> planeData = FXCollections.observableArrayList();
	
	
	@FXML
    public void initialize() {
		System.out.println("init... load from flight");
		List<Flight> list = new Repository<Flight>(Flight.class).selectall();
		this.refresh_table(list);
	
    }

	private void refresh_table(List<Flight> list) {
		// TODO Auto-generated method stub
		
	}
}
