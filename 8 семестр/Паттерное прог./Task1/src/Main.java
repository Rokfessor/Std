import animals.Animal;
import animals.Cheetah;
import animals.Rabbit;
import animals.Turtle;

public class Main {

    final static int DISTANCE = 100; //meters

    public static void main(String[] args) throws InterruptedException {
        Animal[] animals = new Animal[]{
                new Cheetah("Гепард", "\uD83D\uDC06", 25),
                new Rabbit("Кролик", "\uD83D\uDC07", 11),
                new Turtle("Черепаха", "\uD83D\uDC22", 2)
        };

        System.out.println("===Забег животных===");
        for (int i = 0; i < animals.length; ++i)
            System.out.println((i + 1) + ") " + animals[i].getName() + " " + animals[i].getEmoji());

        System.out.println();

        Animal leader = animals[0];
        double leaderDistance = 0;
        int seconds = 0;
        while (true) {
            for (Animal animal : animals) {
                animal.run(seconds);
                if (animal.getDistance() > leaderDistance) {
                    leaderDistance = animal.getDistance();
                    leader = animal;
                }
                leaderDistance = Math.max(animal.getDistance(), leaderDistance);
            }
            printStatus(DISTANCE, animals);
            Thread.sleep(1000);
            if (leaderDistance >= DISTANCE) {
                System.out.println("\n\n===Победитель===\n   " + leader.getName() + " " + leader.getEmoji());
                break;
            }
            ++seconds;
        }
        System.out.println();
    }

    public static void printStatus(double totalDistance, Animal[] animals) {
        StringBuilder sb = new StringBuilder("\r[");
        for (int i = 0; i < 50; ++i) {
            boolean flag = true;
            for (Animal animal : animals) {
                int d = (int) (animal.getDistance() * 100 / totalDistance) / 2;
                if (d == i && flag) {
                    sb.append(animal.getEmoji());
                    flag = false;
                }
            }

            if (flag)
                sb.append('.');
        }
        sb.append("\uD83C\uDFC1");

        System.out.print(sb);
    }
}