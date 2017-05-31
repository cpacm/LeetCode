import java.util.*;

/**
 * Created by cpacm on 2017/5/27.
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int trap(int[] height) {
        int secHight = 0;
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                secHight = Math.max(height[left], secHight);
                area += secHight - height[left];
                left++;
            } else {
                secHight = Math.max(height[right], secHight);
                area += secHight - height[right];
                right--;
            }
        }
        return area;
    }
}
