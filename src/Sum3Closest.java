import java.util.Arrays;

/**
 * Created by cpacm on 2017/4/28.
 */
public class Sum3Closest {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int distance = Integer.MAX_VALUE;
        int min = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                int start = i + 1, end = nums.length - 1, t = target - nums[i];
                while (start < end) {
                    int value = nums[start] + nums[end];
                    if (value == t) {
                        return target;
                    } else if (value > t) {
                        end--;
                        if (distance > value - t) {
                            distance = value - t;
                            min = nums[i] + value;
                        }
                    } else {
                        start++;
                        if (distance > t - value) {
                            distance = t - value;
                            min = nums[i] + value;
                        }
                    }
                }
            }
        }
        return min;
    }
}
