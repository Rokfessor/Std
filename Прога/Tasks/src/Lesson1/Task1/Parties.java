package Lesson1.Task1;

public enum Parties {
    Straight(0),
    Back(2),
    Left(-1),
    Right(1);


    private final int i;

    Parties(int i) {
        this.i = i;
    }

    public int getValue() {
        return i;
    }
}
