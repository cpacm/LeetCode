/**
 * Created by cpacm on 2017/5/22.
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 2, 3, 4, 5}, 2));
    }

    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int index = start;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target > nums[mid]) {
                start = mid + 1;
                index = start;
            } else if (target < nums[mid]) {
                end = mid - 1;
                index = mid;
            } else {
                return mid;
            }
        }
        return index;
    }
}
