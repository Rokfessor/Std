public class Utils {
    public static String performSDNF(String s){
        System.err.print(s + " = ");
        StringBuilder sbSDNFU = new StringBuilder();
        boolean flag = false;
        sbSDNFU.append("(");
        for (int j = 0; j < s.length(); j++) {
            if (s.toCharArray()[j] == '-') {
                continue;
            }

            if (j != 0 && flag)
                sbSDNFU.append(" ∧ ");

            flag = true;

            if (s.toCharArray()[j] == '0')
                sbSDNFU.append("¬");

            sbSDNFU.append((char)( j + 65));
        }
        sbSDNFU.append(")");
        System.err.println(sbSDNFU.toString());
        return sbSDNFU.toString();
    }
}
