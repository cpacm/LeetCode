/**
 * Created by cpacm on 2017/5/12.
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, -2147483648));
    }

    public static int divide(int dividend, int divisor) {
        int res = 0;
        boolean flag = true;
        if (dividend < 0) {
            flag = !flag;
            if (dividend == Integer.MIN_VALUE)
                dividend = Integer.MAX_VALUE;
            else dividend = -dividend;
        }
        if (divisor < 0) {
            flag = !flag;
            if (divisor == Integer.MIN_VALUE)
                divisor = Integer.MAX_VALUE;
            else divisor = -divisor;
        }
        while (dividend >= divisor) {
            dividend -= divisor;
            res += 1;
        }
        return flag ? res : -res;
    }
}
