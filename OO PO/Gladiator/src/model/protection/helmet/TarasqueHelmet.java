package model.protection.helmet;

import model.actions.DefendAction;
import model.gladiator.Gladiator;
import model.protection.Protection;

public class TarasqueHelmet extends Protection {
    public TarasqueHelmet() {
        super(0.3,0.04);

        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                me.health += 2;
            }
        };
    }
}
