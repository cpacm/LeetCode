import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/5/2.
 */
public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        //System.out.println('a' - '2');
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            temp.clear();
            temp.addAll(result);
            result.clear();
            char c = digits.charAt(i);
            if (c <= '1' || c > '9') {
                return result;
            } else {
                char start;
                if (c <= '7') {
                    start = (char) ((c - '2') * 3 + 'a');
                } else {
                    start = (char) ((c - '2') * 3 + 1 + 'a');
                }
                int size = (c == '7' || c == '9') ? 4 : 3;
                if (temp.size() == 0) {
                    for (char k = start; k <start + size; k++) {
                        result.add(String.valueOf(k));
                    }
                } else {
                    for (String aTemp : temp) {
                        for (char k = start; k < start + size; k++) {
                            result.add(aTemp + String.valueOf(k));
                        }
                    }
                }
            }

        }
        return result;
    }

}
