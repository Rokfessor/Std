package Task6;

public class Book {
    public String name;
    public boolean mayTakeHome;

    public Book(String name, boolean mayTakeHome) {
        this.name = name;
        this.mayTakeHome = mayTakeHome;
    }

    @Override
    public String toString() {
        return name;
    }
}
