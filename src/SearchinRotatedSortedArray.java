/**
 * Created by cpacm on 2017/5/19.
 */
public class SearchinRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{6, 7, 8, 0, 1, 2, 3, 4, 5}, 4));
    }

    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
