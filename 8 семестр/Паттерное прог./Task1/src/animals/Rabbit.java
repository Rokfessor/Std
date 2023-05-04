package animals;

public class Rabbit extends Animal{
    public Rabbit(String name, String emoji, int speed) {
        super(name, emoji, speed);
    }

    @Override
    public void run(int time) {
        this.distance = speed * time + Math.random() % 5;
    }
}
