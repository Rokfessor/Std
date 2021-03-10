package Task1;

public class Counter {
    public static class Coun{
        public static long even = 0;
        public static long odd = 0;

        public static void reset(){
            even = 0;
            odd = 0;
        }
    }
    @Override
    public String toString() {
        return "Counter{" +
                "even=" + Coun.even +
                ", odd=" + Coun.odd +
                '}';
    }
}
