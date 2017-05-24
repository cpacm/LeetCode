import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/5/24.
 */
public class CombinationSum {

    static List<List<Integer>> cs = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{7, 2, 6, 3}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        sum(candidates, target, new ArrayList<>(), 0);
        return cs;
    }

    public static void sum(int[] candidates, int target, List<Integer> list, int index) {
        for (int i = index; i < candidates.length; i++) {
            List<Integer> temp = new ArrayList<>(list);
            if (target == candidates[i]) {
                temp.add(candidates[i]);
                cs.add(temp);
                continue;
            } else if (candidates[i] > target) {
                sum(candidates, target, temp, i + 1);
                break;
            } else if (target > candidates[i]) {
                temp.add(candidates[i]);
                sum(candidates, target - candidates[i], temp, i);
            }

        }
    }

}
