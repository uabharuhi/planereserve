package ui.flight;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.id.IntegralDataTypeHolder;
import org.hibernate.procedure.internal.StandardCallableStatementSupport;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Flight;
import model.PlaneMeta;
import ui.Main;
import ui.Repository;
import ui.Util;
import ui_model.FlightUI;

public class AddFlightController {
	//private ObservableList<FlightUI> flightData = FXCollections.observableArrayList();
	
	@FXML
	private TextField planeid_tf;
	
	@FXML 
	private TextField time_tf;
	
	
	@FXML
	private DatePicker start_date;
	
	@FXML
	private DatePicker end_date;
	
	@FXML
	ChoiceBox<String> freq_choice;
	
	@FXML
	private Button confirm_btn;
	
	@FXML
	private Label res_lb;
	@FXML
	private TextField dep_tf;
	@FXML
	private TextField des_tf;
	@FXML
	private TextField price_tf;
	
	@FXML
    public void initialize() {
		System.out.println("init... load from flight");
		
		freq_choice.getItems().addAll("每天","每兩天","每個禮拜");
		
	
    }
	
	public String checkfield() {
		return "";
	}
	
	
	
	public void addFlights() {
		String res="新增成功";
		Session session = Main.getSession();
		try {
			//check Plane
			PlaneMeta plane = session.get(PlaneMeta.class,
					Integer.valueOf(planeid_tf.getText()));
			if(plane==null) {
				res = "不存在該id的飛機";
				return;
			}
			
			String  freq_str = freq_choice.getValue();;
			System.out.println(freq_str);
			if(freq_str==null) {
				res = "請選擇班機頻率";
				return;
			}
			
			int intev = 1; 
			
			if(freq_str=="每天") {
				intev = 1;
			}else if(freq_str=="每兩天") {
				intev = 2;
			}else  if(freq_str=="每個禮拜"){
				intev = 7;
			}else {
				assert false;
			}
			
			String date1 = start_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String date2 = end_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			List<Date> dates = Util.getDaysBetweenDates(Date.valueOf(date1),Date.valueOf(date2),intev);
			for(Date d : dates) {
				System.out.println(d.toString());
			}
			session.refresh(plane);
			for(Flight f:plane.getFlights()) {
				if(dates.contains(f.getDate())) {
					res = String.format("該飛機 在 %s 已經有航班了 新增失敗",f.getDate());
					return ;
				}
			}
			Time time = new Time(Integer.valueOf(time_tf.getText()),0,0);
			Transaction transaction = session.beginTransaction();
			for(Date d : dates) {
				int price = Integer.valueOf(price_tf.getText());
				session.save(new Flight(plane,price, dep_tf.getText(), des_tf.getText(), 
						d.toString(), time));
			}
			transaction.commit();
			session.refresh(plane);
			/*
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Flight> c = (CriteriaQuery) criteriaBuilder.createQuery(PlaneMeta.class);
			Root<Flight> root = c.from(Flight.class);
			c.select(root).where(root.get("date").in(dates));
			
			//check the plane has flights
			List<Flight> flights = session.createQuery(c).getResultList();
			for(Flight flight:flights) {
				System.out.println("1234");
				System.out.println(flight.getId());
				System.out.println(flight.getDate());
				System.out.println(flight.getDeparture());
				System.out.println("3234");
			}
			*/
			//Transaction transaction = session.beginTransaction();
			//session.save(obj);
			//transaction.commit();
			
			//return session.createQuery(c).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			res = "新增失敗";
		}finally {
			res_lb.setText(res);
		}
	}
	
	public void onConfirmClick() {
		addFlights();
		System.out.println("confirm click");
	}
	
	
}
