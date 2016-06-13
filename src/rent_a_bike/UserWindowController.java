package rent_a_bike;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class UserWindowController {

    rentBIkeController ctrl;

    @FXML
    private Button exitReserve;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField enterSurname;

    @FXML
    private TextField enterName;

    @FXML
    private TextField enterBikemodel;

    @FXML
    private TextField enterDate;
    
    @FXML
    void abandon(ActionEvent event) {
        Stage stage = (Stage) enterName.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void reserveBike(ActionEvent event) {
        //  Parcurg lista de angajati si gasesc val. maxima a atributului id
        int idmax = 0;
        NodeList lista = ctrl.doc.getElementsByTagName("userBike");
        //  Parcurg lista de elemente de tip 'angajat'
        for (int contor = 0; contor < lista.getLength(); contor++) {
            Element newusr = (Element) lista.item(contor);
            int idCurent = Integer.parseInt(newusr.getAttribute("id"));
            if (idCurent > idmax) {
                idmax = idCurent;
            }
        }
        //  Identificatorul noului user va fi idmax + 1
        idmax++;

        //  Adaug user in fisier
        Element radacina = ctrl.doc.getDocumentElement();
        //  nodul radacina este <userBike>
        Text valoare;  //  Folosit la incarcarea valorilor
        Element newusr = ctrl.doc.createElement("userBike");
        newusr.setAttribute("id", String.valueOf(idmax));
        
        Element surn = ctrl.doc.createElement("enterSurname");
        newusr.appendChild(surn);
        valoare = ctrl.doc.createTextNode(enterSurname.getText());
        surn.appendChild(valoare);
        
        Element num = ctrl.doc.createElement("enterName");
        newusr.appendChild(num);
        valoare = ctrl.doc.createTextNode(enterName.getText());
        num.appendChild(valoare);
        
        Element bkm = ctrl.doc.createElement("enterBikemodel");
        newusr.appendChild(bkm);
        valoare = ctrl.doc.createTextNode(enterBikemodel.getText());
        bkm.appendChild(valoare);
        
        Element dta = ctrl.doc.createElement("enterDate");
        newusr.appendChild(dta);
        valoare = ctrl.doc.createTextNode(enterDate.getText());
        dta.appendChild(valoare);
        
        radacina.appendChild(newusr);
        //  Creez un user si il adaug in lista langajati
        UserBike ang = new UserBike(idmax, enterSurname.getText(),enterName.getText(), enterBikemodel.getText(),enterDate.getText());
        ctrl.userList.add(ang);
        ctrl.regUsers.getItems().add(ang);
        //  Golesc controalele
        enterSurname.setText(null);
        enterName.setText(null);
        enterBikemodel.setText(null);
        enterDate.setText(null);
        //  Repopulez tabelul
        ctrl.regUsers.getItems().clear();
        for (UserBike an : ctrl.userList) {
            ctrl.regUsers.getItems().add(an);
        }

        //  Salvez fisierul .xml
        ctrl.saveData(ctrl.doc);
        
        Stage stage = (Stage) exitReserve.getScene().getWindow();
        stage.hide(); 
        
}
}
