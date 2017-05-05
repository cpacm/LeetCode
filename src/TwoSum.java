import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpacm on 2016/12/30.
 */
public class TwoSum {

    public static void main(String[] args){

    }

    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if(hashMap.containsKey(i)){
                answer[0] = hashMap.get(i);
                answer[1] = i;
                break;
            }
            else{
                hashMap.put(target - nums[i],i);
            }
        }
        return answer;
    }
}
