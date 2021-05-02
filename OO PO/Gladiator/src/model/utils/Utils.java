package model.utils;

public class Utils {
    public static boolean calcLuck(double luck){
        return Math.random() * 100 < luck;
    }
}
