public class Handler {
    public boolean x1 = false, x2 = false, x3 = false;

    public Resul calcStep(boolean left) {
        boolean r1 = false, r2 = true, center = false;
        if (left) {
            if (x1) {
                center = true;
                r1 = true;
            }
            if (Math.random() < 0.5F)
                x1 = !x1;
        } else {
            center = true;
            if (x3) {
                center = false;
                r1 = true;
            }
            if (Math.random() < 0.5F)
                x3 = !x3;
        }

        if (center){
            r2 = x2;
            if (Math.random() < 0.5F)
                x2 = !x2;
        } else {
            r2 = left;
        }
        return new Resul(r1, r2);
    }

    public void randomize(){
        if (Math.random() < 0.5F)
            x1 = !x1;
        if (Math.random() < 0.5F)
            x2 = !x2;
        if (Math.random() < 0.5F)
            x3 = !x3;
    }
}
