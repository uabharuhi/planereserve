package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Util {
	public static void openNewWindow(String fxmlpath,String title) {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource(fxmlpath));
	        Parent page =  loader.load();
	
	        // Create the dialog Stage.
	        Stage stage = new Stage();
	        stage.setTitle(title);
	        stage.initModality(Modality.WINDOW_MODAL);
	        Scene scene = new Scene(page);
	        stage.setScene(scene);
	        stage.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
