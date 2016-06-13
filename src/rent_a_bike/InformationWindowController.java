package rent_a_bike;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class InformationWindowController {
    
    ArrayList <priceBike> tableLines;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button eWindow;

    @FXML
    private TableView<priceBike> bTable;

    @FXML
    private TableColumn<priceBike, String> bEquip;

    @FXML
    private TableColumn<priceBike, Integer> bPrice;

    @FXML
    void exitWindow(ActionEvent event) {
        Stage stage = (Stage) eWindow.getScene().getWindow();
        stage.hide(); 

    }

    @FXML
    void initialize() {
        
    bEquip.setCellValueFactory(cellData -> cellData.getValue().bEquip);
    bPrice.setCellValueFactory(cellData -> cellData.getValue().bPrice.asObject()); 
        tableLines = new ArrayList();
        tableLines.add(new priceBike("Bicycle", 10));
        tableLines.add(new priceBike("Bicycle & equipment", 18));
        for (priceBike p : tableLines) {
        bTable.getItems().add(p);
} 

    }
}