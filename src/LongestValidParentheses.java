import java.util.*;

/**
 * Created by cpacm on 2017/5/18.
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")((())())()"));
    }

    public static int longestValidParentheses(String s) {
        if (s.isEmpty()) return 0;
        Stack<Integer> ms = new Stack<>();
        int maxlen = 0;
        int last = -1;  // Position of the last unmatched ')'
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') ms.push(i); //
            else if (ms.empty()) last = i; // == ')' and the stack is empty, it means this is a non-matching ')'
            else {
                ms.pop();           // pops the matching '('.
                if (ms.empty()) // If there is nothing left in the stack, it means this ')' matches a '(' after the last unmatched ')'
                    maxlen = Math.max(maxlen, i - last);
                else    // otherwise,
                    maxlen = Math.max(maxlen, i - ms.peek());
            }
        }
        return maxlen;
    }

    /**
     * TLE
     * @param s
     * @return
     */
    public static int longestValidParentheses2(String s) {
        if (s == null || s.length() == 1) return 0;
        List<Character> stack = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add(c);
                index++;
            } else {
                if (!stack.isEmpty()) {
                    stack.remove(stack.size() - 1);
                    int value = map.getOrDefault(index, 0);
                    map.put(index, 0);
                    index--;
                    value = map.getOrDefault(index, 0) + value + 2;
                    if (value > max) {
                        max = value;
                    }
                    map.put(index, value);
                } else {
                    index++;
                }
            }
        }
        return max;
    }
}
