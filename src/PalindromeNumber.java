import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/1/13.
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(344454443));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        long index = 0;
        int s = x;
        while (x > 0) {
            index = index * 10 + x % 10;
            x = x / 10;
        }

        return index == s;
    }
}
