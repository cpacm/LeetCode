/**
 * Created by cpacm on 2017/7/3.
 */
public class UniquePathsII {

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        if (n == 0) return 0;
        int a[][] = new int[m][n];
        if (obstacleGrid[0][0] > 0) {
            a[0][0] = 0;
        } else {
            a[0][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] > 0) {
                a[0][i] = 0;
            } else {
                a[0][i] = a[0][i - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] > 0) {
                a[i][0] = 0;
            } else {
                a[i][0] = a[i - 1][0];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] > 0) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = a[i - 1][j] + a[i][j - 1];
                }
            }
        }
        return a[m - 1][n - 1];

    }
}
