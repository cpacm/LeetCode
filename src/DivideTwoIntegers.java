/**
 * Created by cpacm on 2017/5/12.
 * 63/4 = 15;
 * 63 = 32+16+8+4+3 = 4*2^3+4*2^2+4*2^1+4*2^0+3=(8+4+2+1)*4+3 = 63
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, -1));
    }

    public static int divide(int dividend, int divisor) {
        int sign = 1;
        if (dividend < 0) sign = -sign;
        if (divisor < 0) sign = -sign;
        long temp = Math.abs((long) dividend);
        long temp2 = Math.abs((long) divisor);
        long c = 1;
        while (temp > temp2) {
            temp2 = temp2 << 1;
            c = c << 1;
        }
        long res = 0;
        while (temp >= Math.abs((long) divisor)) {
            while (temp >= temp2) {
                temp -= temp2;
                res += c;
            }
            temp2 = temp2 >> 1;
            c = c >> 1;
        }
        if (sign > 0) {
            if (res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) res;
        } else return -(int) res;
    }
}
