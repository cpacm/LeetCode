/**
 * Created by cpacm on 2017/7/10.
 */
public class ClimbingStairs {


    public static void main(String[] args) {
        System.out.println(climbStairs(14));
    }

    public static int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2) return n;
        int cs[] = new int[n+1];
        cs[0] = 0;
        cs[1] = 1;
        cs[2] = 2;
        for (int i = 3; i <= n; i++) {
            cs[i] = cs[i - 1] + cs[i - 2];
        }
        return cs[n];
    }
}
