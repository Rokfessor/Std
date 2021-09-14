package model.weapon;

import javafx.scene.image.Image;

public class Spear extends Weapon {
    public Spear() {
        super(7, DEFAULT_INTEGRITY, 0.2, "Копьё");
        image = new Image("sprites/weapon3.png", 300,300,false,false);
    }
}