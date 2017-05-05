import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by cpacm on 2017/5/4.
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(4));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> temp = new ArrayList<>();
        generateP("",n,n,temp);
        return temp;
    }

    public static void generateP(String str, int left, int right, List<String> list) {
        if (left > right) {
            return;
        }
        if (left > 0) {
            generateP(str + "(", left - 1, right, list);
        }
        if (right > 0) {
            generateP(str + ")", left, right - 1, list);
        }
        if (left == 0 && right == 0) {
            list.add(str);
        }
    }
}
