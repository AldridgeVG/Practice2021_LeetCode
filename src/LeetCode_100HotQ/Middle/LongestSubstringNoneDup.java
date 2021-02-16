package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubstringNoneDup {
    public static void main(String[] args) {
        System.out.println(mLengthOfLongestSubstring("acabbccc"));
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] ss = s.toCharArray();
        ArrayList list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            list.clear();
            for (int j = i; j < s.length(); j++) {
                if (!list.contains(s.charAt(j))) list.add(s.charAt(j));
                else break;
            }
            max = Math.max(max, list.size());
        }
        return max;
    }

    public static int mLengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int len = s.length();
        int ret = 0;
        int rp = -1;
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                set.remove(s.charAt(i - 1));
            }
            while (rp + 1 < len && !set.contains(s.charAt(rp + 1))) {
                set.add(s.charAt(rp + 1));
                rp++;
            }
            ret = Math.max(ret, rp + 1 - i);
        }
        return ret;
    }

    public static int hLengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}


