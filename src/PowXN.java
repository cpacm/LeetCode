/**
 * Created by cpacm on 2017/6/12.
 */
public class PowXN {

    public static void main(String[] args) {
        System.out.println(myPow(10, -3));
    }

    public static double myPow(double x, int n) {
        double temp = x;
        if (n == 0) return 1;
        temp = myPow(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        } else {
            if (n > 0)
                return x * temp * temp;
            else
                return (temp * temp) / x;
        }
        //return Math.pow(x, n);
    }
}
