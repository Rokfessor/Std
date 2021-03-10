package Lesson1.Task1;

public class Main {
    public static void main(String[] args) {
        CardinalPoints points = CardinalPoints.South;
        Parties side = Parties.Back;
        System.err.print("Turning " + side + " from the " + points + " is the ");
        points = points.turn(side);
        System.err.println(points);
    }
}
