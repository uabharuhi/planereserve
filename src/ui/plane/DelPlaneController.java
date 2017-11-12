package ui.plane;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.PlaneMeta;
import ui.Main;
import ui.Repository;

public class DelPlaneController {
	@FXML
	private Button confirm_btn;
	@FXML
	private TextField planeid_tf;
	@FXML
	private Label state;

	
	@FXML
	protected  void confirm(ActionEvent event) {
		assert state!=null;
		state.setText("");
		String s = "刪除成功";
		try {
			boolean res = new Repository(PlaneMeta.class).deletetOne(Integer.valueOf(planeid_tf.getText()));
			if(!res) {
				s = "不存在id";
			}
		}catch(javax.persistence.PersistenceException e) {
			s = "刪除失敗,有至少一個班次使用本飛機";
		}finally {
			state.setText(s);
		}
	}
}
