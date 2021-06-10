package model.protection.helmet;

import javafx.scene.image.Image;
import model.actions.DefendAction;
import model.gamemanager.GameManager;
import model.gladiator.Gladiator;
import model.protection.Protection;

public class TarasqueHelmet extends Protection {
    public TarasqueHelmet() {
        super(1, 0.2);
        image = new Image("sprites/helmet4.png", 170, 170, false, false);

        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                GameManager.getInstance().printAction(me.name + ": бронёй восстановлены 2 жизни");
                me.health += 15;
            }
        };
    }
}
