package model.gladiator;

public class GladiatorManager {
    public static Gladiator getGladiator(String type) {
        switch (type) {
            case "gladiator1" -> {
                return new Hoplomachus();
            }

            case "gladiator2" -> {
                return new Trax();
            }

            case "gladiator3" -> {
                return new Spartak();
            }

            case "gladiator4" -> {
                return new Sekutor();
            }

            default -> {return null;}
        }
    }
}
