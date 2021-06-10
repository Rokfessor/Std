package model.gladiator;

import javafx.scene.image.Image;
import model.actions.Action;
import model.actions.Attack;
import model.actions.Block;
import model.actions.Debuff;
import model.gamemanager.GameManager;

public class Trax extends Gladiator {
    public Trax() {
        health = maxHealth = 80;
        damage = 3;
        name = "Тракс";
        image = new Image("sprites/gladiator2.png", 170, 170, false, false);

        debuff = new Debuff(2, 0.3) {
            @Override
            public Action onActionStart(int actionType) {
                if (actionType == Action.ATTACK) {
                    count--;
                    return new Action() {
                        @Override
                        public void doAction(Gladiator me, Gladiator enemy) {
                            GameManager.getInstance().printAction(name + ": Здоровье поменяно");
                            double temp = me.health;
                            me.health = enemy.health;
                            enemy.health = temp;
                        }
                    };
                }
                return null;
            }

            @Override
            public Action onActionEnd(int actionType) {
                return null;
            }
        };

        attack = new Attack() {
            @Override
            public double doAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT) {
                if (debuff.isEjected())
                    enemy.getDebuff(debuff);

                if (ATTACK_TYPE == Attack.WEAPON) {
                    if (weapon.missed())
                        return 0;
                    else
                        return weapon.calcDamage();
                } else {
                    return damage;
                }
            }
        };

        block = new Block() {
            @Override
            public double doBlock(Gladiator me, Gladiator enemy, double damage, int ATTACK_POINT, int ATTACK_TYPE) {
                switch (ATTACK_POINT) {
                    case Attack.HEAD_ATTACK -> {
                        if (helmet != null)
                            damage = helmet.defend(me, enemy, damage, ATTACK_TYPE);
                    }
                    case Attack.BODY_ATTACK -> {
                        if (armor != null)
                            damage = armor.defend(me, enemy, damage, ATTACK_TYPE);
                    }
                    case Attack.LEG_ATTACK -> {
                        if (greaves != null)
                            damage = greaves.defend(me, enemy, damage, ATTACK_TYPE);
                    }
                }
                return damage;
            }
        };
    }
}

