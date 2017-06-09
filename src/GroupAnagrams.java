import java.util.*;

/**
 * Created by cpacm on 2017/6/9.
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ll = new ArrayList<>();
        if (strs.length <= 0) return ll;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String sstr = sortStr(str);
            if (map.containsKey(sstr)) {
                map.get(sstr).add(str);
            } else {
                List<String> l = new ArrayList<>();
                l.add(str);
                map.put(sstr, l);
            }
        }
        return new ArrayList<>(map.values());
    }

    public static String sortStr(String str) {
        char[] cc = str.toCharArray();
        Arrays.sort(cc);
        return String.valueOf(cc);
    }
}
