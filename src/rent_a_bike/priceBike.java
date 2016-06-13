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
public class priceBike {
    SimpleStringProperty bEquip;
    SimpleIntegerProperty bPrice; 

    public priceBike(String bEquip, int bPrice) {
        this.bEquip = new SimpleStringProperty(bEquip);
        this.bPrice = new SimpleIntegerProperty(bPrice);
    }
}
