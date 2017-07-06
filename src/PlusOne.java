/**
 * Created by cpacm on 2017/7/5.
 */
public class PlusOne {

    public static void main(String[] args) {
        System.out.println(new int[]{9, 9, 9, 9});
    }

    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        if (len <= 0) return digits;
        int temp = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] + temp >= 10) {
                digits[i] = 0;
                temp = 1;
            } else {
                digits[i] = digits[i] + 1;
                temp = 0;
                break;
            }
        }
        if (temp == 1) {
            int[] newD = new int[len + 1];
            System.arraycopy(digits, 0, newD, 1, len);
            newD[0] = 1;
            return newD;
        }
        return digits;
    }
}
