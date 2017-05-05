
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
```

## 11. Container With Most Water
题目：以X轴为底，给出一组高度不同的值，求其中的两个值能够容纳最多水的值。

常规思路是输入一个值然后跟前面一一比较，这样导致时间复杂度为 nlogn 。但因为值是一口气给你的，所有可以从两边开始缩进，毕竟面积取决于短板。

```java
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