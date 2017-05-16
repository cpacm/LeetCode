import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cpacm on 2017/5/16.
 */
public class SubstringwithConcatenationofAllWords {

    public static void main(String[] args) {

        System.out.println(findSubstring("aaaaaaaa", new String[]{"aa", "aa", "aa"}));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.containsKey(str) ? map.get(str) + 1 : 1);
        }
        int len = words[0].length();
        for (int i = 0; i <= s.length() - len * words.length; i++) {
            Map<String, Integer> tempMap = new HashMap<>(map);
            int temp = i;
            int count = 0;
            String tempStr = s.substring(temp, temp + len);
            while (tempMap.containsKey(tempStr) && tempMap.get(tempStr) > 0) {
                tempMap.put(tempStr, tempMap.get(tempStr) - 1);
                temp = temp + len;
                count++;
                if (temp + len <= s.length()) {
                    tempStr = s.substring(temp, temp + len);
                } else {
                    break;
                }
            }
            if (count == words.length) {
                res.add(i);
            }
        }
        return res;
    }
}
