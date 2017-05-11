/**
 * Created by cpacm on 2017/5/11.
 */
public class Implement_strStr {

    public static void main(String[] args) {
        System.out.println(strStr("qwsderftdhasfjancka", "dhasfj"));
    }

    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
