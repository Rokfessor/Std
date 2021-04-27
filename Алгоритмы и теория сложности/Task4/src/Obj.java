public class Obj {
    public int st, v;
    public Obj(int v, int st){
        this.st = st;
        this.v = v;
    }

    @Override
    public String toString() {
        return "Вес-" + v + ":Стоимость-" + st;
    }
}
