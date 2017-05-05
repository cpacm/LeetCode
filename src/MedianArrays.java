/**
 * Created by cpacm on 2017/1/6.
 */
public class MedianArrays {

    public static void main(String[] args) {
        int num1[] = new int[]{1, 2};
        int num2[] = new int[]{3, 4};
        double m = findMedianSortedArrays(num1, num2);
        System.out.println(m);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        double sum = 0;
        boolean flag = (m + n) % 2 == 1;
        int median = (m + n) / 2;
        int i = 0, j = 0;
        int x1 = 0;
        for (int k = 0; k <= n + m; k++) {
            if (i < m && j < n && nums1[i] <= nums2[j]) {
                x1 = nums1[i];
                i++;
            } else if (i < m && j < n && nums1[i] > nums2[j]) {
                x1 = nums2[j];
                j++;
            } else if (i >= m) {
                x1 = nums2[j];
                j++;
            } else if (j >= n) {
                x1 = nums1[i];
                i++;
            }
            if (flag) {
                if (k == median) {
                    sum = x1 * 1.0;
                    break;
                }
            } else {
                if (k == median - 1) {
                    sum += x1;
                } else if (k == median) {
                    sum += x1;
                    sum = sum / 2.0;
                    break;
                }
            }
        }
        return sum;
    }
}
