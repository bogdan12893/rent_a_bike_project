package rent_a_bike;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class rentBIkeController {

    Stage infoStage = new Stage();
    InformationWindowController ctrlInfo;

    Stage rentStage = new Stage();
    UserWindowController ctrlUserWindow;
    
    Stage editStage = new Stage();
    EditWindowController ctrlEditWindow;
    
    private ArrayList<RoadBike> bList;

    File fXml;
    ArrayList<UserBike> userList;
    String cale;
    Document doc;
    int indexSelect;
    int idReg;

    @FXML
    private ResourceBundle resources;

    @FXML
    TableView<UserBike> regUsers;
    
    @FXML
    private TableColumn<UserBike, Integer> regId;

    @FXML
    private TableColumn<UserBike, String> regSurname;

    @FXML
    private TableColumn<UserBike, String> regName;

    @FXML
    private TableColumn<UserBike, String> regBikemodel;

    @FXML
    private TableColumn<UserBike, String> regDate;

    @FXML
    private URL location;

    @FXML
    private ImageView bikePicture;

    @FXML
    private ComboBox<String> bikeList;

    @FXML
    private TextField groupSet;

    @FXML
    private TextField weight;

    @FXML
    private TextArea bikeInfo;

    @FXML
    void bikeShow(ActionEvent event) {
        // Pozitia selectata
        int nr = bikeList.getSelectionModel().getSelectedIndex();
        // Extrag din lista obiectul de pe pozitia nr
        RoadBike bycicle = bList.get(nr);
        // Inserez datele in contoale din fereastra:
        groupSet.setText(String.valueOf(bycicle.grSet));
        weight.setText(String.valueOf(bycicle.wght));
        bikeInfo.setText(String.valueOf(bycicle.bInfo));
        bikePicture.setImage(bycicle.bPicture);
    }
    
    void saveData(Document doc) {
        try {
            //  Salvez pe disc
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource sursa = new DOMSource(doc);
            FileOutputStream fo = new FileOutputStream(cale);
            StreamResult rezultat = new StreamResult(fo);
            transformer.transform(sursa, rezultat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void delete_user(ActionEvent event) {
         //  Caut in doc angajatul idAng:
        NodeList lista = doc.getElementsByTagName("userBike");
        //  Parcurg lista de elemente de tip 'angajat'
        for (int contor = 0; contor < lista.getLength(); contor++) {
            Element angajat = (Element) lista.item(contor);
            int idCurent = Integer.parseInt(angajat.getAttribute("id"));

            if (idCurent == idReg) {
                //  L-am gasit. Il sterg din doc
                angajat.getParentNode().removeChild(angajat);
                //  Sterg obiectul corespunzator din din lAngajati
                userList.remove(indexSelect);  
                //  Repopulez tabelul
                regUsers.getItems().clear();
                for (UserBike an : userList) {
                    regUsers.getItems().add(an);
                }
                //  Salvez si pe disc
                saveData(doc);
                //  Golesc controalele
                regSurname.setText(null);
                regName.setText(null);
                regBikemodel.setText(null);
                regDate.setText(null);
                break;  //  Intrerup ciclul de cautare
            }
        }
    }

    @FXML
    void edit_user(ActionEvent event) {
        editStage.showAndWait();
    }

    @FXML
    void exit_app(ActionEvent event) {
        Platform.exit();
    }
    

    @FXML
    void info_price(ActionEvent event) {
        infoStage.showAndWait();
    }

    @FXML
    void rentNow(ActionEvent event) {
        rentStage.showAndWait();
        
    }

    @FXML
    void initialize() {

        bList = new ArrayList();
        RoadBike bycicle = new RoadBike("Scott FOIL 40 105 Carbon", "Shimano", 8.2, "A dedicated group of engineers "
                + "within the Scott organization was charged with the task of innovating "
                + "the most aerodynamic bicycles possible.", "img_bikes/r1.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Cervélo R2 105", "Shimano", 7.7, "This bike is based on the "
                + "ultralight and super stiff R3 carbon race frame which was victorious in Paris-Roubaix "
                + "three times although being the lightest frame in that race.", "img_bikes/r2.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Simplon PAVO 3 Ultegra 20", "Shimano", 7.3, "840 g lightweight frame with superb handling, "
                + "classical aesthetics and completely internal cable routing. "
                + "The latest technologies of carbon processing combined with super lightweight, "
                + "extremely durable fibers guarantee this claim. Race geometry, optimal power transfer "
                + "and high driving comfort enable a new level of road bike riding. ", "img_bikes/r3.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Colnago CX-ZERO ALU Veloce", "Campagnolo", 8.05, "The CX Zero Alu is the aluminum "
                + "version of the latest Colnago frame developed for endurance. The geometry has been designed to meet the "
                + "needs of gran fondo riders, or for anyone looking for a bit more comfort on longer rides.", "img_bikes/r4.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Trek DOMANE SLR 6 DISC", "Shimano", 6.9, "Domane with front and rear IsoSpeed has it all: Blistering speed, "
                + "smooth race comfort, and superior balance for precision handling and all-day domination. ", "img_bikes/r5.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Look 566 105 Carbon", "Shimano", 8.1, "The 566 frameset has been designed and fine tuned for responsiveness "
                + "and a comfortable ride. This frame was developed for those cyclists who want to ride for several kilometers/miles "
                + "at a fast pace to a leisurely pace. This bike is for cyclists looking for comfort and will do on occasional "
                + "race or leisure tour.", "img_bikes/r6.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Colnago V1-R Super Record", "Campagnolo", 6.6, "The V1-r is the new frame from Colnago designed for cyclists "
                + "who are particular about their bike’s weight-to-performance ratio. It is made with the highest quality carbon fiber, "
                + "selected in cooperation with Ferrari, which is why is we use the Cavallino logo on each frame.", "img_bikes/r7.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Colnago C60 Dura Ace", "Shimano ", 6.5, "The ultimate goal is to improve the bicycle: to make it more efficient, "
                + "with less rider fatigue and no sacrifice in strength or reliability. The design must be striking. From a marketing standpoint, "
                + "the frame’s characteristics and technical advantages must stand out and not be completely hidden inside the frame.", "img_bikes/r8.jpg");
        bList.add(bycicle);

        bycicle = new RoadBike("Cervélo R5 Dura Ace Di2", "Shimano", 6.7, "The R5 frameset is be the production version of the RCA version, and will share "
                + "Squoval 3 shapes to improve aerodynamics and stiffness. The R5 brings Cervelo's RCA engineering into the regular line- up. "
                + "With the new Squoval 3 TM tube shapes, aero meets ultimate lightweight design. Sure-footed riding is assured with clearance "
                + "for 700x25c tires. Climbing and cutting through the wind with ease are matched "
                + "with excellent stability and control.", "img_bikes/r9.jpg");
        bList.add(bycicle);

        for (RoadBike a : bList) {
            bikeList.getItems().add(a.bName);
        }
        //saving Data in .XML
        regId.setCellValueFactory(cellData -> cellData.getValue().id.asObject());
        regSurname.setCellValueFactory(cellData -> cellData.getValue().enterSurname);
        regName.setCellValueFactory(cellData -> cellData.getValue().enterName);
        regBikemodel.setCellValueFactory(cellData -> cellData.getValue().enterBikemodel);
        regDate.setCellValueFactory(cellData -> cellData.getValue().enterDate);

        regUsers.getSelectionModel().selectedIndexProperty().addListener((obiect, valVeche, valNoua) -> {
            // Se trateaza schimbarea starii. 
            indexSelect = (int) valNoua;
            
            if (indexSelect >= 0) {
                UserBike a = userList.get(indexSelect);
                regSurname.setText(a.enterSurname.get());
                regName.setText(a.enterName.get());
                regBikemodel.setText(a.enterBikemodel.get());
                regDate.setText(a.enterDate.get());
                idReg = a.id.get();
            }
        });
        userList = new ArrayList();
        cale = "user_data.xml";  // Fisierul este in directorul radacina
        //  Se incarca din fisier datele angajatilor
        try {
            fXml = new File(cale);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXml);
            doc.getDocumentElement().normalize();
            Element radacina = doc.getDocumentElement();
            //  Nodul radacina este <userss>
            //  Lista de noduri tip <userBike>
            NodeList lista = doc.getElementsByTagName("userBike");
            //  Parcurg lista de elemente de tip 'UserBike'
            for (int contor = 0; contor < lista.getLength(); contor++) {
                Element userBike = (Element) lista.item(contor);
                int id = Integer.parseInt(userBike.getAttribute("id"));
                String surn = userBike.getElementsByTagName("enterSurname").item(0).getTextContent();
                String num = userBike.getElementsByTagName("enterName").item(0).getTextContent();
                String bkm = userBike.getElementsByTagName("enterBikemodel").item(0).getTextContent();
                String dta = userBike.getElementsByTagName("enterDate").item(0).getTextContent();

                UserBike usr = new UserBike(id, num, surn, bkm, dta);
                userList.add(usr);
                regUsers.getItems().add(usr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //The two windows
        try {
            //1. încarc fisierul fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("informationWindow.fxml"));
            //2. creez un container care va conţine fereastra
            AnchorPane container = (AnchorPane) loader.load();
            //3. preiau obiectul controller al ferestrei de dialog
            ctrlInfo = loader.getController();
            //4. impun titlul ferestrei adaugate
            infoStage.setTitle("info/price");
            //5. impun tipul ferestrei (modalã sau nemodalã)
            infoStage.initModality(Modality.NONE);
            //6. creez scena care contine interfata ferestrei
            Scene scena = new Scene(container);
            //7. ataşez obiectului informationWindow (Stage) scena
            infoStage.setScene(scena);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }

        try {
            //1. încarc fisierul fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userWindow.fxml"));
            //2. creez un container care va conţine fereastra
            AnchorPane container = (AnchorPane) loader.load();
            //3. preiau obiectul controller al ferestrei de dialog
            ctrlUserWindow = loader.getController();
            //4. impun titlul ferestrei adaugate
            rentStage.setTitle("Rent NOW!");
            //5. impun tipul ferestrei (modalã sau nemodalã)
            rentStage.initModality(Modality.NONE);
            //6. creez scena care contine interfata ferestrei
            Scene scena = new Scene(container);
            rentStage.setScene(scena);
            ctrlUserWindow.ctrl = this;
            //7. ataşez obiectului userWindow (Stage) scena
            rentStage.setScene(scena);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
        try {
            //1. încarc fisierul fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editWindow.fxml"));
            //2. creez un container care va conţine fereastra
            AnchorPane container = (AnchorPane) loader.load();
            //3. preiau obiectul controller al ferestrei de dialog
            ctrlEditWindow = loader.getController();
            //4. impun titlul ferestrei adaugate
            editStage.setTitle("Edit User");
            //5. impun tipul ferestrei (modalã sau nemodalã)
            editStage.initModality(Modality.NONE);
            //6. creez scena care contine interfata ferestrei
            Scene scena = new Scene(container);
            editStage.setScene(scena);
            ctrlEditWindow.ctrl = this;
            //7. ataşez obiectului userWindow (Stage) scena
            editStage.setScene(scena);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
}
