import java.util.Arrays;

/**
 * Created by cpacm on 2017/6/26.
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int maxSubArray(int[] nums) {
        int[] total = new int[nums.length];
        total[0] = nums[0];
        int totalMax = total[0];
        for (int i = 1; i < nums.length; i++) {
            total[i] = Math.max(nums[i], nums[i] + total[i - 1]);
            totalMax = Math.max(totalMax, total[i]);
        }
        return totalMax;

    }
}
