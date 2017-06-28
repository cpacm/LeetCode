import java.util.ArrayList;

/**
 * Created by cpacm on 2017/6/28.
 */
public class PermutationSequence {

    public static void main(String[] args) {
        System.out.println(getPermutation(4, 5));
    }

    public static String getPermutation(int n, int k) {
        int data[] = new int[n + 1];
        data[0] = data[1] = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            data[i] = data[i - 1] * (i);
            list.add(i);
        }
        String result = "";
        k--;
        for (int i = n - 1; i >= 0; i--) {
            int cur = k / data[i];
            result += list.remove(cur);
            k = k % data[i];
        }
        return result;
    }
}
