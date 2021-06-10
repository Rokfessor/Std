package model.protection.greaves;

import javafx.scene.image.Image;
import model.actions.DefendAction;
import model.gamemanager.GameManager;
import model.gladiator.Gladiator;
import model.protection.Protection;

public class TarasqueGreaves extends Protection {
    public TarasqueGreaves() {
        super(1,0.2);
        image = new Image("sprites/greaves4.png", 200, 200, false, false);
        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                GameManager.getInstance().printAction(me.name + ": бронёй восстановлены 2 жизни");
                me.health += 15;
            }
        };
    }
}
