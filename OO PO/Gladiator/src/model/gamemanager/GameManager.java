package model.gamemanager;

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

    public void setPlayer1Armor(Protection armor) {
        player1.gladiator.armor = armor;
    }

    public void setPlayer2Armor(Protection armor) {
        player2.gladiator.armor = armor;
    }
}
