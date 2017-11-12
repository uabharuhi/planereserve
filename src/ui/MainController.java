package ui;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
	
	   
	 
	    @FXML
	    protected void userBtnClick(ActionEvent event) {
	    	System.out.println("user click");
	    }
	    @FXML
	    protected void adminBtnClick(ActionEvent event) throws IOException {
	    	System.out.println("admin click");
	    	Util.openNewWindow("adminMain.fxml","admin");
	    }
	    @FXML
	    protected void planeBtnClick(ActionEvent event) {
	    	System.out.println("plane click");
	    }
}
