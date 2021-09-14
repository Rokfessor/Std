package model.gladiator;

import javafx.scene.image.Image;
import model.actions.*;
import model.gamemanager.GameManager;
import model.protection.Protection;
import model.protection.shield.Shield;
import model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Gladiator {
    public double health;
    public double maxHealth;
    public double debuffChance = 0.5;
    public double damage;
    public String name;

    public Protection helmet;
    public Protection armor;
    public Protection greaves;
    public Shield shield;
    public Weapon weapon;

    public Image image;

    public List<Debuff> debuffs = new ArrayList<>();

    public Debuff debuff;
    public Attack attack;
    public Attack superAttack;
    public Block block;

    private GameManager gameManager = GameManager.getInstance();

    public List<Action> actionIsStart(int actionType) {
        List<Action> list = new ArrayList<>();

        for (Debuff debuff : debuffs) {
            System.err.println("111!!!");
            if (debuff.isActive()) {
                Action action = debuff.onActionStart(actionType);
                if (action != null)
                    list.add(action);
            }
        }

        return list;
    }

    public void getDebuff(Debuff debuff) {
        debuffs.add(debuff);
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
        for (Action action : actionIsStart(Action.ATTACK)) {
            action.doAction(this, enemy);
        }


        double damage = attack.doAttack(enemy, ATTACK_TYPE, ATTACK_POINT);
        gameManager.printAction("Гладиатор " + name + " нанёс урон равный " + damage + (ATTACK_TYPE == Attack.WEAPON ? " оружием '" + weapon.name + "'": " рукой"));
        double blockedDamage = enemy.doBlock(this, damage, ATTACK_POINT, ATTACK_TYPE);
        enemy.getDamage(blockedDamage);

        //TODO добавить skill

        for (Action action : actionIsEnd(Action.ATTACK))
            action.doAction(this, enemy);
    }

    public final double doBlock(Gladiator enemy, double damage, int ATTACK_POINT, int ATTACK_TYPE) {
        for (Action action : actionIsStart(Action.BLOCK))
            action.doAction(this, enemy);

        damage = block.doBlock(this, enemy, damage, ATTACK_POINT, ATTACK_TYPE);

        for (Action action : actionIsEnd(Action.BLOCK))
            action.doAction(this, enemy);

        return damage;
    }

    public final void getDamage(double damage) {
        gameManager.printAction("Гладиатор " + name + " получил урон равный " + damage);
        health -= damage;
    }

    public final void doSuperAttack(Gladiator enemy, int ATTACK_TYPE, int ATTACK_POINT) {
        for (Action action : actionIsStart(Action.SUPER_ATTACK))
            action.doAction(this, enemy);

        superAttack.doAttack(enemy, ATTACK_TYPE, ATTACK_POINT);

        for (Action action : actionIsEnd(Action.SUPER_ATTACK))
            action.doAction(this, enemy);
    }

    public final boolean debuff() {
        return Math.random() < debuffChance;
    }

    @Override
    public String toString() {
        return "Gladiator{" +
                "health=" + health +
                ", maxHealth=" + maxHealth +
                ", evadeChance=" + debuffChance +
                ", damage=" + damage +
                ", helmet=" + helmet +
                ", armor=" + armor +
                ", greaves=" + greaves +
                ", shield=" + shield +
                ", weapon=" + weapon +
                '}';
    }
}