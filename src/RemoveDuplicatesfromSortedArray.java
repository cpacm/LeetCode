/**
 * Created by cpacm on 2017/5/11.
 */
public class RemoveDuplicatesfromSortedArray {

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10}));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 1;
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != temp) {
                temp = nums[i];
                nums[res] = nums[i];
                res++;

            }
        }
        return res;
    }
}
