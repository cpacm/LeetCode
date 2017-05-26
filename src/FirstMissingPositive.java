/**
 * Created by cpacm on 2017/5/26.
 */
public class FirstMissingPositive {

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{6, -2, -3, 2, 6, 3, 1, 7, 12, 6, 7, 8, 9, 0, 0, 0, 8, 5, 4, 3, 2, 2, 7, 9, 10, 10, 11,10,-2-2,13}));
    }

    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int value = nums[i];
            if (value == i + 1 || value <= 0 || value >= nums.length) {
                i++;
            } else {
                int temp = nums[value - 1];
                if (temp == nums[i]) {
                    i++;
                    continue;
                }
                nums[value - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != k + 1) {
                return k + 1;
            }
        }
        return nums.length + 1;
    }
}
