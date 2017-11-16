package ui_model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.PlaneMeta;

public class  PlaneMetaUI {
	private PlaneMeta plane;


	
	private  StringProperty id;
	private  StringProperty typename;
	private  StringProperty max_num_people;
	
   public PlaneMetaUI(PlaneMeta plane) {
	  this.plane = plane;
	  id = new SimpleStringProperty(String.format("%d",plane.getId()));
	  assert id!=null;
	  typename = new SimpleStringProperty(plane.getTypename());
	  max_num_people = new SimpleStringProperty(String.format("%d",plane.getMax_num_people()));
   }

   public PlaneMeta getPlane() {
	   return plane;
   }
   
	public StringProperty idProperty() {
		return id;
	}

	public StringProperty typenameProperty() {
		return typename;
	}
	
	public StringProperty max_num_peopleProperty() {
		return max_num_people;
	} 
   
  
  

}
