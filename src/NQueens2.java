import java.util.Arrays;

/**
 * Created by cpacm on 2017/6/26.
 */
public class NQueens2 {
    private static int count = 0;
    private static int[] positions;

    public static void main(String[] args) {
        System.out.println(totalNQueens(8));
    }

    public static int totalNQueens(int n) {
        positions = new int[n];
        Arrays.fill(positions, -1);
        dp(0, n);
        return count;
    }

    public static void dp(int n, int max) {
        if (n == max) {
            count++;
            return;
        }
        int valueX = n;
        int valueY = 0;
        while (valueY < max) {
            if (hasPlaced(n, valueX, valueY)) {
                positions[n] = valueY;
                dp(n + 1, max);
            }
            valueY++;
        }
    }

    public static boolean hasPlaced(int n, int valueX, int valueY) {
        for (int j = 0; j < n; j++) {
            int x = j;
            int y = positions[j];
            if (positions[j] == valueY || Math.abs(valueX - x) == Math.abs(valueY - y)) {
                return false;
            }
        }
        return true;
    }
}
