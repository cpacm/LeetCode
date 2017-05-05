/**
 * Created by cpacm on 2017/4/27.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{50, 10, 20, 100}));
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            int area = Math.min(height[start], height[end]) * (end - start);
            if (height[start] <= height[end]) {
                start++;
            } else {
                end--;
            }
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
