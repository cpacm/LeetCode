import java.util.Arrays;

/**
 * Created by cpacm on 2017/6/9.
 */
public class RotateImage {

    public static void main(String[] args) {
        rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        //System.out.println();
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = temp[n - 1 - j][i];
            }
        }
    }
}
