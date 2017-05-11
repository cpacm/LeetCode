/**
 * Created by cpacm on 2017/5/11.
 */
public class RemoveElement {

    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{1, 1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10}, 4));
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res] = nums[i];
                res++;
            }
        }
        return res;
    }
}
