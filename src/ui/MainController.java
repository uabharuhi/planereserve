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
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("adminMain.fxml"));
	        Parent page =  loader.load();

	        // Create the dialog Stage.
	        Stage stage = new Stage();
	        stage.setTitle("Admin");
	        stage.initModality(Modality.WINDOW_MODAL);
	        //dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        stage.setScene(scene);

	        // Set the person into the controller.
	        //PersonEditDialogController controller = loader.getController();
	        //controller.setDialogStage(dialogStage);
	        //controller.setPerson(person);

	        // Show the dialog and wait until the user closes it
	        stage.showAndWait();
	        System.out.println("1234");
	    }
	    @FXML
	    protected void planeBtnClick(ActionEvent event) {
	    	System.out.println("plane click");
	    }
}
