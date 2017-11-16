package ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
	
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result =  new Date(calendar.getTimeInMillis());
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate,int inc_date)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result =  new Date(calendar.getTimeInMillis());
	        dates.add(result);
	        calendar.add(Calendar.DATE, inc_date);
	    }
	    return dates;
	}
}
