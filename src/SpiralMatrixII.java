import java.util.List;

/**
 * Created by cpacm on 2017/6/28.
 */
public class SpiralMatrixII {

    public static void main(String[] args) {
        System.out.println(generateMatrix(3));
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int k = 1;
        int startM = 0, startN = 0;
        int m = n;
        while (m > 0 && n > 0) {
            k = spiral1(startM, startN, m, n, matrix, k);
            startM++;
            m--;
            if (m <= 0 || n <= 0) return matrix;
            k = spiral2(startM, startN, m, n, matrix, k);
            n--;
            if (m <= 0 || n <= 0) return matrix;
            k = spiral3(startM, startN, m, n, matrix, k);
            m--;
            if (m <= 0 || n <= 0) return matrix;
            k = spiral4(startM, startN, m, n, matrix, k);
            startN++;
            n--;
        }
        return matrix;
    }

    public static int spiral1(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = startN; k < n + startN; k++) {
            matrix[startM][k] = s++;
        }
        return s;
    }

    public static int spiral2(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = startM; k < m + startM; k++) {
            matrix[k][n + startN - 1] = s++;
        }
        return s;
    }

    public static int spiral3(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = startN + n - 1; k >= startN; k--) {
            matrix[startM + m - 1][k] = s++;
        }
        return s;
    }

    public static int spiral4(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = m + startM - 1; k >= startM; k--) {
            matrix[k][startN] = s++;
        }
        return s;
    }
}
