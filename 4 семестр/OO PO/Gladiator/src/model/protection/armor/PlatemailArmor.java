package model.protection.armor;

import javafx.scene.image.Image;
import model.actions.DefendAction;
import model.gamemanager.GameManager;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class PlatemailArmor extends Protection {
    public PlatemailArmor() {
        super(1,0.1);
        image = new Image("/sprites/armor3.png", 300, 300, false, false);
        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                if (me.weapon != null) {
                    me.weapon.damage = Utils.round(me.weapon.damage + 0.5);
                } else {
                    me.damage += 0.5;
                }
                GameManager.getInstance().printAction(me.name + ": бронёй увеличен урон на 0.5");
            }
        };
    }
}
