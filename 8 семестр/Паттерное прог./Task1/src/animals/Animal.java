package animals;

public abstract class Animal {
    protected int speed;
    protected String name;
    protected String emoji;

    protected double distance;

    public Animal(String name, String emoji, int speed) {
        this.name = name;
        this.emoji = emoji;
        this.speed = speed;
        this.distance = 0;
    }

    public abstract void run(int time);

    public String getName() {
        return name;
    }

    public String getEmoji() {
        return emoji;
    }

    public double getDistance() {
        return distance;
    }
}
