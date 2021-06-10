package model.weapon;

import javafx.scene.image.Image;
import model.gamemanager.GameManager;
import model.utils.Utils;

public abstract class Weapon {
    public static double DEFAULT_DAMAGE = 10, DEFAULT_INTEGRITY = 1, DEFAULT_MISS_CHANCE = 0.05;

    public double damage;
    public double integrity ;
    public double missChance;
    public String name;
    public Image image;

    GameManager gameManager = GameManager.getInstance();

    public double calcDamage() {
        damage = Utils.round(this.damage - integrity);
        if (damage < 1)
            damage = 1;
        return damage;
    }

    public final boolean missed() {
        if (Math.random() < missChance) {
            gameManager.printAction(name + " ПРОМАХ!");
            return true;
        }
        return false;
    }

    public Weapon(double damage, double integrity, double missChance, String name) {
        this.damage = damage;
        this.integrity = integrity;
        this.missChance = missChance;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "damage=" + damage +
                ", integrity=" + integrity +
                ", missChance=" + missChance +
                '}';
    }
}
