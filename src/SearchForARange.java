/**
 * Created by cpacm on 2017/5/19.
 */
public class SearchForARange {

    public static void main(String[] args) {
        System.out.println(searchRange(new int[]{9, 9}, 9));
    }

    public static int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        int st = 0, len = nums.length - 1;
        while (st <= len) {
            int mid = (len + st + 1) / 2;
            if (nums[mid] == target) {
                start = binarySearch(nums, st, mid - 1, target, true);
                end = binarySearch(nums, mid + 1, len, target, false);
                if (start == -1) start = mid;
                if (end == -1) end = mid;
                break;
            } else if (nums[mid] > target) {
                len = mid - 1;
            } else if (nums[mid] < target) {
                st = mid + 1;
            }
        }
        System.out.println(start + " " + end);
        return new int[]{start, end};
    }

    public static int binarySearch(int[] nums, int start, int end, int target, boolean left) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end + 1) / 2;
        int index = -1;
        if (nums[mid] == target) {
            if (left) {
                index = binarySearch(nums, start, mid - 1, target, left);
            } else {
                index = binarySearch(nums, mid + 1, end, target, left);
            }
            if (index == -1) {
                return mid;
            }
            return index;
        } else if (nums[mid] > target) {
            return binarySearch(nums, start, mid - 1, target, left);
        } else if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, end, target, left);
        } else {
            return index;
        }
    }
}
