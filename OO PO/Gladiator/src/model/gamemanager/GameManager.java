package model.gamemanager;

import controller.BattleController;
import model.gladiator.Gladiator;
import model.protection.Protection;
import model.weapon.Weapon;

public class GameManager {
    private static GameManager manager;
    public Gladiator gladiator1;
    public Gladiator gladiator2;
    private BattleController battleController;

    public static GameManager getInstance() {
        if (manager == null)
            manager = new GameManager();
        return manager;
    }

    private GameManager() {
    }

    public void setBattleController(BattleController battleController) {
        this.battleController = battleController;
    }

    public void start() {

    }

    public void printAction(String action) {
        battleController.printAction(action);
    }

    public void setPlayer1Gladiator(Gladiator gladiator, String type) {
        gladiator1 = gladiator;
    }

    public void setPlayer2Gladiator(Gladiator gladiator, String type) {
        gladiator2 = gladiator;
    }

    public void setPlayer1Helmet(Protection helmet, String type) {
        gladiator1.helmet = helmet;
    }

    public void setPlayer2Helmet(Protection helmet, String type) {
        gladiator2.helmet = helmet;
    }

    public void setPlayer1Armor(Protection armor, String type) {
        gladiator1.armor = armor;
    }

    public void setPlayer2Armor(Protection armor, String type) {
        gladiator2.armor = armor;
    }

    public void setPlayer1Greaves(Protection greaves, String type) {
        gladiator1.greaves = greaves;
    }

    public void setPlayer2Greaves(Protection greaves, String type) {
        gladiator2.greaves = greaves;
    }

    public void setPlayer1Weapon(Weapon weapon, String type) {
        gladiator1.weapon = weapon;
    }

    public void setPlayer2Weapon(Weapon weapon, String type) {
        gladiator2.weapon = weapon;
    }
}
