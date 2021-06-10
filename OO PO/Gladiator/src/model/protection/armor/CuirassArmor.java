package model.protection.armor;

import javafx.scene.image.Image;
import model.protection.Protection;

public class CuirassArmor extends Protection {
    public CuirassArmor() {
        super(1,0.1);
        image = new Image("/sprites/armor2.png", 300, 300, false, false);
    }
}
