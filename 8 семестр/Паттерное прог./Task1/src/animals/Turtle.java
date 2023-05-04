package animals;

public class Turtle extends Animal{
    public Turtle(String name, String emoji, int speed) {
        super(name, emoji, speed);
    }

    @Override
    public void run(int time) {
        this.distance = speed * time + Math.random() % 2;
    }
}
