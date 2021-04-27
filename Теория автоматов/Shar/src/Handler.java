public class Handler {
    private boolean x1 = false, x2 = false, x3 = false;

    public Res calcStep(boolean left) {
        boolean r1 = false, r2 = true, center = false;
        if (left) {
            if (x1) {
                center = true;
                r1 = true;
            }
            x1 = !x1;
        } else {
            center = true;
            if (x3) {
                center = false;
                r1 = true;
            }
            x3 = !x3;
        }

        if (center){
            r2 = x2;
            x2 = !x2;
        } else {
            r2 = left;
        }
        return new Res(r1, r2);
    }
}
