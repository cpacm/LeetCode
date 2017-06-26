import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cpacm on 2017/6/24.
 */
public class NQueens {

    private static List<List<String>> resultList = new ArrayList<>();
    private static int[] positions;
    private static char[] tags;

    public static void main(String[] args) {
        System.out.println(solveNQueens(8));
    }

    public static List<List<String>> solveNQueens(int n) {
        positions = new int[n];
        tags = new char[n];
        Arrays.fill(positions, -1);
        Arrays.fill(tags, '.');
        dp(0, n);
        return resultList;
    }

    public static void dp(int n, int max) {
        if (n == max) {
            List<String> qLine = new ArrayList<>();
            for (int i = 0; i < positions.length; i++) {
                tags[positions[i]] = 'Q';
                String q = new String(tags);
                qLine.add(q);
                tags[positions[i]] = '.';
            }
            resultList.add(qLine);
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
