package model.weapon;

public class WeaponManager {
    public static Weapon getWeapon(String name) {
        return switch (name) {
            case "weapon1" -> new Sword();
            case "weapon2" -> new Axe();
            case "weapon3" -> new Spear();
            case "weapon4" -> new Trident();

            default -> null;
        };
    }
}
