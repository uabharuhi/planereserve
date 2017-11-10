package ui;

import java.io.IOException;
import java.util.List;

import org.hibernate.query.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.PlaneMeta;

public class ListFlightController {
	private ObservableList<PlaneMeta> planeData = FXCollections.observableArrayList();
	@FXML
	private Button add_btn;
	@FXML
	private Button del_btn;	
	@FXML
	private TableView<PlaneMeta> table;	
	@FXML
	private TableColumn<PlaneMeta,String> id_col;
	@FXML
	private TableColumn<PlaneMeta,String> type_col;
	@FXML
	private TableColumn<PlaneMeta,String> maxnum_col;
	
	@FXML
    public void initialize() {
		System.out.println("init... load from plane");
		Query query = Main.getSession().createQuery("from planemeta");
		List<PlaneMeta> list = query.list();
		for(PlaneMeta plane:list) {
			planeData.add(plane);
		}
		id_col.setCellFactory(cellData -> cellData.getV);
    }
	
}
