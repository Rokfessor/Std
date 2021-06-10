package model.gladiator;

import javafx.scene.image.Image;
import model.actions.Action;
import model.actions.Attack;
import model.actions.Block;
import model.actions.Debuff;
import model.gamemanager.GameManager;

public class Hoplomachus extends Gladiator {
    public Hoplomachus() {
        health = maxHealth = 100;
        damage = 5;
        name = "Гопломак";
        image = new Image("sprites/gladiator1.png", 170, 170, false, false);

        debuff = new Debuff(5, 0.3) {
            @Override
            public Action onActionStart(int actionType) {
                if (actionType == Action.ATTACK) {
                    if (count == 5) {
                        return new Action() {
                            @Override
                            public void doAction(Gladiator me, Gladiator enemy) {
                                if (enemy.weapon != null) {
                                    enemy.weapon.damage -= 5;
                                    GameManager.getInstance().printAction(enemy.name + ": урон от атаки снижен на 5");
                                }
                            }
                        };
                    }
                    count--;
                }
                return null;
            }

            @Override
            public Action onActionEnd(int actionType) {
                if (actionType == Action.ATTACK) {
                    if (count == 0) {
                        return new Action() {
                            @Override
                            public void doAction(Gladiator me, Gladiator enemy) {
                                if (enemy.weapon != null)
                                    enemy.weapon.damage += 5;
                            }
                        };
                    }
                }
                return null;
            }

            @Override
            public String toString() {
                return "гопломаковская ксреньт";
            }
        };

        attack = new Attack() {
            @Override
            public double doAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT) {
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
                if (debuff.isEjected())
                    enemy.getDebuff(debuff);

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

        superAttack = new Attack() {
            @Override
            public double doAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT) {
                if (weapon != null)
                    return 2 * weapon.calcDamage();
                else
                    return 2 * damage;
            }
        };
    }
}
