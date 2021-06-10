package model.weapon;

import javafx.scene.image.Image;

public class Axe extends Weapon {
    public Axe() {
        super(12, DEFAULT_INTEGRITY, 0.5, "Топор");
        image = new Image("sprites/weapon2.png", 300,300,false,false);
    }
}
