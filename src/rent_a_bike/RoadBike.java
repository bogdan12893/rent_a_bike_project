/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rent_a_bike;

import javafx.scene.image.Image; 


/**
 *
 * @author Bogdan
 */
public class RoadBike {
    String bName;
    String grSet;
    double wght;
    String bInfo;
    Image bPicture;

    public RoadBike(String bName, String grSet, double wght, String bInfo, String calebPicture) {
        this.bName = bName;
        this.grSet = grSet;
        this.wght = wght;
        this.bInfo = bInfo;
        this.bPicture = new Image(calebPicture);
    }

}