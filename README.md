
# 给自己的目标：每日一题
[LeetCode](https://leetcode.com/ "Online Judge Platform") 

在做题的过程中记录下解题的思路或者重要的代码碎片以便后来翻阅。题目从编号1开始逐渐递增。


## 1. Two Sum
题目：给出一个数组和一个目标值，求数组内两个值相加与目标值相等的下标。假设唯一解。
```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```
使用 HashMap 做存储，value为num值，key为目标值减去value后需要的值。
```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if(hashMap.containsKey(nums[i])){
                answer[0] = hashMap.get(nums[i]);
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
```


## 2. Add Two Numbers 
题目：输入两个链表，链表中的值一一相加，输出链表。

```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
```
由于会出现链表不同长和进位的情况，所以开头要做好为空的处理。
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1 == null && l2 == null)
                return null;
            if (l1 == null)
                l1 = new ListNode(0);
            if (l2 == null)
                l2 = new ListNode(0);
        }
        ListNode result = new ListNode(0);
        result.val = (l1.val + l2.val) % 10;
        int off = (l1.val + l2.val) / 10;
        if (l1.next != null) {
            l1.next.val += off;
        } else if ((l2.next != null)) {
            l2.next.val += off;
        } else if (off > 0) {
            l1.next = new ListNode(off);
        }
        result.next = addTwoNumbers(l1.next, l2.next);
        return result;
    }
}
```

## 3.  Longest Substring Without Repeating Characters 
题目：最长不重复子串
```
Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 

Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

贪心算法，需要一个Map记录字符最后出现的位置。同时还有一个 index 记录不重复字符串的开头以便计算长度和 max 最大值。
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int curIndex = 0;
        int max = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (charMap.containsKey(s.charAt(i))) {
                curIndex = Math.max(charMap.get(s.charAt(i)) + 1, curIndex);
            }
            charMap.put(s.charAt(i), i);
            max = Math.max(max, i - curIndex + 1);
        }
        return max;
    }
}
```

## 4. Median of Two Sorted Arrays 
题目：给出两个有序数组，合并后求中心值。

```
nums1 = [1, 3]
nums2 = [2]
The median is 2.0

nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5
```

给出两个指针分别指向两个数组的开头，当nums1上的 数小于nums2时，nums上的指针向后移一位，一直到两个长度和的中间值。

```java
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        double sum = 0;
        boolean flag = (m + n) % 2 == 1;
        int median = (m + n) / 2;
        int i = 0, j = 0;
        int x1 = 0;
        for (int k = 0; k <= n + m; k++) {
            if (i < m && j < n && nums1[i] <= nums2[j]) {
                x1 = nums1[i];
                i++;
            } else if (i < m && j < n && nums1[i] > nums2[j]) {
                x1 = nums2[j];
                j++;
            } else if (i >= m) {
                x1 = nums2[j];
                j++;
            } else if (j >= n) {
                x1 = nums1[i];
                i++;
            }
            if (flag) {
                if (k == median) {
                    sum = x1 * 1.0;
                    break;
                }
            } else {
                if (k == median - 1) {
                    sum += x1;
                } else if (k == median) {
                    sum += x1;
                    sum = sum / 2.0;
                    break;
                }
            }
        }
        return sum;
    }
}
```

## 5. Longest Palindromic Substring
题目：最长回文子串。string长度最大为1000
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Input: "cbbd"
Output: "bb"
```

回文有两种：（1）中间存在单个字符，如 bab；（2）左右对称，如 bb。
所以求回文时要把两种形式都要考虑进去。
求回文的方法：取中间一个值或中间两个相等的值分别向左向右循环比较。递归求解。
```java
public class Solution {
    private int index,max;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;

        for (int i = 0; i < s.length() - 1; i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }

        return s.substring(index, index + max);
    }
    
    private void extendPalindrome(String s,int begin,int end){
        while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        if (end - begin - 1 > max) {
            max = end - begin - 1;
            index = begin + 1;
        }
    }
}
```

## 6. ZigZag Conversion 
题目：将一行字符串按给出的行数竖向锯齿形排列，再按行输出。
```
input:PAYPALISHIRING
P   A   H   N
A P L S I I G
Y   I   R
ouput:PAHNAPLSIIGYIR
```
一道找规律的题目
对于每一层垂直元素的坐标 (i,j)= (j+1 )*n +i；对于每两层垂直元素之间的插入元素（斜对角元素），(i,j)= (j+1)*n -i

```java
public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int len = s.length();
        int span = numRows * 2 - 2;
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < numRows; i++) {
            int j = i;
            while (j < len) {
                sb.append(s.charAt(j));
                if (j % span == 0 || j % span == (numRows - 1)) {
                    j += span;
                } else {
                    int k = j + (span - i * 2);
                    if (k < len) {
                        sb.append(s.charAt(k));
                    }
                    j += span;
                }
            }
        }
        return sb.toString();
    }
}
```

## 7. Reverse Integer 
题目：数字反转,当反转后数字超出32位时，设值为0
```
Example1: x = 123, return 321
Example2: x = -123, return -321 
```

先将数字转换为字符串，然后利用字符串反转。当反转后数字溢出时，使用try...catch 捕获。

```java
public class Solution {
    public int reverse(int x) {
        boolean prefix = x < 0;
        int ax = Math.abs(x);
        StringBuffer sb = new StringBuffer();
        sb.append(ax);
        String s = sb.reverse().toString();
        int value = 0;
        try {
            value = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            value = 0;
        }
        if (prefix) {
            return -value;
        }
        return value;
    }
}
```

## 8. String to Integer (atoi) 
题目：字符串转换为数字。需要注意的是允许字符串前面出现的空格和当字符串中出现数字之外的值时要输出前面的数值。数值溢出时取最大或者最小值。

```java
public class Solution {
    public int myAtoi(String str) {
        int value = 0;
        int prefix = 1;
        boolean isFirst = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ' && isFirst) {
                continue;
            }
            if (isFirst && (c == '+' || c == '-')) {
                if (c == '-') {
                    prefix = -1;
                }
                isFirst = false;
                continue;
            }
            if (c >= '0' && c <= '9') {
                isFirst = false;
                int newValue = value * 10 + c - '0';
                if (newValue / 10 != value) {
                    if (prefix < 0) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
                value = value * 10 + c - '0';
            } else {
                return value * prefix;
            }

        }
        return value * prefix;
    }
}
```

## 9. Palindrome Number 
题目：回文数字

将数字反转判断是否相等即可

```java
public class Solution {
    public boolean isPalindrome(int x) {
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
```

## 10. Regular Expression Matching
题目：'.' 匹配任一字符，'*'匹配前面数字0或多个字符。输入一行字符串和一行匹配串，判断是否匹配。
```
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

```

递归和动态规划两种解法。自己先使用的递归方法，耗时有点长。

```
1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
3, If p.charAt(j) == '*': 
   here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
```

```java
public class Solution {
    public boolean isMatch(String s, String p) {
            if (s == null || p == null) {
                return false;
            }
            int m = s.length();
            int n = p.length();
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            for (int j = 0; j < n; j++) {
                if (p.charAt(j) == '*') {
                    dp[0][j + 1] = dp[0][j - 1];
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (p.charAt(j) == '.') {
                        dp[i + 1][j + 1] = dp[i][j];
                    }
                    if (p.charAt(j) == s.charAt(i)) {
                        dp[i + 1][j + 1] = dp[i][j];
                    }
                    if (p.charAt(j) == '*') {
                        if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                            dp[i + 1][j + 1] = dp[i + 1][j - 1];
                        } else {
                            dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1];
                        }
                    }
                }
            }
            return dp[m][n];
        }
    }
```

## 11. Container With Most Water
题目：以X轴为底，给出一组高度不同的值，求其中的两个值能够容纳最多水的值。

常规思路是输入一个值然后跟前面一一比较，这样导致时间复杂度为 nlogn 。但因为值是一口气给你的，所有可以从两边开始缩进，毕竟面积取决于短板。

```java
public class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            int area = Math.min(height[start], height[end]) * (end - start);
            if (height[start] <= height[end]) {
                start++;
            } else {
                end--;
            }
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
```

## 12. Integer to Roman 
题目： 数字转罗马字，范围为1-3999.

将千百十个的值用数组保存，然后整除求余获得相应值。

```java
public class Solution {
    
    String[] M = {"", "M", "MM", "MMM"};
    String[] D = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    
    public String intToRoman(int num) {
        String str = M[num / 1000] + D[num % 1000 / 100] + X[num % 100 / 10] + I[num % 10];
        return str;
    }
}
```

## 13. Roman to Integer 
题目：将罗马字转为数字
```java
public class Solution {
    public int romanToInt(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'M') {
                value += 1000;
            }
            if (s.charAt(i) == 'D') {
                value += 500;
            }
            if (s.charAt(i) == 'C') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'D')) {
                    value -= 100;
                } else {
                    value += 100;
                }
            }
            if (s.charAt(i) == 'L') {
                value += 50;
            }
            if (s.charAt(i) == 'X') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                    value -= 10;
                } else {
                    value += 10;
                }
            }
            if (s.charAt(i) == 'V') {
                value += 5;
            }
            if (s.charAt(i) == 'I') {
                if (i + 1 < s.length() && (s.charAt(i + 1) == 'X' || s.charAt(i + 1) == 'V')) {
                    value -= 1;
                } else {
                    value += 1;
                }
            }

        }
        return value;
    }
}
```


## 14. Longest Common Prefix 
题目： 输入一组字符串，求最长相同前缀。
```java
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String commonStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int j = 0;
            while (j < str.length() && j < commonStr.length()) {
                if (str.charAt(j) == commonStr.charAt(j)) {
                    j++;
                }else{
                    break;
                }
            }
            commonStr = commonStr.substring(0, j);
        }
        return commonStr;
    }
}
```
## 15. 3Sum 
题目：输入一行数字，求出所有三个数相加为0的所有组合，不允许重复。

```
For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

首先要给数组排序，依次取出一个值作为目标值，剩下的值依次取和来匹配。同时当值相同时要去重复。利用大小比来避免没必要的重复且能保证不会错过组合。

```java
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int start = i + 1, end = nums.length - 1, target = 0 - nums[i];
                while (start < end) {
                    if (nums[start] + nums[end] == target) {
                        result.add(Arrays.asList(nums[start], nums[end], nums[i]));
                        while (start < end && nums[end] == nums[end - 1]) end--;
                        while (start < end && nums[start + 1] == nums[start]) start++;
                        end--;
                        start++;
                    } else if (nums[start] + nums[end] < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }

        return result;
    }
}
```

## 16. 3SumClosest 
题目：输入一行数字和一个目标数字，求出三个数相加最接近目标的值。
```
For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```
思路与 3sum 一样，都是先排序再前后取值，只不过目标值从0变为要输入的值。

```java
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int distance = Integer.MAX_VALUE;
        int min = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                int start = i + 1, end = nums.length - 1, t = target - nums[i];
                while (start < end) {
                    int value = nums[start] + nums[end];
                    if (value == t) {
                        return target;
                    } else if (value > t) {
                        end--;
                        if (distance > value - t) {
                            distance = value - t;
                            min = nums[i] + value;
                        }
                    } else {
                        start++;
                        if (distance > t - value) {
                            distance = t - value;
                            min = nums[i] + value;
                        }
                    }
                }
            }
        }
        return min;
    }
}
```

## 17. Letter Combinations of a Phone Number
题目：求老式手机上按下数字键后所有的字符组合，按下0和1会导致数组为空。
![手机键盘](https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png)
```
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```
利用char的数字特性来找到键盘的规律，之后便是不断循环添加字符。
```java
public class Solution {
    public List<String> letterCombinations(String digits) {
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
```
## 18. 4Sum 
题目：给出一行数字和一个目标值，求出其中4个数相加等于目标值的所有组合。不能有重复。
```
For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```

思路同 3sum 一样，不过要在 3sum 上再套上一层循环。

```java
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                int t = target - nums[i];
                threeSum(nums, t, result, i + 1);
            }
        }
        return result;
    }
    
    public void threeSum(int[] nums, int target, List<List<Integer>> result, int k) {
        for (int i = k; i < nums.length - 2; i++) {
            if (i == k || (i > k && nums[i] != nums[i - 1])) {
                int start = i + 1, end = nums.length - 1, t = target - nums[i];
                while (start < end) {
                    if (nums[start] + nums[end] == t) {
                        result.add(Arrays.asList(nums[k-1], nums[start], nums[end], nums[i]));
                        while (start < end && nums[end] == nums[end - 1]) end--;
                        while (start < end && nums[start + 1] == nums[start]) start++;
                        end--;
                        start++;
                    } else if (nums[start] + nums[end] < t) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
    }
}
```

## 19. Remove Nth Node From End of List 
题目：输入一串节点和一个数字n,n表示从尾部开始数起的第n个节点，这个节点将会从节点列表汇中删除。
```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```
首先从头到尾遍历一遍获取节点列表的长度，之后便是再从头部开始找到要删除的节点。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        int len = 0;
        ListNode dummy = new ListNode(-1), pt = dummy;
        dummy.next = head;
        
        while (pt.next != null) {
            pt = pt.next;
            len++;
        }
        pt = dummy;
        for (int cnt = len - n; cnt > 0; cnt--) pt = pt.next;
        if (pt.next != null) {
            pt.next = pt.next.next;
        }
        return dummy.next;
    }
}
```

## 20. Valid Parentheses
题目：有效的括号，输入一行只包含"(){}[]"的字符串，判断是否有效（即括号能否左右对应）

使用栈FILO的特性来匹配。
```java
public class Solution {
    public boolean isValid(String s) {
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
```

## 21. Merge Two Sorted Lists
题目：给出两个有序的字节列表，将其按照大小合并。

与题目4的解题思路相同，同样也是从两个头部开始逐步比较大小。
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                head.next = l2;
                break;
            } else if (l2 == null) {
                head.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
                head = head.next;
            } else {
                head.next = l2;
                l2 = l2.next;
                head = head.next;
            }
        }
        return temp.next;
    }
}
```

## 22. Generate Parentheses 
题目：给出一个数值 n,求能生成所有合法结构的组合。
```
 For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

一道基础的深度优先算法，当左括号的个数小于右括号的个数时退出循环递归。当左括号和右括号为0时，加入结果集。

```java
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> temp = new ArrayList<>();
        generateP("",n,n,temp);
        return temp;
    }
    
    public void generateP(String str, int left, int right, List<String> list) {
        if (left > right) {
            return;
        }
        if (left > 0) {
            generateP(str + "(", left - 1, right, list);
        }
        if (right > 0) {
            generateP(str + ")", left, right - 1, list);
        }
        if (left == 0 && right == 0) {
            list.add(str);
        }
    }
}
```

## 23. Merge k Sorted Lists 
题目：合并多个有序的节点列表，要求按大小排列。
最初是两两从头合并导致 TLE。之后使用归并排序来进行优化。
`归并操作：将两个顺序合并成一个顺序序列的方法`

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> list = new ArrayList<>();
        Collections.addAll(list, lists);
        return mergeKLists(list);
    }

   public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);

        int length = lists.size();
        int mid = (length - 1) / 2;
        ListNode l1 = mergeKLists(lists.subList(0, mid + 1));
        ListNode l2 = mergeKLists(lists.subList(mid + 1, length));
        return merge2Lists(l1, l2);
    }
    
    public  ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null) {
            temp.next = l1;
        } else if (l2 != null) {
            temp.next = l2;
        }
        return head.next;
    }
}
```

## 24. Swap Nodes in Pairs 
题目： 给出一个字节列表，两个数为一组进行交换。节点里面的 val 是无法被改变的。
```
Given 1->2->3->4, you should return the list as 2->1->4->3. 
```

交换时要知道四个数，交换的i1和i2，i1前一个数i0,i2的后一个数i3.

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode v = new ListNode(-1);
        v.next = head;
        ListNode res = v;
        while (v.next != null && v.next.next != null) {
            ListNode temp = v.next;
            ListNode l1 = temp.next;
            ListNode l2 = temp.next.next;
            l1.next = temp;
            temp.next = l2;
            v.next = l1;
            v = v.next.next;
        }
        return res.next;
    }
}
```

## 25. Reverse Nodes in k-Group 
题目：给出一组节点列表和一个数字，按组来反转节点，组里面数字的个数为输入时给出的数字。
```
For example,
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5 
```

我的解法是先将所有 ListNode 放入队列中，再按照K值来进行反转。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        List<ListNode> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head);
            head = head.next;
        }
        int size = nodeList.size();
        int start = 0;
        while (size >= k) {
            swapNodes(nodeList, start, start + k - 1);
            size = size - k;
            start = start + k;
        }
        ListNode v = nodeList.get(0);
        ListNode temp = v;
        for (int i = 1; i < nodeList.size(); i++) {
            temp.next = nodeList.get(i);
            temp = temp.next;
        }
        temp.next = null;
        return v;
    }
    
    public void swapNodes(List<ListNode> list, int start, int end) {
        if (end >= list.size()) {
            return;
        }
        while (start < end) {
            ListNode temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }
}
```

## 26. Remove Duplicates from Sorted Array
题目：给出一组有序的数组，移除重复的数字后，输出不重复数字的个数。不允许分配新的空间来存储数组。
```
Given input array nums = [1,1,2]
output:length = 2,nums = [1,2]
```
直接将重复的数字用之后不重复的数字覆盖过去，这样输出的数组就能够不存在重复的数字了。
```java
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 1;
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != temp) {
                temp = nums[i];
                nums[res] = nums[i];
                res++;
            }
        }
        return res;
    }
}
```

## 27. Remove Element
题目：给出一组有序的数组和一个给定的值，从数组中移除给定的数字后，输出数组长度。不允许分配新的空间来存储数组
```
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
```

解题与 # 26 思路一样，同样是碰到指定的值后使用后面的值来进行覆盖。
```java
public class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res] = nums[i];
                res++;
            }
        }
        return res;
    }
}
```

## 28. Implement strStr()
题目：给出两个字符串，求子串在父串的的位置，若不匹配返回-1.

之前以为 indexOf() n*m 的时间复杂度会导致TLE，便想先看看KMP的算法或者Sunday算法来缩减复杂度，后来试了一下发现n*m的时间复杂度能够直接AC.

```java
public class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```

## 29. Divide Two Integers
题目：给出两个数进行相除，要求程序不能使用乘法，除法和求模的运算。数值溢出使用最大值。

刚开始直接使用减法来求值，最后导致 TLE . 换一种思路，任何一个整数可以表示成以2的幂为底的一组基的线性组合，即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。时间复杂度为 O(logn).
```
63/4 = 15;
63 = 32+16+8+4+3 = 4*2^3+4*2^2+4*2^1+4*2^0+3=(8+4+2+1)*4+3 = 63
```
注意正负数和数值的溢出。
```java
public class Solution {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if (dividend < 0) sign = -sign;
        if (divisor < 0) sign = -sign;
        long temp = Math.abs((long) dividend);
        long temp2 = Math.abs((long) divisor);
        long c = 1;
        while (temp > temp2) {
            temp2 = temp2 << 1;
            c = c << 1;
        }
        long res = 0;
        while (temp >= Math.abs((long) divisor)) {
            while (temp >= temp2) {
                temp -= temp2;
                res += c;
            }
            temp2 = temp2 >> 1;
            c = c >> 1;
        }
        if (sign > 0) {
            if (res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) res;
        } else return -(int) res;
    }
}
```

## 30. Substring with Concatenation of All Words `TLE`
题目：给定一个字符串S和一个字符串数组L，L中的字符串长度都相等，找出S中所有的子串恰好包含L中所有字符各一次，返回子串的起始位置。
```
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
```

使用map将数组中的字符串记录下来，从字符串截取字符来进行匹配。但是在最后的 case 中会出现 TLE.在网上找到的资料是说可以使用 slide window 的算法来实现，等待以后来学习。
```java
/**
* 这是 TLE 的代码
 *  */
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.containsKey(str) ? map.get(str) + 1 : 1);
        }
        int len = words[0].length();
        for (int i = 0; i <= s.length() - len * words.length; i++) {
            HashMap<String, Integer> tempMap = new HashMap<>(map);
            int temp = i;
            int count = 0;
            String tempStr = s.substring(temp, temp + len);
            while (tempMap.containsKey(tempStr) && tempMap.get(tempStr) > 0) {
                tempMap.put(tempStr, tempMap.get(tempStr) - 1);
                temp = temp + len;
                count++;
                if (temp + len <= s.length()) {
                    tempStr = s.substring(temp, temp + len);
                } else {
                    break;
                }
            }
            if (count == words.length) {
                res.add(i);
            }
        }
        return res;
    }
}
````

## 31. Next Permutation
题目：给出一个序列，寻找比当前排列顺序大的下一个排列。规则如下：
```
1,2,3 ->next
1,3,2 ->next
2,1,3 ->next
2,3,1 ->next
3,1,2 ->next
3,2,1 ->next
1,2,3 ->loop
```
因为是找递增的下一个排列，所以从后往前找到第一个升序对的位置，如1,2,4,3,1， 从后向前找就是2,4,3,1，因为2比前一个数4小，所以就锁定2这个数。
之后就是在4,3,1中找到比2大的最小的那个数3，将3与2对换得到降序排列4,2,1.然后就是将4,2,1反序得到1,2,4.最终结果就是1,3,1,2,4

```java
public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;
        int len = nums.length;
        int index = len - 1;
        int value = nums[index];
        for (index = index - 1; index >= 0; index--) {
            if (nums[index] < value) {
                value = nums[index];
                break;
            }
            value = nums[index];
        }
        if (index < 0) {
            reversal(nums, 0, len - 1);
        } else {
            for (int j = len - 1; j > index; j--) {
                if (nums[j] > value) {
                    nums[index] = nums[j];
                    nums[j] = value;
                    reversal(nums, index + 1, len - 1);
                    break;
                }
            }
        }
    }

    public void reversal(int[] nums, int start, int end) {
        int len = end + 1 - start;
        for (int i = 0; i < len / 2; i++) {
            int k = nums[start + i];
            nums[start + i] = nums[end - i];
            nums[end - i] = k;
        }
    }
}
````

## 32. Longest Valid Parentheses
题目：输入一串只包含"(",")"的字符串，求合法子串的最大值。
```
")()())", where the longest valid parentheses substring is "()()", which has length = 4.
```
一道很容易TLE的题目，最优解法是使用 stack 记录字符串中"("出现时的index，当出现")"进行匹配时相减得到长度。
```java
public class Solution {
    public int longestValidParentheses(String s) {
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
}
```

## 33.Search in Rotated Sorted Array
题目：给出一组有序的数组，如 `0 1 2 4 5 6 7`，将其分成两段并互换，比如会成为`4 5 6 7 0 1 2`。给出一个 target 值，求在数组中的位置，若不存在输出-1

一种最简单的方法是直接遍历过，时间复杂度为O(n),另一种是先使用 binary search 找出旋转点（最小值）所在的位置，之后在用二分遍历一次获得 target 所在的位置。

```java
/**
* 简单遍历
*/
public class Solution {
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
```

## 34. Search for a Range
题目：给出一个有序的数组和一个目标值，求该目标值在数组的范围（由于目标值在数组中存在好几个）。要求时间复杂度为O(logn)
```
For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
```

一个很简单的二分查找题，要注意的是即使找到一个目标值也要继续向左右查找下去以便确定目标值在数组中的范围。
```java
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        int st = 0, len = nums.length - 1;
        while (st <= len) {
            int mid = (len + st + 1) / 2;
            if (nums[mid] == target) {
                start = binarySearch(nums, st, mid - 1, target, true);
                end = binarySearch(nums, mid + 1, len, target, false);
                if (start == -1) start = mid;
                if (end == -1) end = mid;
                break;
            } else if (nums[mid] > target) {
                len = mid - 1;
            } else if (nums[mid] < target) {
                st = mid + 1;
            }
        }
        return new int[]{start, end};
    }

    public int binarySearch(int[] nums, int start, int end, int target, boolean left) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end + 1) / 2;
        int index = -1;
        if (nums[mid] == target) {
            if (left) {
                index = binarySearch(nums, start, mid - 1, target, left);
            } else {
                index = binarySearch(nums, mid + 1, end, target, left);
            }
            if (index == -1) {
                return mid;
            }
            return index;
        } else if (nums[mid] > target) {
            return binarySearch(nums, start, mid - 1, target, left);
        } else if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, end, target, left);
        } else {
            return index;
        }
    }
}
```

## 35. Search Insert Position
题目：给出一组有序数组和一个目标数字，求目标数字在数组中的位置，没找到时则求插入数组的位置。
```
Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
```
一道简单的二分查找题
```java
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int index = start;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target > nums[mid]) {
                start = mid + 1;
                index = start;
            } else if (target < nums[mid]) {
                end = mid - 1;
                index = mid;
            } else {
                return mid;
            }
        }
        return index;
    }
}
````

## 36. Valid Sudoku
题目：给出九个字符串，每个字符串有数字和 '.'组成，'.'表示要填写的数字，判断这九个字符串能否组成一个有效的数独。
有效数独：每个单元，每行，每列都必须有1-9组成，不能重复。不一定要求有解。

![sudoku](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

不要傻乎乎的循环去求每行每列和每个单元是否存在重复数字，可以给每个数字进行位置标记，再利用set看是否有重复。
```
    '4' in row 7 is encoded as "(4)7".
    '4' in column 7 is encoded as "7(4)".
    '4' in the top-right block is encoded as "0(4)2".
```

```java
public class Solution {

    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        return false;
                }
            }
        }
        return true;
    }

}
```

## 37. Sudoku Solver
题目：给出一个有效的数独表，求空缺值。假设必定有解。

![blank sudoku](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)
![filled_sudoku](https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

使用回溯来求解，每个空格可以填1-9的值，每填入一个值去判断数独表是否是有效的数独表，若有效填写下一个无效则循环0-9,循环结束后没有找到值则回到上一个。

```java
public class Solution {
    public void solveSudoku(char[][] board) {
        sudoku(board, 0,0);
    }

    public boolean sudoku(char[][] board, int i, int j) {
        if (j >= 9) {
            return sudoku(board, i + 1, 0);
        }
        if (i >= 9) {
            return true;
        }
        char c = board[i][j];
        if (c != '.') {
            return sudoku(board, i, j + 1);
        } else {
            for (int k = 1; k <= 9; k++) {
                char cc = (char) (k + '0');
                board[i][j] = cc;
                if (isValidSudoku(i, j, board)) {
                    if (sudoku(board, i, j + 1)) {
                        return true;
                    }
                }
                board[i][j] = '.';
            }
        }
        return false;
    }

    public boolean isValidSudoku(int i, int j, char[][] board) {
        for (int k = 0; k < 9; k++) {
            if (k != j && board[i][k] == board[i][j])
                return false;
        }
        for (int k = 0; k < 9; k++) {
            if (k != i && board[k][j] == board[i][j])
                return false;
        }
        for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {
            for (int col = j / 3 * 3; col < j / 3 * 3 + 3; col++) {
                if ((row != i || col != j) && board[row][col] == board[i][j])
                    return false;
            }
        }
        return true;
    }
}
```

## 38. Count and Say
题目：n=1时输出字符串1；n=2时，数上次字符串中的数值个数，因为上次字符串有1个1，所以输出11；n=3时，由于上次字符是11，有2个1，所以输出21；n=4时，由于上次字符串是21，有1个2和1个1，所以输出1211。依次类推，写个countAndSay(n)函数返回字符串。
```
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
```

这道题理解了题意就是一道简单题，代码如下：
```java
public class Solution {
    public String countAndSay(int n) {
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
```

## 39. Combination Sum
题目：给出一组数字和一个目标数，数组内部的数字不重复，但可以多次使用。求数组里面和为目标数的组合。要求组合不能重复。
```
For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:
[
  [7],
  [2, 2, 3]
]
```
递归求解。
1）当目标值与当前所选择的值相等时，将组合加入列表时。
2）当目标值大于所选择的值时，将值加入组合并将目标值减去选择值进入下一轮递归。
3）当目标值小于所选择的值，跳过当前值。

```java
public class Solution {
    List<List<Integer>> cs = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        sum(candidates, target, new ArrayList<>(), 0);
        return cs;
    }

    public void sum(int[] candidates, int target, List<Integer> list, int index) {
        for (int i = index; i < candidates.length; i++) {
            List<Integer> temp = new ArrayList<>(list);
            if (target == candidates[i]) {
                temp.add(candidates[i]);
                cs.add(temp);
                continue;
            } else if (candidates[i] > target) {
                sum(candidates, target, temp, i + 1);
                break;
            } else if (target > candidates[i]) {
                temp.add(candidates[i]);
                sum(candidates, target - candidates[i], temp, i);
            }

        }
    }
}
```

## 40. Combination Sum II
题目：给出一组数字和一个目标数，数组内部的数字有重复，且只能使用一次。求数组里面和为目标数的组合。要求组合不能重复
```
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
A solution set is:

[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

我使用的方法是使用map来去重和记录数字重复的个数，虽然也能AC但map的寻址拖慢了整个运行速度。最优的解法是先对数组进行排序，当遇到重复的数字时跳过重复的步骤。

```java
/**
* 有待优化的代码
*/
public class Solution {
    List<List<Integer>> cs = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < candidates.length; i++) {
            map.put(candidates[i], map.getOrDefault(candidates[i], 0) + 1);
        }
        sum(target, new ArrayList<>(), map);
        return cs;
    }

    public void sum(int target, List<Integer> list, Map<Integer, Integer> map) {
        Set<Integer> keys = map.keySet();
        Map<Integer, Integer> tempMap = new HashMap<>(map);
        for (int key : keys) {
            List<Integer> temp = new ArrayList<>(list);
            int value = tempMap.get(key);
            if (target == key) {
                temp.add(key);
                cs.add(temp);
                tempMap.remove(key);
                continue;
            } else if (key > target) {
                tempMap.remove(key);
                sum(target, temp, tempMap);
                break;
            } else if (target > key) {
                temp.add(key);
                if (value > 1) {
                    tempMap.put(key, tempMap.get(key) - 1);
                } else {
                    tempMap.remove(key);
                }
                sum(target - key, temp, tempMap);
                tempMap.remove(key);
            }
        }
    }
}
```

## 41. First Missing Positive
题目：给出一组无序数组，找出第一个缺少的正整数。要求时间复杂度为O(n),空间为O(1)
```
For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.
```
正整数从1开始，所以可以将数组的下标序号与此一一对应。
比如说4,3,6,1,5的数组，第一个数为4,，则与第四个数交换 -> 1,3,6,4,5. 此时第一个数为1，不再交换进行下一轮。
第二个数为3，则与第三个数交换 -> 1,6,3,4,5.此时第2个数为6，没有与序号对应需要再次交换，但此时6已经超过数组的长度所以跳过进入下一轮。
第三个数为3，与序号相等,下一轮...
直到完全交换完成，再次遍历整个数组，找出第一个没有对应的序号则是我们要求的数，这个例子则是为第二个数，所以返回2.
```java
public class Solution {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int value = nums[i];
            if (value == i + 1 || value <= 0 || value >= nums.length) {
                i++;
            } else {
                int temp = nums[value - 1];
                if (temp == nums[i]) {
                    i++;
                    continue;
                }
                nums[value - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != k + 1) {
                return k + 1;
            }
        }
        return nums.length + 1;
    }
}
```

## 42. Trapping Rain Water
题目：给出一组数字代表柱高，求能够容量的雨水体积。
```
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
```
![rain water](https://leetcode.com/static/images/problemset/rainwatertrap.png)

与 ContainerWithMostWater 的思路类似，也是采取从两边向中间遍历的方法。我们使用两个指针，一个从左向右遍历，一个从右向左遍历。从左向右遍历时，记录下上次左边的峰值，如果右边一直没有比这个峰值高的，就已经加上这些差值。从右向左遍历时，记录下上次右边的峰值，如果左边一直没有比这个峰值高的，就加上这些差值。难点在于，当两个指针遍历到相邻的峰时，我们要选取较小的那个峰值来计算差值。所以，我们在遍历左指针或者右指针之前，要先判断左右两个峰值的大小。

```java
public class Solution {
    public int trap(int[] height) {
        int secHight = 0;
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                secHight = Math.max(height[left], secHight);
                area += secHight - height[left];
                left++;
            } else {
                secHight = Math.max(height[right], secHight);
                area += secHight - height[right];
                right--;
            }
        }
        return area;
    }
}
```

## 43. Multiply Strings
题目：输入两个由数字组成的字符串，求他们的乘积。两个字符串的长度小于110，且都是有0-9组成，没有包含前置0，不能用 BigInteger 或者直接将字符串转化成数字来进行计算。

大数的乘法计算，我采取的是最传统的做法：挨个相乘并做大数的加法，最后得到解，算法并不算是最优解。
```java
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num1.length(); i++) {
            int k = num1.charAt(i) - '0';
            if (result.length() != 0) {
                result.append("0");
            }
            result = add(result, singleMul(k, num2));
        }
        return result.toString();
    }

    public StringBuilder add(StringBuilder s1, StringBuilder s2) {
        int s = 0;
        StringBuilder sb = new StringBuilder();
        int len1 = s1.length() - 1;
        int len2 = s2.length() - 1;
        while (len1 >= 0 || len2 >= 0) {
            int m1 = len1 >= 0 ? s1.charAt(len1) - '0' : 0;
            int m2 = len2 >= 0 ? s2.charAt(len2) - '0' : 0;
            sb.insert(0, (m1 + m2 + s) % 10);
            s = (m1 + m2 + s) / 10;
            len1--;
            len2--;
        }
        if (s != 0) {
            sb.insert(0, s);
        }
        return sb;
    }

    public StringBuilder singleMul(int num, String num2) {
        StringBuilder sb = new StringBuilder();
        int s = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            int k = num2.charAt(i) - '0';
            sb.insert(0, (num * k + s) % 10);
            s = (num * k + s) / 10;
        }
        if (s != 0) {
            sb.insert(0, s);
        }
        return sb;
    }
}
```
最优的解法是
设两个数长度为m,n,然后定义一个数组a[m+n-1]，其各位乘积的规律为`a[i+j] += (num1.charAt(i)-'0')*(num2.charAt(j)-'0');`
```java
public class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) return "0";
        int m = num1.length(), n = num2.length();
        int[] digits = new int[m + n -1];
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                digits[i+j] += (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
            }
        }
        int carry = 0;
        String rs = "";
        for(int i=m+n-2; i>=0; i--){
            int temp = digits[i]+carry;
            carry = temp/10;
            rs = (temp%10) + rs;
        }
        rs = (carry==0?"":carry) + rs;
        return rs;
    }
}
```

## 44. Wildcard Matching
题目：字符串匹配
```
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
```
刚开始用的是递归算法，很遗憾的是TLE了，后面用的是暴力解法，当遇到 '\*' 号时做上标记，当后续无法继续匹配下去时回到 '\*' 的位置开始匹配
```java
public class Solution {
    public boolean isMatch(String s, String p) {
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

}
```

## 45. Jump Game II
题目：跳棋。给出一组数据，里面的值表示为可跳的最大范围，求从第一个数可以跳到最后一个数最小的步数。
```
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
```
动态规范，每一步都取 `i + nums[i]` 最大的那一步来走。
```java
public class Solution {
    public int jump(int[] nums) {
        int res = 0, i = 0, cur = 0, n = nums.length;
        while (cur < n - 1) {
            int pre = cur;
            while (i <= pre) {
                cur = Math.max(cur, i + nums[i]);
                ++i;
            }
            ++res;
            if (pre == cur) return -1;
        }
        return res;
    }
}
```

## 46. Permutations
题目：给出一组不重复的数字，求其排列组合
```
 For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```
思路：每输入一个数，在原有的列表中每个数之间插入新的数生成新的组合。
```java
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i : nums) {
            List<List<Integer>> temp = new ArrayList<>(result);
            result.clear();
            if (temp.size() == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                result.add(list);
                continue;
            }
            for (List<Integer> list : temp) {
                for (int k = 0; k <= list.size(); k++) {
                    if (k == list.size()) {
                        list.add(i);
                        result.add(list);
                        break;
                    }
                    List<Integer> ll = new ArrayList<>(list);
                    ll.add(k, i);
                    result.add(ll);
                }
            }
        }
        return result;
    }
}
```

## 47. Permutations II
题目：给出一组数字，可能重复，求其排列组合，要求结果不能包含有重复排列。
```
 For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

思路：使用46的思路是行不通的，因为不能有效的去重。可以先将数组排序，再通过深度优先搜索进行查找，同时将已经使用过的数字通过布尔数组进行记录。当遇到与前一个数字相等时，判断前一个数是否使用过，未使用则跳过。
```java
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        unique(nums, used, list, res);
        return res;
    }

    public void unique(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
            used[i] = true;
            list.add(nums[i]);
            unique(nums, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
```

## 48. Rotate Image
题目：给出一组n x n二维数组，将其顺时针旋转，求旋转后的数组。

将旋转后的数组和旋转前的数组比较一下可以得出一下规律:
```
旋转后——matrix[i][j]
旋转前——matrix[n-1-j][i]
```
```java
public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = temp[n - 1 - j][i];
            }
        }
    }
}
```

也可以不使用 temp 数组，直接变换
```
旋转前——matrix[i][j]
旋转后——matrix[j][n-1-x]

变化过程：swap(matrix[i][j], matrix[j][i]) —— swap(matrix[i][j], matrix[i][n-1-j]
```
```java
public class Solution {
    public void rotate(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
}
```

## 49. Group Anagrams
题目：给出一组字符串，将其按照字符的组成进行分类。
```
For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```
思路：将每一个字符串里面的字符进行排序放到HashMap中。
```java
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ll = new ArrayList<>();
        if (strs.length <= 0) return ll;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String sstr = sortStr(str);
             if (map.containsKey(sstr)) {
                map.get(sstr).add(str);
            } else {
                List<String> l = new ArrayList<>();
                l.add(str);
                map.put(sstr, l);
            }
        }
        return new ArrayList<>(map.values());
    }

    public String sortStr(String str) {
        char[] cc = str.toCharArray();
        Arrays.sort(cc);
        return String.valueOf(cc);
    }
}
```

## 50. Pow(x, n)
题目：求 x 的 n 次方。
一个 `Math.pow()` 方法解决,或者使用二分法。

```java
public class Solution {
    public double myPow(double x, int n) {
        double temp = x;
        if (n == 0) return 1;
        temp = myPow(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        } else {
            if (n > 0)
                return x * temp * temp;
            else
                return (temp * temp) / x;
        }
        //return Math.pow(x, n);
    }
}
```

## 51. N-Queens
题目：经典的N皇后问题，给出一个NxN的棋盘,求所有皇后排列的组合。
![8-queens](https://leetcode.com/static/images/problemset/8-queens.png)
要保证皇后不能互相攻击，就必须要求任意两个皇后不在同一行，同一列和同一对角线上。

```
For example,
There exist two distinct solutions to the 4-queens puzzle:
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
```

一个NP问题，开一个n大的数组，下标表示行值表示列。每加入一个值时先与之前放入数组的值进行判断是否符合规则，符合规则继续下一个。

```java
public class Solution {
    private List<List<String>> resultList = new ArrayList<>();
    private int[] positions;
    private char[] tags;

    public List<List<String>> solveNQueens(int n) {
        positions = new int[n];
        tags = new char[n];
        Arrays.fill(positions, -1);
        Arrays.fill(tags, '.');
        dp(0, n);
        return resultList;
    }

    public void dp(int n, int max) {
        if (n == max) {
            List<String> qLine = new ArrayList<>();
            for (int i = 0; i < positions.length; i++) {
                tags[positions[i]] = 'Q';
                String q = new String(tags);
                qLine.add(q);
                tags[positions[i]] = '.';
            }
            resultList.add(qLine);
            return;
        }
        int valueX = n;
        int valueY = 0;
        while (valueY < max) {
            if (hasPlaced(n, valueX, valueY)) {
                positions[n] = valueY;
                dp(n + 1, max);
            }
            valueY++;
        }
    }

    public boolean hasPlaced(int n, int valueX, int valueY) {
        for (int j = 0; j < n; j++) {
            int x = j;
            int y = positions[j];
            if (positions[j] == valueY || Math.abs(valueX - x) == Math.abs(valueY - y)) {
                return false;
            }
        }
        return true;
    }
}
```

## 52. N-Queens II
与上一题一样的题目，不过是求组合的个数而不需要将组合罗列出来
```java
public class Solution {
    private int count = 0;
    private int[] positions;

    public int totalNQueens(int n) {
        positions = new int[n];
        Arrays.fill(positions, -1);
        dp(0, n);
        return count;
    }

    public void dp(int n, int max) {
        if (n == max) {
            count++;
            return;
        }
        int valueX = n;
        int valueY = 0;
        while (valueY < max) {
            if (hasPlaced(n, valueX, valueY)) {
                positions[n] = valueY;
                dp(n + 1, max);
            }
            valueY++;
        }
    }

    public boolean hasPlaced(int n, int valueX, int valueY) {
        for (int j = 0; j < n; j++) {
            int x = j;
            int y = positions[j];
            if (positions[j] == valueY || Math.abs(valueX - x) == Math.abs(valueY - y)) {
                return false;
            }
        }
        return true;
    }
}
```

## 53. Maximum Subarray
题目：给出一个数组，求一个和最大的子数组。
```
For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
```
需要一个额外的数组来维护每个位置的最大值，以便进行下一个位置最大值的计算。

```java
public class Solution {
    public int maxSubArray(int[] nums) {
        int[] total = new int[nums.length];
        total[0] = nums[0];
        int totalMax = total[0];
        for (int i = 1; i < nums.length; i++) {
            total[i] = Math.max(nums[i], nums[i] + total[i - 1]);
            totalMax = Math.max(totalMax, total[i]);
        }
        return totalMax;
    }
}
```

## 54. Spiral Matrix
题目：给出一组m x n 的二维数字数组，返回螺旋顺序的数字列表。
```
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

You should return [1,2,3,6,9,8,7,4,5].
```
将螺旋的顺序分解开来，可以分成4种顺序，依次为从左向右，从上向下，从右向左，从下向上。定义4个变量分别为`m-剩下的行数`,`n-剩下的列数`,`startM-行开始的坐标`,`startN-列开始的地方`。
接下来就是按顺序求列表了。
```java
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> s = new ArrayList<>();
        int m = matrix.length;
        if(m == 0) return s;
        int n = matrix[0].length;
        int startM = 0, startN = 0;
        while (m > 0 && n > 0) {
            spiral1(startM, startN, m, n, matrix, s);
            startM++;
            m--;
            if (m <= 0 || n <= 0) return s;
            spiral2(startM, startN, m, n, matrix, s);
            n--;
            if (m <= 0 || n <= 0) return s;
            spiral3(startM, startN, m, n, matrix, s);
            m--;
            if (m <= 0 || n <= 0) return s;
            spiral4(startM, startN, m, n, matrix, s);
            startN++;
            n--;
        }
        return s;
    }

    public void spiral1(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = startN; k < n + startN; k++) {
            s.add(matrix[startM][k]);
        }
    }

    public void spiral2(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = startM; k < m + startM; k++) {
            s.add(matrix[k][n + startN - 1]);
        }
    }

    public void spiral3(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = startN + n - 1; k >= startN; k--) {
            s.add(matrix[startM + m - 1][k]);
        }
    }

    public void spiral4(int startM, int startN, int m, int n, int[][] matrix, List<Integer> s) {
        for (int k = m + startM - 1; k >= startM; k--) {
            s.add(matrix[k][startN]);
        }
    }
}
```

## 55. Jump Game
题目：跳棋，给出一组数字，每个数字代表当前可以跳过的最大距离，求是否能够从第一个位置达到最后一个位置。
```
 For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
```
要先记录每个位置能够跳到的最远距离，特殊处理当数值为0时能够跳过去。
```java
public class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;
        if (nums[0] == 0) return false;
        int[] maxs = new int[nums.length];
        maxs[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                if (maxs[i - 1] <= 1) {
                    return false;
                }
                maxs[i] = maxs[i - 1] - 1;
            }
            maxs[i] = Math.max(maxs[i - 1] - 1, nums[i]);
        }
        return true;
    }

}
```

## 56. Merge Intervals
题目：给出一组包含闭合线段的对象数组，求去除重叠后的对象数组。
```
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
```
先将给出的数组按线段的开头进行排序（快排或者归并排序），之后就可以每加入一个线段只需要跟前一个线段判断是否重复就可以了。
```java
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        quickSort(0, intervals.size() - 1, intervals);
        List<Interval> res = new ArrayList<>();
        for (Interval interval : intervals) {
            if (res.size() == 0) {
                res.add(interval);
                continue;
            }
            Interval pastInterval = res.get(res.size() - 1);
            if (interval.start > pastInterval.end) {
                res.add(interval);
            } else {
                pastInterval.end = Math.max(pastInterval.end, interval.end);
            }
        }
        return res;
    }

    public void quickSort(int start, int end, List<Interval> intervals) {
        if (start >= end) return;
        int startTemp = start;
        int endTemp = end;
        Interval value = intervals.get(start);
        while (start < end) {
            while (intervals.get(end).start >= value.start && end > start)
                end--;
            intervals.set(start, intervals.get(end));
            while (intervals.get(start).start <= value.start && end > start)
                start++;
            intervals.set(end, intervals.get(start));
        }
        intervals.set(start, value);
        quickSort(startTemp, end, intervals);
        quickSort(end + 1, endTemp, intervals);
    }
}
```

## 57. Insert Interval
题目：给出一组已经有序的线段数组，插入一个新的线段，求去重复的新数组。
```
 Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
```
一道逻辑判断题，先根据各种边界条件加入新的线段（主要根据start插入新的线段，end之后再处理），之后再处理end的边界问题。
```java
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() <= 0) {
            intervals.add(newInterval);
            return intervals;
        }
        int position = -1;
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.start <= newInterval.start && interval.end >= newInterval.start) {
                position = intervals.indexOf(interval);
                interval.end = Math.max(interval.end, newInterval.end);
                break;
            } else if (interval.start >= newInterval.start && interval.start <= newInterval.end) {
                position = intervals.indexOf(interval);
                interval.start = newInterval.start;
                interval.end = Math.max(interval.end, newInterval.end);
                break;
            } else if (interval.start > newInterval.start) {
                int index = intervals.indexOf(interval);
                intervals.add(index, newInterval);
                break;
            } else if (i == intervals.size() - 1) {
                intervals.add(newInterval);
                break;
            }
        }
        if (position != -1) {
            int index = position + 1;
            Interval interval = intervals.get(position);
            while (true) {
                if (index >= intervals.size()) {
                    break;
                }
                Interval lastInterval = intervals.get(index);
                if (lastInterval.start > interval.end) {
                    break;
                }
                interval.end = Math.max(interval.end, lastInterval.end);
                intervals.remove(lastInterval);
            }
        }
        return intervals;
    }
}
```

## 58. Length of Last Word
题目：给出一个字符串，包含字符和空格，求最后一个单词的字数。
```
Given s = "Hello World",
return 5.
```
反向遍历，返回碰到空格时碰到的个数。
```java
public class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int total = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (total == 0) continue;
                else return total;
            }
            total++;
        }
        return total;
    }
}
```

## 59. Spiral Matrix II
题目：类似题目54，以螺旋的顺序将n*n的数字填入二维数组中。
```
Given n = 3,
You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```
```java
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int k = 1;
        int startM = 0, startN = 0;
        int m = n;
        while (m > 0 && n > 0) {
            k = spiral1(startM, startN, m, n, matrix, k);
            startM++;
            m--;
            if (m <= 0 || n <= 0) return matrix;
            k = spiral2(startM, startN, m, n, matrix, k);
            n--;
            if (m <= 0 || n <= 0) return matrix;
            k = spiral3(startM, startN, m, n, matrix, k);
            m--;
            if (m <= 0 || n <= 0) return matrix;
            k = spiral4(startM, startN, m, n, matrix, k);
            startN++;
            n--;
        }
        return matrix;
    }

    public int spiral1(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = startN; k < n + startN; k++) {
            matrix[startM][k] = s++;
        }
        return s;
    }

    public int spiral2(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = startM; k < m + startM; k++) {
            matrix[k][n + startN - 1] = s++;
        }
        return s;
    }

    public int spiral3(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = startN + n - 1; k >= startN; k--) {
            matrix[startM + m - 1][k] = s++;
        }
        return s;
    }

    public int spiral4(int startM, int startN, int m, int n, int[][] matrix, int s) {
        for (int k = m + startM - 1; k >= startM; k--) {
            matrix[k][startN] = s++;
        }
        return s;
    }
}
```

## 60. Permutation Sequence
题目：求出n个数字的第k个排列组合，规律
```
a1 = k / (n - 1)!
k1 = k

a2 = k1 / (n - 2)!
k2 = k1 % (n - 2)!
...

an-1 = kn-2 / 1!
kn-1 = kn-2 / 1!

an = kn-1 / 0!
kn = kn-1 % 0!

```

```java
public class Solution {
    public String getPermutation(int n, int k) {
        int data[] = new int[n + 1];
        boolean visited[] = new boolean[n + 1];
        data[0] = data[1] = 1;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            data[i] = data[i - 1] * (i);
            list.add(i);
        }
        String result = "";
        k--;
        for (int i = n - 1; i >= 0; i--) {
            int cur = k / data[i];
            result += list.remove(cur);
            k = k % data[i];
        }
        return result;
    }
}
```

## 61. Rotate List
题目：倒着数k个node，从那开始到结尾和之前那部分对调，那个例子就是，4->5拿前面来，1->2->3拿后面去。
几个特殊：
k是可以大于整个list的长度的，所以这时要对k对len取模
如果取模之后得0，相当于不用rotate，直接返回

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int count = 1;
        ListNode temp = head;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            count++;
        }
        k = k % count;
        int index = count - k;
        if (index <= 0) {
            return head;
        }
        index--;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        last.next = head;
        head = temp.next;
        temp.next = null;
        return head;
    }
}
```

## 62. Unique Paths
题目：一个`mxn`的空间内，求左上角到右下角的路线共有多少条。
![route](https://leetcode.com/static/images/problemset/robot_maze.png)

一道很简单的动态规划题，当前的路线数
> a[m][n] = a[m-1][n]+a[m][n-1]

```java
public class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (m == 1 || n == 1) return 1;
        int[][] a = new int[m][n];
        Arrays.fill(a[0], 1);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = a[i - 1][j] + a[i][j - 1];
                }
            }
        }
        return a[m - 1][n - 1];
    }
}
```

## 63. Unique Paths II
题目：题意跟上一题一样，只不过在mxn的空间里面加上障碍物妨碍路线。

思路跟上一题一样，只不过碰到障碍物时设置当前的位置的可到达路线为0

```java
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        if (n == 0) return 0;
        int a[][] = new int[m][n];
        if (obstacleGrid[0][0] > 0) {
            a[0][0] = 0;
        } else {
            a[0][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] > 0) {
                a[0][i] = 0;
            } else {
                a[0][i] = a[0][i - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] > 0) {
                a[i][0] = 0;
            } else {
                a[i][0] = a[i - 1][0];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] > 0) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = a[i - 1][j] + a[i][j - 1];
                }
            }
        }
        return a[m - 1][n - 1];
    }
}
```

## 64. Minimum Path Sum
题目：题目与62相似，不过这次不是求路径的可达数而是求最小路径值。

同样是动态规划，数组维护的到达每个位置的最小值。

```java
public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        int a[][] = new int[m][n];
        a[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            a[i][0] = a[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            a[0][i] = a[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[i][j] = Math.min(a[i - 1][j], a[i][j - 1]) + grid[i][j];
            }
        }
        return a[m - 1][n - 1];
    }
}
```

## 65. Valid Number
题目：给出一个字符串，判断是否是有效的数值
```
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true

// 更详细的测试案例，源自讨论区
test(1, "123", true);
test(2, " 123 ", true);
test(3, "0", true);
test(4, "0123", true);  //Cannot agree
test(5, "00", true);  //Cannot agree
test(6, "-10", true);
test(7, "-0", true);
test(8, "123.5", true);
test(9, "123.000000", true);
test(10, "-500.777", true);
test(11, "0.0000001", true);
test(12, "0.00000", true);
test(13, "0.", true);  //Cannot be more disagree!!!
test(14, "00.5", true);  //Strongly cannot agree
test(15, "123e1", true);
test(16, "1.23e10", true);
test(17, "0.5e-10", true);
test(18, "1.0e4.5", false);
test(19, "0.5e04", true);
test(20, "12 3", false);
test(21, "1a3", false);
test(22, "", false);
test(23, "     ", false);
test(24, null, false);
test(25, ".1", true); //Ok, if you say so
test(26, ".", false);
test(27, "2e0", true);  //Really?!
test(28, "+.8", true);
test(29, " 005047e+6", true);  //Damn = =|||
```

可以借助 `Double.parseDouble()` 方法try catch获取true或false,也可以使用正则表达式。
 ```java
 //Pattern.matches("(\\+|-)?(\\d+(\\.\\d*)?|\\.\\d+)(e(\\+|-)?\\d+)?", s);

 public class Solution {
     public boolean isNumber(String s) {
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
 ```

 ## 66. Plus One
 题目：给出一组数组，每个位置放置一个非0数字，将数组作为一个数值考虑，求将其+1后的数值。

 一道很简单的问题，主要考虑的还是临界点的进位问题。

 ```java
 public class Solution {
     public int[] plusOne(int[] digits) {
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
 ```

## 67. Add Binary
题目：两个二进制字符串相加，求和。
```
a = "11"
b = "1"
Return "100".
```
一道很简单的后序相加问题。
```java
public class Solution {
    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        if (m == 0) return b;
        if (n == 0) return a;
        m = m - 1;
        n = n - 1;
        String k = "";
        int temp = 0;
        while (m >= 0 || n >= 0) {
            int aa = m >= 0 ? a.charAt(m) - '0' : 0;
            int bb = n >= 0 ? b.charAt(n) - '0' : 0;
            int r = aa + bb + temp;
            if (r >= 2) {
                r = r - 2;
                temp = 1;
            } else {
                temp = 0;
            }
            k = r + k;
            m--;
            n--;
        }
        if (temp == 1) {
            k = temp + k;
        }
        return k;
    }
}
```

## 68. Text Justification
题目：分行。给出一组词，按照给出的长度给这些词分行，词与词之间用一个空格或多个分开且词紧靠保证左右两边，最后一行除外。
```
For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

```

先求出每行能够容纳多少词，再在每个词之间填充空格保证对齐。

```java
public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> lines = new ArrayList<String>();

        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > L) break;
                count += words[last].length() + 1;
                last++;
            }

            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < L; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (L - count) / diff;
                int r = (L - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }


        return lines;
    }
}
```

## 69. Sqrt(x)
题目：整数开根

牛顿迭代法：`xi+1= (xi + n/xi) / 2`

```java
public class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last)
        {
            last = res;
            res = (res + x / res) / 2;
        }
        return (int)res;
    }
}
```

## 70. Climbing Stairs
题目：爬楼梯，每次可以向上爬1或2步，求爬到第n层楼梯有多少种走法。

一个斐波那数的应用

```java
public class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2) return n;
        int cs[] = new int[n+1];
        cs[0] = 0;
        cs[1] = 1;
        cs[2] = 2;
        for (int i = 3; i <= n; i++) {
            cs[i] = cs[i - 1] + cs[i - 2];
        }
        return cs[n];
    }
}
```