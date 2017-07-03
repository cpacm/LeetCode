import java.util.Arrays;

/**
 * Created by cpacm on 2017/7/3.
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(4, 5));
    }

    public static int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (m == 1 || n == 1) return 1;
        int[][] a = new int[m][n];
        Arrays.fill(a[0], 1);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = a[i - 1][j] + a[i][j - 1];
                }
            }
        }
        return a[m - 1][n - 1];
    }
}
