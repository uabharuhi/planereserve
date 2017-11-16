package ui.user;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PlaneMeta;
import ui.plane.PlaneInfoController;

public class InputUsernameController {
	@FXML
	private TextField username_tf;
	@FXML
	private Button confirm_btn;
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void onConfirmClick() {
		String username = username_tf.getText();
		System.out.println("useranme click");
		System.out.println(username);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("usermain.fxml"));
		UserMainController controller = new UserMainController();
		fxmlLoader.setController( controller);
		Parent root=null;
		try {
			root = (Parent)fxmlLoader.load();						
			controller.setUsername(username);
			//if method name is initialize,fxml will auto call that method...
			controller.init();
			Scene scene = new Scene(root);
			Stage stage  = new Stage();
			stage.setScene(scene);   
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}    
		
		
		
	}
}
