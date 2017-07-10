/**
 * Created by cpacm on 2017/7/10.
 */
public class SqrtX {

    public static void main(String[] args) {
        System.out.println(mySqrt(64));
    }

    public static int mySqrt(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last) {
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }
}
