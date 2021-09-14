package controller;

import javafx.scene.input.KeyEvent;
import controller.modei.Rеgex;
import model.Regex;
import model.Utils;
import org.fxmisc.richtext.StyleClassedTextField;

public class Controller {
    public StyleClassedTextField tf11;
    public StyleClassedTextField tf12;
    public StyleClassedTextField tf13;
    public StyleClassedTextField tf21;
    public StyleClassedTextField tf22;
    public StyleClassedTextField tf31;
    public StyleClassedTextField tf32;
    public StyleClassedTextField tf33;
    public StyleClassedTextField tf41;
    public StyleClassedTextField tf51;

    public void tf11Typed(KeyEvent keyEvent) {
        /*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("a") && !key.equals("b") && !key.equals("c") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf11.getText();
        tf11.setStyleClass(0, tf11.getLength(), "red");

        for (Integer i : Regex.t11(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf11.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf12Typed(KeyEvent keyEvent) {
        /*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("1") && !key.equals("0") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf12.getText();
        tf12.setStyleClass(0, tf12.getLength(), "red");
        for (Integer i : Regex.t12(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf12.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf13Typed(KeyEvent keyEvent) {
        /*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("1") && !key.equals("0") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf13.getText();
        tf13.setStyleClass(0, tf13.getLength(), "red");
        for (Integer i : Regex.t13(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf13.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf21Typed(KeyEvent keyEvent) {
        /*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("1") && !key.equals("0") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf21.getText();
        tf21.setStyleClass(0, tf21.getLength(), "red");
        for (Integer i : Regex.t21(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf21.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf22Typed(KeyEvent keyEvent) {
        /*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("1") && !key.equals("0") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf22.getText();
        tf22.setStyleClass(0, tf22.getLength(), "red");
        for (Integer i : Regex.t22(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf22.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf31Typed(KeyEvent keyEvent) {
        /*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("1") && !key.equals("0") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf31.getText();
        tf31.setStyleClass(0, tf31.getLength(), "red");
        for (Integer i : Regex.t31(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf31.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf32Typed(KeyEvent keyEvent) {
/*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("1") && !key.equals("0") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf32.getText();
        tf32.setStyleClass(0, tf32.getLength(), "red");
        for (Integer i : Regex.t32(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf32.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf33Typed(KeyEvent keyEvent) {
        /*String key = keyEvent.getCharacter().toLowerCase(Locale.ROOT);
        if (!key.equals("1") && !key.equals("0") && !key.equals(" ")) {
            tf11.deletePreviousChar();
        } else {*/
        String line = tf33.getText();
        tf33.setStyleClass(0, tf33.getLength(), "red");
        for (Integer i : Rеgex.t33(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf33.setStyleClass(start, end, "green");
        }
        //}
    }

    public void tf41Typed(KeyEvent keyEvent) {
        String line = tf41.getText();
        tf41.setStyleClass(0, tf41.getLength(), "red");
        for (Integer i : Regex.t41(line)) {
            int start = Utils.getStartIndex(i, line);
            int end = line.length();
            if (start + 1 < line.length() - 1)
                end = line.indexOf(" ", start + 1);
            if (end == -1)
                end = line.length();

            tf41.setStyleClass(start, end, "green");
        }
    }

    public void tf51Typed(KeyEvent keyEvent) {
        String line = tf51.getText();
        tf51.setStyleClass(0, tf51.getLength(), "red");
        if (Regex.t51(line))
            tf51.setStyleClass(0, line.length(), "green");
    }
}
