package ui;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
    public static void main(String[] args) {
    	System.out.println("1234");
        launch(args);
    }
	
	
	@Override
    public void start(Stage primaryStage) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
	 
		//用讀進來FXML的作為Scene的root node
		Scene scene = new Scene(root, 1000,600);
		primaryStage.setScene(scene);
		primaryStage.show();

    }
}
