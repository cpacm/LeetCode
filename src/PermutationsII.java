import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/6/6.
 */
public class PermutationsII {

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 2, 3, 4, 6, 8, 998, 18}));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i : nums) {
            List<List<Integer>> temp = new ArrayList<>(result);
            result.clear();
            if (temp.size() == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                result.add(list);
                continue;
            }
            for (List<Integer> list : temp) {
                for (int k = 0; k <= list.size(); k++) {
                    if (k > 0 && list.get(k - 1) == i) {
                        continue;
                    }
                    if (k == list.size()) {
                        list.add(i);
                        result.add(list);
                        break;
                    }
                    List<Integer> ll = new ArrayList<>(list);
                    ll.add(k, i);
                    result.add(ll);
                }
            }
        }
        return result;
    }
}
