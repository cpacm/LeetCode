/**
 * Created by cpacm on 2017/6/1.
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(multiply("9133", "0"));
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num1.length(); i++) {
            int k = num1.charAt(i) - '0';
            if (result.length() != 0) {
                result.append("0");
            }
            result = add(result, singleMul(k, num2));
        }
        return result.toString();
    }

    public static StringBuilder add(StringBuilder s1, StringBuilder s2) {
        int s = 0;
        StringBuilder sb = new StringBuilder();
        int len1 = s1.length() - 1;
        int len2 = s2.length() - 1;
        while (len1 >= 0 || len2 >= 0) {
            int m1 = len1 >= 0 ? s1.charAt(len1) - '0' : 0;
            int m2 = len2 >= 0 ? s2.charAt(len2) - '0' : 0;
            sb.insert(0, (m1 + m2 + s) % 10);
            s = (m1 + m2 + s) / 10;
            len1--;
            len2--;
        }
        if (s != 0) {
            sb.insert(0, s);
        }
        return sb;
    }

    public static StringBuilder singleMul(int num, String num2) {
        StringBuilder sb = new StringBuilder();
        int s = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            int k = num2.charAt(i) - '0';
            sb.insert(0, (num * k + s) % 10);
            s = (num * k + s) / 10;
        }
        if (s != 0) {
            sb.insert(0, s);
        }
        return sb;
    }
}
