public class Utils {
    public static Obj[] performData(String v, String st){
        String[] ves = v.split(" ");
        String[] stoi = st.split(" ");
        Obj[] res = new Obj[ves.length];

        for (int i = 0; i < res.length; i++) {
            res[i] = new Obj(Integer.parseInt(ves[i]), Integer.parseInt(stoi[i]));
        }
        return res;
    }
}
