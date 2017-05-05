/**
 * Created by cpacm on 2017/1/10.
 */
public class ZigZag {

    public static void main(String[] args) {
        System.out.println(convert("", 1));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int len = s.length();
        int span = numRows * 2 - 2;
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < numRows; i++) {
            int j = i;
            while (j < len) {
                sb.append(s.charAt(j));
                if (j % span == 0 || j % span == (numRows - 1)) {
                    j += span;
                } else {
                    int k = j + (span - i * 2);
                    if (k < len) {
                        sb.append(s.charAt(k));
                    }
                    j += span;
                }
            }
        }
        return sb.toString();
    }
}
