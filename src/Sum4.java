import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cpacm on 2017/5/3.
 */
public class Sum4 {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                int t = target - nums[i];
                threeSum(nums, t, result, i + 1);
            }
        }
        return result;
    }

    public static void threeSum(int[] nums, int target, List<List<Integer>> result, int k) {
        for (int i = k; i < nums.length - 2; i++) {
            if (i == k || (i > k && nums[i] != nums[i - 1])) {
                int start = i + 1, end = nums.length - 1, t = target - nums[i];
                while (start < end) {
                    if (nums[start] + nums[end] == t) {
                        result.add(Arrays.asList(nums[k-1], nums[start], nums[end], nums[i]));
                        while (start < end && nums[end] == nums[end - 1]) end--;
                        while (start < end && nums[start + 1] == nums[start]) start++;
                        end--;
                        start++;
                    } else if (nums[start] + nums[end] < t) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
    }
}
