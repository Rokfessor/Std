package model.gladiator;

import model.actions.Action;
import model.actions.Attack;
import model.actions.Block;
import model.actions.Debuff;
import model.protection.Protection;
import model.protection.Shield;
import model.weapon.Weapon;

public class Hoplomachus extends Gladiator {
    public Hoplomachus(Protection helmet, Protection armor, Protection greaves, Shield shield, Weapon weapon) {
        health = 100;
        this.helmet = helmet;
        this.armor = armor;
        this.greaves = greaves;
        this.shield = shield;
        this.weapon = weapon;
        damage = 5;

        debuff = new Debuff(5) {
            @Override
            public Action onActionStart(int actionType) {
                if (actionType == Action.ATTACK) {
                    if (count == 5) {
                        return new Action() {
                            @Override
                            public void doAction(Gladiator me, Gladiator enemy) {
                                if (enemy.weapon != null)
                                    enemy.weapon.damage -= 5;
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
            public double doBlock(Gladiator me, Gladiator enemy, double damage, int ATTACK_POINT) {
                if (evade())
                    return 0;
                else {
                    if (shield != null && shield.blocked())
                        return shield.defend(me, enemy,damage);
                    else {
                        switch (ATTACK_POINT) {
                            case Attack.HEAD_ATTACK -> {
                                if (helmet != null)
                                    damage = helmet.defend(me, enemy, damage);
                            }
                            case Attack.BODY_ATTACK -> {
                                if (helmet != null)
                                    damage = armor.defend(me, enemy, damage);
                            }
                            case Attack.LEG_ATTACK -> {
                                if (helmet != null)
                                    damage = greaves.defend(me, enemy, damage);
                            }
                        }
                        return damage;
                    }
                }
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
