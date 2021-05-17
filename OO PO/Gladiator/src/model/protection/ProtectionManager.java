package model.protection;

import model.protection.armor.BladeArmor;
import model.protection.armor.CuirassArmor;
import model.protection.armor.PlatemailArmor;
import model.protection.armor.TarasqueArmor;
import model.protection.greaves.BladeGreaves;
import model.protection.greaves.CuirassGreaves;
import model.protection.greaves.PlatemailGreaves;
import model.protection.greaves.TarasqueGreaves;
import model.protection.helmet.BladeHelmet;
import model.protection.helmet.CuirassHelmet;
import model.protection.helmet.PlatemailHelmet;
import model.protection.helmet.TarasqueHelmet;

public class ProtectionManager {
    public static Protection getProtection(String name) {
        return switch (name) {
            case "helmet1" -> new BladeHelmet();
            case "helmet2" -> new CuirassHelmet();
            case "helmet3" -> new PlatemailHelmet();
            case "helmet4" -> new TarasqueHelmet();

            case "armor1" -> new BladeArmor();
            case "armor2" -> new CuirassArmor();
            case "armor3" -> new PlatemailArmor();
            case "armor4" -> new TarasqueArmor();

            case "greaves1" -> new BladeGreaves();
            case "greaves2" -> new CuirassGreaves();
            case "greaves3" -> new PlatemailGreaves();
            case "greaves4" -> new TarasqueGreaves();

            default -> null;
        };
    }
}
