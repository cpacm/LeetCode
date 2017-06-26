import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/6/26.
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        System.out.println(spiralOrder(
                new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> s = new ArrayList<>();
        int m = matrix.length;
        if(m == 0) return s;
        int n = matrix[0].length;
        int startM = 0, startN = 0;
        while (m > 0 && n > 0) {
            spiral1(startM, startN, m, n, matrix, s);
            startM++;
            m--;
            if (m <= 0 || n <= 0) return s;
            spiral2(startM, startN, m, n, matrix, s);
            n--;
            if (m <= 0 || n <= 0) return s;
            spiral3(startM, startN, m, n, matrix, s);
            m--;
            if (m <= 0 || n <= 0) return s;
            spiral4(startM, startN, m, n, matrix, s);
            startN++;
            n--;
        }
        return s;
    }

    public static void spiral1(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = startN; k < n + startN; k++) {
            s.add(matrix[startM][k]);
        }
    }

    public static void spiral2(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = startM; k < m + startM; k++) {
            s.add(matrix[k][n + startN - 1]);
        }
    }

    public static void spiral3(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = startN + n - 1; k >= startN; k--) {
            s.add(matrix[startM + m - 1][k]);
        }
    }

    public static void spiral4(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = m + startM - 1; k >= startM; k--) {
            s.add(matrix[k][startN]);
        }
    }
}
