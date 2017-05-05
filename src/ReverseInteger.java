/**
 * Created by cpacm on 2017/1/11.
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {
        boolean prefix = x < 0;
        int ax = Math.abs(x);
        StringBuffer sb = new StringBuffer();
        sb.append(ax);
        String s = sb.reverse().toString();
        int value = 0;
        try {
            value = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            value = 0;
        }
        if (prefix) {
            return -value;
        }
        return value;
    }
}
