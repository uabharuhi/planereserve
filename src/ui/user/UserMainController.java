package ui.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Flight;
import model.PlaneMeta;
import model.Ticket;
import ui.Main;
import ui.Repository;
import ui.plane.PlaneInfoController;
import ui_model.PlaneMetaUI;
import ui_model.TicketInfoUI;

public class UserMainController {
	private ObservableList<TicketInfoUI> ticketData = FXCollections.observableArrayList();
	
	private String username;
	@FXML
	private Label username_lb;
	@FXML
	private TextField planeid_tf;
	@FXML
	private Button bookTicketButton;
	@FXML
	private TableView<TicketInfoUI> table;
	@FXML
	private TableColumn<TicketInfoUI,String> ticketid;
	@FXML
	private TableColumn<TicketInfoUI,String> plane_id_col;
	@FXML
	private TableColumn<TicketInfoUI,String> passegernum;
	@FXML
	private TableColumn<TicketInfoUI,String> date;
	@FXML
	private TableColumn<TicketInfoUI,String> time;
	@FXML
	private TableColumn<TicketInfoUI,String> departure;
	@FXML
	private TableColumn<TicketInfoUI,String> destination;
	
	
	
	public List<Ticket> getTicketsOfUser(){
		CriteriaBuilder criteriaBuilder = Main.getSession().getCriteriaBuilder();
		CriteriaQuery<Ticket> c = (CriteriaQuery) criteriaBuilder.createQuery(Ticket.class);
		Root<Ticket> root = c.from(Ticket.class);
		c.select(root).where(criteriaBuilder.equal(root.get("username"),username));
		List<Ticket> list = Main.getSession().createQuery(c).getResultList();
		return list;
	}
	
	public void init() {
		username_lb.setText(username);
		
		refresh_table(getTicketsOfUser());
		
		table.setRowFactory(tv -> {
			TableRow<TicketInfoUI> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() > 1 && (!row.isEmpty())) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("取消機票");
					alert.setHeaderText(null);
					alert.setContentText("妳要取消本機票嗎?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
					   //cancel ticket
						Repository<Ticket> repo = new Repository<Ticket>(Ticket.class);
						repo.deletetOne(row.getItem().getTicket().getId());
						refresh_table(getTicketsOfUser());
					} else {
					    // ... user chose CANCEL or closed the dialog
					}

				}

			});
			return row;
		});
	}
	
	@FXML 
	public void onTicketButtonClick() {
		int pid = Integer.valueOf(plane_id_col.getText());
		Repository<Ticket> repo = new Repository<Ticket>(Ticket.class);
		try {
			
		}catch(Exception e) {
			Main.getSession().get
		}
		repo.insertOne(new Ticket(username,));
	}
	
	
	public void refresh_table(List<Ticket> list) {
		ticketData.clear();
		for(Ticket ticket:list) {
			ticketData.add(new TicketInfoUI(ticket));
		}
		table.setItems(ticketData);
				
		ticketid.setCellValueFactory(c->c.getValue().getTicketId());
		plane_id_col.setCellValueFactory(c->c.getValue().getPlaneId());
		passegernum.setCellValueFactory(c->c.getValue().getPassengerNum());
		date.setCellValueFactory(c->c.getValue().getDate());
		time.setCellValueFactory(c->c.getValue().getTime());
		departure.setCellValueFactory(c->c.getValue().getDeparture());
		destination.setCellValueFactory(c->c.getValue().getDestination());
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
