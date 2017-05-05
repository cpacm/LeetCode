import java.util.*;

/**
 * Created by cpacm on 2017/4/27.
 */
public class Sum3 {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{1, 2, -3, 4, 5, -6, 0, -1, -6, 0}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int start = i + 1, end = nums.length - 1, target = 0 - nums[i];
                while (start < end) {
                    if (nums[start] + nums[end] == target) {
                        result.add(Arrays.asList(nums[start], nums[end], nums[i]));
                        while (start < end && nums[end] == nums[end - 1]) end--;
                        while (start < end && nums[start + 1] == nums[start]) start++;
                        end--;
                        start++;
                    } else if (nums[start] + nums[end] < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }

        return result;
    }
}
