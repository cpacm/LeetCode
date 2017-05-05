/**
 * Created by cpacm on 2017/1/13.
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("", ".*"));
    }

    public static boolean isMatch(String s, String p) {
        return match(s, p, 0, 0);
    }

    public static boolean match(String s, String p, int sIndex, int pIndex) {
        if (sIndex == s.length() && pIndex == p.length()) {
            return true;
        }
        if (pIndex >= p.length()) {
            return false;
        }
        int pPlus = 0;
        char pChar = p.charAt(pIndex);
        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            pPlus = 1;
        }
        if (sIndex >= s.length()) {
            if (pPlus == 1) {
                return match(s, p, sIndex, pIndex + 2);
            } else {
                return false;
            }
        }
        char sChar = s.charAt(sIndex);
        if (pPlus == 0) {
            if (sChar == pChar || pChar == '.') {
                return match(s, p, sIndex + 1, pIndex + 1);
            } else {
                return false;
            }
        } else {
            if (sChar == pChar || pChar == '.') {
                if (!match(s, p, sIndex + 1, pIndex + 2)) {
                    if (!match(s, p, sIndex + 1, pIndex)) {
                        return match(s, p, sIndex, pIndex + 2);
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return match(s, p, sIndex, pIndex + 2);
            }
        }
    }
}
