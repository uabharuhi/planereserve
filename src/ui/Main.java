package ui;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private  Configuration config;
	private SessionFactory sessionFactory;
	private static  Session session;
	
	
	
	
	

	
	
	
	
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public void init_ORM() {
		this.config = new Configuration();
		this.config.addAnnotatedClass(model.PlaneMeta.class);
		this.config.addAnnotatedClass(model.Flight.class);
		this.config.addAnnotatedClass(model.Ticket.class);
		this.config = config.configure();
		this.sessionFactory = config.buildSessionFactory();
		Main.session =  sessionFactory.openSession();    	
    }
    
    
    public static Session getSession() {
		return Main.session;
    	
    }
	
	
	@Override
    public void start(Stage primaryStage) throws IOException{
		init_ORM();
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
	 
		//用讀進來FXML的作為Scene的root node
		Scene scene = new Scene(root, 1000,600);
		primaryStage.setScene(scene);
		primaryStage.show();
		

    }
}
