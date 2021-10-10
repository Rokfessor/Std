import lombok.Getter;

public class Parent {
    @Getter
    Integer parent;
    @Getter
    Integer child;

    public Parent(Integer parent, Integer child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public String toString() {
        return parent + ":" + child;
    }
}
