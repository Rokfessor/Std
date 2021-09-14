package model;

import java.util.ArrayList;
import java.util.List;

public class Regex {
    public static List<Integer> t11(String text) {
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^([abc]*)((a([abc]*)b)|(b([abc]*)a))([abc]*)$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t12(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^[01]*1([01]{9})$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t13(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^(0|(10))*(11)?(0|(01))*$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t21(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^((01|1)*(0011)+0?(01|1)*)*$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t22(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^(((1*0)|(01*)|0){5})*$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t31(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^1*((00|1|000)*)0*$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t32(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^((01)*|(10)*|(0110)*|(1001)*)*$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t33(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^((101|1)*(1100)+0?(011|1)*2)*5$"))
                res.add(i);
        }
        return res;
    }

    public static List<Integer> t41(String text){
        String[] lines = text.split(" ");
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].matches("^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$"))
                res.add(i);
        }
        return res;
    }

    public static boolean t51(String text){
        return text.matches("([^0-9]){0,20}[0-9]{1,10}([.,])?[0-9]{0,2}([^0-9]){0,20}");
    }
}
