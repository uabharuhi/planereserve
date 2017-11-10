package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AdminController {
	@FXML
	private AnchorPane rightPane;
	
	
	 @FXML
	 protected void onAddDeletePlaneBtnClick(ActionEvent event) {
		loadFxml("listplane.fxml");
	}
	 
	 public void loadFxml (String path) {
		    // load new pane
		    Pane newPane;
			try {
		    	FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(AdminController.class.getResource(path));
				newPane =  loader.load();
				System.out.println("aasf");
				rightPane.getChildren().add(newPane);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("qwrwqrwq");
				e.printStackTrace();
			}


		}
}
