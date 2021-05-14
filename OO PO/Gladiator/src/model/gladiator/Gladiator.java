package model.gladiator;

import model.actions.*;
import model.protection.Protection;
import model.protection.Shield;
import model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Gladiator {
    public double health;
    public double evadeChance;
    public double damage;

    public Protection helmet;
    public Protection armor;
    public Protection greaves;
    public Shield shield;
    public Weapon weapon;

    public List<Debuff> debuffs = new ArrayList<>();

    public Debuff debuff ;
    public Attack attack;
    public Attack superAttack;
    public Block block;


    public List<Action> actionIsStart(int actionType) {
        List<Action> list = new ArrayList<>();

        for (Debuff debuff : debuffs) {
            if (debuff.isActive()) {
                Action action = debuff.onActionStart(actionType);
                if (action != null)
                    list.add(action);
            }
        }

        return list;
    }

    public List<Action> actionIsEnd(int actionType) {
        List<Action> list = new ArrayList<>();

        for (Debuff debuff : debuffs) {
            if (debuff.isActive()) {
                Action action = debuff.onActionEnd(actionType);
                if (action != null)
                    list.add(action);
            }
        }

        return list;
    }

    public final void doAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT) {
        for (Action action : actionIsStart(Action.ATTACK))
            action.doAction(this, enemy);

        double damage = attack.doAttack(enemy, ATTACK_TYPE, ATTACK_POINT);
        double blockedDamage = enemy.doBlock(this, damage, ATTACK_POINT);
        enemy.getDamage(blockedDamage);

        //TODO добавить skill

        for (Action action : actionIsEnd(Action.ATTACK))
            action.doAction(this, enemy);
    }

    public final double doBlock(Gladiator enemy, double damage, int ATTACK_POINT) {
        for (Action action : actionIsStart(Action.BLOCK))
            action.doAction(this, enemy);

        damage = block.doBlock(this, enemy, damage, ATTACK_POINT);

        for (Action action : actionIsEnd(Action.BLOCK))
            action.doAction(this, enemy);

        return damage;
    }

    public final void getDamage(double damage) {
        health -= damage;
    }

    public final void doSuperAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT) {
        for (Action action : actionIsStart(Action.SUPER_ATTACK))
            action.doAction(this, enemy);

        superAttack.doAttack(enemy, ATTACK_TYPE, ATTACK_POINT);

        for (Action action : actionIsEnd(Action.SUPER_ATTACK))
            action.doAction(this, enemy);
    }

    public final boolean evade() {
        return Math.random() < evadeChance;
    }
}