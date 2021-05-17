package model.protection;

public class Shield extends Protection {
    public Shield() {
        super(DEFAULT_DEFEND, DEFAULT_INTEGRITY);
    }

    public final boolean blocked() {
        return Math.random() < 0.3;
    }
}
