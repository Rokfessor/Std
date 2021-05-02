package model.gamemanager;

import model.gladiator.Gladiator;
import model.gladiator.Hoplomachus;
import model.gladiator.Trax;

public class GameManager {
    public GameManager() {
    }

    public void start() {
        Gladiator hoplomachus = new Hoplomachus();
        Gladiator trax = new Trax();

        hoplomachus.doAttack(trax);
    }
}
