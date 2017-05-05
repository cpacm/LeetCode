import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpacm on 2017/1/6.
 */
public class LongestSubstring {

    public static void main(String[] args) {
        int m = lengthOfLongestSubstring("fasdcfdefrs");
        System.out.println(m);
    }

    public static int lengthOfLongestSubstring(String s) {
        int curIndex = 0;
        int max = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (charMap.containsKey(s.charAt(i))) {
                curIndex = Math.max(charMap.get(s.charAt(i)) + 1, curIndex);
            }
            charMap.put(s.charAt(i), i);
            max = Math.max(max, i - curIndex + 1);
        }
        return max;
    }
}
