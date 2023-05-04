package animals;

public class Cheetah extends Animal{
    public Cheetah(String name, String emoji, int speed) {
        super(name, emoji, speed);
    }

    @Override
    public void run(int time) {
        this.distance = speed * time + Math.random() % 10;
    }
}
