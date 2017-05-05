/**
 * Created by cpacm on 2017/4/27.
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"MMMCMXCIX","MMM"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String commonStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int j = 0;
            while (j < str.length() && j < commonStr.length()) {
                if (str.charAt(j) == commonStr.charAt(j)) {
                    j++;
                }else{
                    break;
                }
            }
            commonStr = commonStr.substring(0, j);
        }
        return commonStr;
    }
}
