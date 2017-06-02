import com.sun.istack.internal.NotNull;

/**
 * Created by cpacm on 2017/6/2.
 */
public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("aaabababaaabaababbbaaaabbbbbbabbbbabbbabbaabbababab", "*ab***ba**b*b*aaab*b"));
    }

    public static boolean isMatch(String s, String p) {
        int m = 0, n = 0, index = 0, star = -1;
        while (m < s.length()) {
            if (n < p.length() && (s.charAt(m) == p.charAt(n) || p.charAt(n) == '?')) {
                m++;
                n++;
            } else if (n < p.length() && p.charAt(n) == '*') {
                index = m;
                star = n;
                n++;
            } else if (star != -1) {
                n = star + 1;
                index++;
                m = index;
            } else return false;
        }
        while (n < p.length() && p.charAt(n) == '*')
            n++;
        return n == p.length();
    }

    /**
     * 递归算法，但TLE了
     */
    public static boolean match(String s, String p, int m, int n) {
        if (n >= p.length() && m < s.length()) return false;
        if (n >= p.length() && m >= s.length()) return true;
        if (m >= s.length()) {
            char c = p.charAt(n);
            return c == '*' && match(s, p, m, n + 1);
        }
        char c1 = s.charAt(m);
        char c2 = p.charAt(n);
        if (c2 == '?') {
            return match(s, p, m + 1, n + 1);
        } else if (c2 == '*') {
            return match(s, p, m + 1, n + 1) || match(s, p, m, n + 1) || match(s, p, m + 1, n);
        } else if (c1 == c2) {
            return match(s, p, m + 1, n + 1);
        } else {
            return false;
        }
    }
}
