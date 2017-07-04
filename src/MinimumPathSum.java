/**
 * Created by cpacm on 2017/7/4.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        int a[][] = new int[m][n];
        a[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            a[i][0] = a[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            a[0][i] = a[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[i][j] = Math.min(a[i - 1][j], a[i][j - 1]) + grid[i][j];
            }
        }
        return a[m - 1][n - 1];
    }
}
