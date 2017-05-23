import java.util.HashSet;
import java.util.Set;

/**
 * Created by cpacm on 2017/5/23.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void solveSudoku(char[][] board) {
        sudoku(board, 0);
    }

    public static boolean sudoku(char[][] board, int index) {
        if (index >= 81) {
            return true;
        }
        int i = index / 9;
        int j = index % 9;
        char c = board[i][j];
        if (c != '.') {
            return sudoku(board, index + 1);
        } else {
            for (int k = 1; k <= 9; k++) {
                char cc = (char) (k + '0');
                board[i][j] = cc;
                if (isValidSudoku(board)) {
                    if (sudoku(board, index + 1)) {
                        return true;
                    }
                }
                board[i][j] = '.';
            }
        }
        return false;
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
