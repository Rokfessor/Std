package model.protection.helmet;

import javafx.scene.image.Image;
import model.actions.Attack;
import model.actions.DefendAction;
import model.gamemanager.GameManager;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class BladeHelmet extends Protection {
    public BladeHelmet() {
        super(1,0.15);
        image = new Image("sprites/helmet1.png", 170, 170, false, false);
        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                double dam = 0;
                if (ATTACK_TYPE == Attack.WEAPON)
                    dam = Utils.round(damage / 2);
                else
                    dam = Utils.round(damage);
                GameManager.getInstance().printAction(me.name + ": Удар отражен бронёй " + (Utils.round(dam)));
                enemy.getDamage(dam);
            }
        };
    }
}
