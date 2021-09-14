package Lesson1.Task1;

public enum CardinalPoints {
    North,
    East,
    South,
    West;

    public CardinalPoints turn(Parties side) {
        int current = side.getValue() + this.ordinal();
        if (current > 3)
            current -= 4;
        if (current < 0)
            current += 4;

        return values()[current];
    }
}
