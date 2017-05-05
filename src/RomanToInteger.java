import java.util.HashMap;

/**
 * Created by cpacm on 2017/4/27.
 */
public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("MMMCMXCIX"));
    }

    public static int romanToInt(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'M') {
                value += 1000;
            }
            if (s.charAt(i) == 'D') {
                value += 500;
            }
            if (s.charAt(i) == 'C') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'D')) {
                    value -= 100;
                } else {
                    value += 100;
                }
            }
            if (s.charAt(i) == 'L') {
                value += 50;
            }
            if (s.charAt(i) == 'X') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                    value -= 10;
                } else {
                    value += 10;
                }
            }
            if (s.charAt(i) == 'V') {
                value += 5;
            }
            if (s.charAt(i) == 'I') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'X' || s.charAt(i + 1) == 'V')) {
                    value -= 1;
                } else {
                    value += 1;
                }
            }

        }
        return value;
    }
}
