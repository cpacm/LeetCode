/**
 * Created by cpacm on 2017/4/27.
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(3999));
    }

    static String[] M = {"", "M", "MM", "MMM"};
    static String[] D = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    static String[] X = {"", "X", "XX", "XXX", "XL", "X", "LX", "LXX", "LXXX", "XC"};
    static String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public static String intToRoman(int num) {
        String str = M[num / 1000] + D[num % 1000 / 100] + X[num % 100 / 10] + I[num % 10];
        return str;
    }
}
