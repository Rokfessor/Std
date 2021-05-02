package model.gladiator;

import model.actions.*;
import model.protection.Protection;
import model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Gladiator {
    public final int ACTION_ATTACK = 0, ACTION_SUPER_ATTACK = 1, ACTION_BLOCK = 2, ACTION_GET_ATTACK = 3, ACTION_TIME = 4;

    public double health = 0;
    public double luck = 1;
    public Protection[] protection = null;
    public Weapon weapon = null;
    public List<Debuff> debuffs = new ArrayList<>();
    public Debuff skill = null;

    public Action attack = new Action() {
        @Override
        public void doAction(Gladiator me, Gladiator enemy) {
        }
    };

    public Action superAttack = new Action() {
        @Override
        public void doAction(Gladiator me, Gladiator enemy) {
        }
    };

    public Action block = new Action() {
        @Override
        public void doAction(Gladiator me, Gladiator enemy) {

        }
    };

    public Action getAttack = new Action() {
        @Override
        public void doAction(Gladiator me, Gladiator enemy) {

        }
    };

    public Action[] action(int actionType) {
        List<Action> list = new ArrayList<>();

        for (Debuff debuff : debuffs) {
            if (debuff.isActive()) {
                Action action = debuff.onActionStart(this, actionType);
                if (action != null)
                    list.add(action);
            }
        }

        return list.toArray(new Action[0]);
    }

    public Action[] actionIsEnd(int actionType) {
        List<Action> list = new ArrayList<>();

        for (Debuff debuff : debuffs) {
            if (debuff.isActive()) {
                Action action = debuff.onActionEnd(this, actionType);
                if (action != null)
                    list.add(action);
            }
        }

        return list.toArray(new Action[0]);
    }

    public void doAttack(Gladiator enemy) {
        for (Action action : action(ACTION_ATTACK))
            action.doAction(this, enemy);

        attack.doAction(this, enemy);

        for (Action action : actionIsEnd(ACTION_ATTACK))
            action.doAction(this, enemy);
    }

    public void doSuperAttack(Gladiator enemy) {
        for (Action action : action(ACTION_SUPER_ATTACK))
            action.doAction(this, enemy);

        superAttack.doAction(this, enemy);

        for (Action action : actionIsEnd(ACTION_ATTACK))
            action.doAction(this, enemy);
    }

    public void doBlock(Gladiator enemy) {
        for (Action action : action(ACTION_BLOCK))
            action.doAction(this, enemy);

        block.doAction(this, enemy);

        for (Action action : actionIsEnd(ACTION_ATTACK))
            action.doAction(this, enemy);
    }

    public void doGetAttack(Gladiator enemy) {
        for (Action action : action(ACTION_BLOCK))
            action.doAction(this, enemy);

        getAttack.doAction(this, enemy);

        for (Action action : actionIsEnd(ACTION_ATTACK))
            action.doAction(this, enemy);
    }
}