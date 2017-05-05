import java.util.Stack;

/**
 * Created by cpacm on 2017/5/4.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid(("([)]")));
    }

    public static boolean isValid(String s) {
        Stack<Character> p = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                p.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (p.size() == 0) {
                    return false;
                }
                if (c == ')' && p.pop() != '(') {
                    return false;
                } else if (c == '}' && p.pop() != '{') {
                    return false;
                } else if (c == ']' && p.pop() != '[') {
                    return false;
                }
            }
        }
        if (p.size() != 0) return false;
        return true;
    }
}
