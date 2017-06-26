/**
 * Created by cpacm on 2017/6/26.
 */
public class JumpGame {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{0, 1}));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;
        if (nums[0] == 0) return false;
        int[] maxs = new int[nums.length];
        maxs[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (maxs[i - 1] <= 1) {
                    return i >= nums.length - 1;
                }
                maxs[i] = maxs[i - 1] - 1;
            }
            maxs[i] = Math.max(maxs[i - 1] - 1, nums[i]);
        }
        return true;
    }
}
