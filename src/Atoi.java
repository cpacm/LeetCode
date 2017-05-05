/**
 * Created by cpacm on 2017/1/11.
 */
public class Atoi {

    public static void main(String[] args) {
        System.out.println(myAtoi("21473648"));
    }

    public static int myAtoi(String str) {
        int value = 0;
        int prefix = 1;
        boolean isFirst = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ' && isFirst) {
                continue;
            }
            if (isFirst && (c == '+' || c == '-')) {
                if (c == '-') {
                    prefix = -1;
                }
                isFirst = false;
                continue;
            }
            if (c >= '0' && c <= '9') {
                isFirst = false;
                int newValue = value * 10 + c - '0';
                if (newValue / 10 != value) {
                    if (prefix < 0) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
                value = value * 10 + c - '0';
            } else {
                return value * prefix;
            }

        }
        return value * prefix;
    }

}
