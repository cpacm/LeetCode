/**
 * Created by cpacm on 2017/7/4.
 */
public class ValidNumber {

    public static void main(String[] args) {
        System.out.println(isNumber("2e10d"));
    }

    public static boolean isNumber(String s) {
        if (s.endsWith("f") || s.endsWith("d")||s.endsWith("F") || s.endsWith("D"))
            return false;
        try {
            double d = Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
