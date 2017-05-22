import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by cpacm on 2017/5/22.
 */
public class ValidSudoku {

    public static void main(String[] args) {
        System.out.println(isValidSudoku(new char[][]{
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'},
                {'1', '2', '3', '4', '.', '.', '.', '.', '.'}
        }));
    }

    public static boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        return false;
                }
            }
        }
        return true;
    }
}
