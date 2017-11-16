package ui;

import java.awt.Button;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Flight;
import ui.flight.TableController;

public class PlaneSearchController {
	@FXML
	private Parent table;
	@FXML
	private TableController tableController;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField depature;
	@FXML
	private TextField destination;
	@FXML
	private Button button;
	
	@FXML
    public void initialize() {
		assert table!=null;
		assert tableController!=null;
		System.out.println("init... load from flight");
		List<Flight> list = new Repository<Flight>(Flight.class).selectall();
		tableController.refresh_table(list);
	
    }
	
	@FXML
	public void onQueryClick() {
		String dep = depature.getText();
		String des = destination.getText();
		Date date = null;
		if(datePicker.getValue()!=null) {
			date = Date.valueOf(datePicker.getValue());
		}
		System.out.println("Query click");
		System.out.println(dep);
		System.out.println(des);
		System.out.println(date);
		System.out.println("- - - - - - - - - - - -");
		
		CriteriaBuilder criteriaBuilder = Main.getSession().getCriteriaBuilder();
		CriteriaQuery<Flight> c = (CriteriaQuery) criteriaBuilder.createQuery(Flight.class);
		Root<Flight> root = c.from(Flight.class);
		c.select(root);
		Predicate  pred = criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		if(date!=null) {
			 pred = criteriaBuilder.and(criteriaBuilder.equal(root.get("date"),date),pred);
		}
		if(dep.length()>0) {
			pred = criteriaBuilder.and(criteriaBuilder.equal(root.get("departure"),dep),pred);
		}
		if(des.length()>0) {
			pred = criteriaBuilder.and(criteriaBuilder.equal(root.get("destination"),des),pred);
		}
		c.where(pred);
		List<Flight> flights = Main.getSession().createQuery(c).getResultList();
		tableController.refresh_table(flights);
	}

}
