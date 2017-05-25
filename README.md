
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