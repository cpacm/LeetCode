/**
 * Created by cpacm on 2017/6/28.
 */
public class LengthofLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("aaaaa a"));
    }

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int total = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (total == 0) continue;
                else return total;
            }
            total++;
        }
        return total;
    }
}
