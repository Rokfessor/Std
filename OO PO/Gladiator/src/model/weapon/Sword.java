package model.weapon;

import javafx.scene.image.Image;

public class Sword extends Weapon {
    public Sword() {
        super(15, Weapon.DEFAULT_INTEGRITY, 0.65, "Меч");
        image = new Image("sprites/weapon1.png", 300,300,false,false);
    }
}
