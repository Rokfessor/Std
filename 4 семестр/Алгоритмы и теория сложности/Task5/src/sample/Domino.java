package sample;

import java.util.Objects;

public class Domino {
    public int left, right;
    public boolean used = false;
    public boolean inverted = false;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Domino)) return false;
        Domino domino = (Domino) o;
        return left == domino.left && right == domino.right && used == domino.used && inverted == domino.inverted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, used, inverted);
    }

    @Override
    public String toString() {
        if (!inverted)
            return "[" + left + "|" + right + "]";
        else
            return "[" + right + "|" + left + "]";
    }
}
