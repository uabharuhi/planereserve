package ui.flight;


import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import model.Flight;
import model.PlaneMeta;
import ui.Main;
import ui.Repository;
import ui.Util;
import ui_model.FlightUI;
import ui_model.PlaneMetaUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListFlightController {
	
	
	@FXML
	private DatePicker start_date;
	
	@FXML
	private DatePicker end_date;
	
	@FXML
	private Button query_btn;
	
	@FXML
	private Button add_btn;
	
	@FXML
	private Button cancel_btn;
	
	@FXML
	private Button refresh_btn;
	@FXML
	private Parent table; 
	@FXML
	private TableController tableController; //id = table table+Controller
	
	
	@FXML
    public void initialize() {
		assert table!=null;
		assert tableController!=null;
		System.out.println("init... load from flight");
		List<Flight> list = new Repository<Flight>(Flight.class).selectall();
		tableController.refresh_table(list);
	
    }
	@FXML
	public void addBtnClick() {
		System.out.println("addBtnClick");
		Util.openNewWindow("flight/addflight.fxml", "·s¼W¯Z¦¸");
	}
	
	@FXML
	public void cancelBtnClick() {

	}
	
	@FXML
	public void onQueryBtnClick() {
		System.out.println("query");
		String date1 = start_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String date2 = end_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Date> dates = Util.getDaysBetweenDates(Date.valueOf(date1),Date.valueOf(date2));
		
		CriteriaBuilder criteriaBuilder = Main.getSession().getCriteriaBuilder();
		CriteriaQuery<Flight> c = (CriteriaQuery) criteriaBuilder.createQuery(Flight.class);
		Root<Flight> root = c.from(Flight.class);
		c.select(root).where(root.get("date").in(dates));
		List<Flight> flights = Main.getSession().createQuery(c).getResultList();
		tableController.refresh_table(flights);
	}
	
	@FXML
	public void onRefreshBtnClick() {
		System.out.println("refresh");
		List<Flight> list = new Repository<Flight>(Flight.class).selectall();
	
		tableController.refresh_table(list);
	}
	

}
