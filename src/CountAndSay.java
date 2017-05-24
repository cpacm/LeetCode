/**
 * Created by cpacm on 2017/5/24.
 */
public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(countAndSay(7));
    }

    public static String countAndSay(int n) {
        String result = "1";
        if (n <= 1) {
            return result;
        }
        for (int i = 1; i < n; i++) {
            StringBuilder res = new StringBuilder();
            int count = 1;
            char c = result.charAt(0);
            for (int j = 1; j < result.length(); j++) {
                if (c == result.charAt(j)) {
                    count++;
                } else {
                    res.append(count).append(c);
                    count = 1;
                    c = result.charAt(j);
                }
            }
            res.append(count).append(c);
            result = res.toString();
        }
        return result;
    }
}
