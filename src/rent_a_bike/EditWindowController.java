package rent_a_bike;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EditWindowController {

    rentBIkeController ctrl;

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
    private Button exitEdit;

    @FXML
    private TextField enterDate;

    @FXML
    void abandon(ActionEvent event) {
        Stage stage = (Stage) enterName.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void editBike(ActionEvent event) {
        //  Caut in doc angajatul idAng:
        NodeList lista = ctrl.doc.getElementsByTagName("userBike");
        //  Parcurg lista de elemente de tip 'angajat'
        for (int contor = 0; contor < lista.getLength(); contor++) {
            Element newusr = (Element) lista.item(contor);
            int idCurent = Integer.parseInt(newusr.getAttribute("id"));

            if (idCurent == ctrl.idReg) {
                //  L-am gasit.  Pun in fisier valorile din controale
                newusr.getElementsByTagName("enterSurname").item(0).setTextContent(enterSurname.getText());
                newusr.getElementsByTagName("enterName").item(0).setTextContent(enterName.getText());
                newusr.getElementsByTagName("enterBikemodel").item(0).setTextContent(enterBikemodel.getText());
                newusr.getElementsByTagName("enterDate").item(0).setTextContent(enterDate.getText());
                //  Creez un Angajat
                UserBike ang = new UserBike(ctrl.idReg, enterSurname.getText(), enterName.getText(), enterBikemodel.getText(),enterDate.getText());
                //  Il adaug in lista langajati langajati
                ctrl.userList.remove(ctrl.indexSelect);  //  Sterg obiectul vechi
                // Adaug ob. nou in aceeasi pozitie
                ctrl.userList.add(ctrl.indexSelect, ang);
                //  Repopulez tabelul
                ctrl.regUsers.getItems().clear();

                //  Golesc controalele
                enterSurname.setText(null);
                enterName.setText(null);
                enterBikemodel.setText(null);
                enterDate.setText(null);

                for (UserBike an : ctrl.userList) {
                    ctrl.regUsers.getItems().add(an);
                }
                //  Salvez si pe disc
                ctrl.saveData(ctrl.doc);
                break;  //  Intrerup ciclul de cautare

            }
        }
        Stage stage = (Stage) exitEdit.getScene().getWindow();
        stage.hide();
    }
}
