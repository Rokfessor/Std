package model.protection.greaves;

import javafx.scene.image.Image;
import model.protection.Protection;

public class CuirassGreaves extends Protection {
    public CuirassGreaves() {
        super(1,0.1);
        image = new Image("sprites/greaves2.png", 200, 200, false, false);
    }
}
