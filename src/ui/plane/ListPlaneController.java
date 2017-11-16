package ui.plane;

import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.PlaneMeta;
import ui.Main;
import ui.Repository;
import ui.Util;
import ui_model.PlaneMetaUI;

public class ListPlaneController {
	private ObservableList<PlaneMetaUI> planeData = FXCollections.observableArrayList();
	@FXML
	private Button add_btn;
	@FXML
	private Button del_btn;	
	@FXML
	private Button refresh_btn;
	@FXML
	private TableView<PlaneMetaUI> table;	
	@FXML
	private TableColumn<PlaneMetaUI,String> id_col;
	@FXML
	private TableColumn<PlaneMetaUI,String> type_col;
	@FXML
	private TableColumn<PlaneMetaUI,String> maxnum_col;
	
	@FXML
    public void initialize() {
		System.out.println("init... load from plane");
		List<PlaneMeta> list = new Repository<PlaneMeta>(PlaneMeta.class).selectall();
		this.refresh_table(list);
		assert refresh_btn!=null;
    }
	
	@FXML
	protected void onAddBtnClick(ActionEvent event) {
		System.out.println("add btn click");
		Util.openNewWindow("plane/addplane.fxml","add a new plane");
	}
	
	@FXML
	protected void onDelBtnClick(ActionEvent event) {
		System.out.println("del btn click");
		Util.openNewWindow("plane/delplane.fxml","add a new plane");
	}
	
	@FXML
	protected void onRefreshBtnClick(ActionEvent event) {
		System.out.println("refresh btn click");
		refresh_table(new Repository<PlaneMeta>(PlaneMeta.class).selectall());
		
		
	}
	
	public void refresh_table(List<PlaneMeta> list) {
		
		planeData.clear();
		for(PlaneMeta plane:list) {
			System.out.println(plane.getId());
			planeData.add(new PlaneMetaUI(plane));
		}
		assert table!=null;
		table.setItems(planeData);
		table.setRowFactory(tv->{
			TableRow<PlaneMetaUI> row = new TableRow<>();
				row.setOnMouseClicked(event->{
					if (event.getClickCount() >0 && (! row.isEmpty()) ) {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("planeinfo.fxml"));
						PlaneInfoController controller = new PlaneInfoController();
						fxmlLoader.setController( controller);
						Parent root=null;
						try {
							root = (Parent)fxmlLoader.load();						
							PlaneMeta plane = row.getItem().getPlane();
							assert plane!=null;
							controller.setPlane(plane);
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
			);
			return row;
		});
		id_col.setCellValueFactory(d->d.getValue().idProperty());
		type_col.setCellValueFactory(d->d.getValue().typenameProperty());
		maxnum_col.setCellValueFactory(d->d.getValue().max_num_peopleProperty());
	}
	
	
	
}
