package model.gladiator;

import model.actions.Action;
import model.actions.Attack;
import model.actions.Block;
import model.actions.Debuff;
import model.protection.Protection;
import model.utils.Utils;
import model.weapon.Weapon;

public class Trax extends Gladiator {
    public Trax(Protection protection, Weapon weapon) {

        debuff = new Debuff(2, 0.3) {
            @Override
            public Action onActionStart(int actionType) {
                if (actionType == Action.ATTACK) {
                    count--;
                    return new Action() {
                        @Override
                        public void doAction(Gladiator me, Gladiator enemy) {
                            me.health += 10;
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

                if (evade())
                    return 0;
                else {
                    if (shield != null && shield.blocked())
                        return shield.defend(me, enemy, damage, ATTACK_TYPE);
                    else if (protection != null) {
                        return protection.defend(me, enemy, damage, ATTACK_TYPE);
                    } else
                        return damage;
                }
            }
        };

        superAttack = new Attack() {
            @Override
            public double doAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT) {
                getDamage(Utils.round(health * 0.5));
                return Utils.round(enemy.health * 0.5);
            }
        };
    }
}

