import java.util.*;

/**
 * Created by cpacm on 2017/5/25.
 */
public class CombinationSumII {

    static List<List<Integer>> cs = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < candidates.length; i++) {
            map.put(candidates[i], map.getOrDefault(candidates[i], 0) + 1);
        }
        sum(target, new ArrayList<>(), map);
        return cs;
    }

    public static void sum(int target, List<Integer> list, Map<Integer, Integer> map) {
        Set<Integer> keys = map.keySet();
        Map<Integer, Integer> tempMap = new HashMap<>(map);
        for (int key : keys) {
            List<Integer> temp = new ArrayList<>(list);
            int value = tempMap.get(key);
            if (target == key) {
                temp.add(key);
                cs.add(temp);
                tempMap.remove(key);
                continue;
            } else if (key > target) {
                tempMap.remove(key);
                sum(target, temp, tempMap);
                break;
            } else if (target > key) {
                temp.add(key);
                if (value > 1) {
                    tempMap.put(key, tempMap.get(key) - 1);
                } else {
                    tempMap.remove(key);
                }
                sum(target - key, temp, tempMap);
                tempMap.remove(key);
            }
        }
    }
}
