package model.weapon;

import javafx.scene.image.Image;

public class Trident extends Weapon {
    public Trident() {
        super(10, DEFAULT_INTEGRITY, 0.25, "Трезубец");
        image = new Image("sprites/weapon4.png", 300,300,false,false);
    }
}