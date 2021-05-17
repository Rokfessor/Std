package model.gamemanager;

import model.gladiator.Gladiator;
import model.protection.Protection;

public class GameManager {
    private static GameManager manager;
    private Player player1;
    private Player player2;

    public static GameManager getInstance() {
        if (manager == null)
            return new GameManager();
        else
            return manager;
    }

    private GameManager() {}

    public void start() {

    }

    public void newGame() {
        player1 = new Player();
        player2 = new Player();
    }

    public void setPlayer1Glagiator(Gladiator gladiator, String type) {
        player1.gladiator = gladiator;
    }

    public void setPlayer2Glagiator(Protection helmet, String type) {
        player2.gladiator.helmet = helmet;
    }

    public void setPlayer1Helmet(Protection helmet, String type) {
        player1.gladiator.helmet = helmet;
    }

    public void setPlayer2Helmet(Protection helmet, String type) {
        player2.gladiator.helmet = helmet;
    }

    public void setPlayer1Armor(Protection armor, String type) {
        player1.gladiator.armor = armor;
    }

    public void setPlayer2Armor(Protection armor, String type) {
        player2.gladiator.armor = armor;
    }

    public void setPlayer1Greaves(Protection greaves, String type) {
        player1.gladiator.greaves = greaves;
    }

    public void setPlayer2Greaves(Protection greaves, String type) {
        player2.gladiator.greaves = greaves;
    }
}
