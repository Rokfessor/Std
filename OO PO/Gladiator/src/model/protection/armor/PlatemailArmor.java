package model.protection.armor;

import model.actions.Action;
import model.actions.DefendAction;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.utils.Utils;

public class PlatemailArmor extends Protection {
    public PlatemailArmor() {
        super(0.8,0.02);

        action = new DefendAction() {
            @Override
            public void doAction(Gladiator me, Gladiator enemy, int ATTACK_TYPE, double damage) {
                me.weapon.damage = Utils.round(me.weapon.damage + 0.04);
            }
        };
    }
}
