package model.protection.helmet;

import javafx.scene.image.Image;
import model.protection.Protection;

public class CuirassHelmet extends Protection {
    public CuirassHelmet() {
        super(1,0.1);
        image = new Image("sprites/helmet2.png", 170, 170, false, false);
    }
}
