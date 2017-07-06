/**
 * Created by cpacm on 2017/7/5.
 */
public class AddBinary {

    public static void main(String[] args) {
        System.out.println(addBinary("1111", "1"));
    }

    public static String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        if (m == 0) return b;
        if (n == 0) return a;
        m = m - 1;
        n = n - 1;
        String k = "";
        int temp = 0;
        while (m >= 0 || n >= 0) {
            int aa = m >= 0 ? a.charAt(m) - '0' : 0;
            int bb = n >= 0 ? b.charAt(n) - '0' : 0;
            int r = aa + bb + temp;
            if (r >= 2) {
                r = r - 2;
                temp = 1;
            } else {
                temp = 0;
            }
            k = (char) (r + '0') + k;
            m--;
            n--;
        }
        if (temp == 1) {
            k = (char) (temp + '0') + k;
        }
        return k;
    }

}
