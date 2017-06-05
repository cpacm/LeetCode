/**
 * Created by cpacm on 2017/6/5.
 */
public class JumpGameII {

    public static void main(String[] args) {
        System.out.println(jump(new int[]{1, 2, 3, 4, 6, 8, 1, 998, 8, 8}));
    }

    public static int jump(int[] nums) {
        int res = 0, i = 0, cur = 0, n = nums.length;
        while (cur < n - 1) {
            int pre = cur;
            while (i <= pre) {
                cur = Math.max(cur, i + nums[i]);
                ++i;
            }
            ++res;
            if (pre == cur) return -1;
        }
        return res;
    }
}
