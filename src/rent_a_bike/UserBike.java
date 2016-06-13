/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rent_a_bike;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Bogdan
 */
public class UserBike {
    SimpleIntegerProperty id;
    SimpleStringProperty enterSurname;
    SimpleStringProperty enterName;
    SimpleStringProperty enterBikemodel;
    SimpleStringProperty enterDate;

    public UserBike(Integer id, String enterSurname, String enterName, String enterBikemodel, String enterDate) {
        this.id = new SimpleIntegerProperty(id);
        this.enterSurname = new SimpleStringProperty(enterSurname);
        this.enterName = new SimpleStringProperty(enterName);
        this.enterBikemodel = new SimpleStringProperty(enterBikemodel);
        this.enterDate = new SimpleStringProperty(enterDate);
    }
    
}
