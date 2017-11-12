package ui.plane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Flight;
import model.PlaneMeta;
import model.Ticket;
import ui.Main;
import ui_model.PlaneMetaUI;

public class AddPlaneController {
	@FXML
	private Button confirm_btn;
	@FXML
	private TextField planetype_tf;
	@FXML
	private TextField max_num_people_tf;
	
	@FXML
	protected  void confirm(ActionEvent event) {
		Session session = Main.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(new PlaneMeta(planetype_tf.getText(),Integer.valueOf(max_num_people_tf.getText())));
		transaction.commit();
		System.out.println("confirm");
	}
	
}
