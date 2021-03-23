package LeetCode_100HotQ.Middle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 */
public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString2Stack("abc4[a]3[c]12[x]yy"));
    }

    // 未考虑括号递归
    public static String decodeString(String s) {
        int len = s.length();
        // spec cond
        if (len <= 3) return s;

        // norm cond
        int left = 0, right = 0;
        int times;
        String content;
        StringBuilder sb = new StringBuilder();
        while (right < len) {
            char cur = s.charAt(left);
            // a-zA-Z
            if (('a' <= cur && cur <= 'z') || ('A' <= cur && cur <= 'Z')) {
                sb.append(cur);
            }

            // number, dont start with 0
            else if ('1' <= cur && cur <= '9') {
                // get repeat time
                do {
                    right++;
                } while (s.charAt(right) != '[');
                times = Integer.parseInt(s.substring(left, right));

                // get repeat content, start with letter after [
                left = right + 1;
                right++;
                do {
                    right++;
                } while (s.charAt(right) != ']');
                content = s.substring(left, right);

                // append decoded content
                for (int i = 0; i < times; i++) {
                    sb.append(content);
                }
            }

            // illegal
            else {
                return null;
            }

            // next
            right++;
            left = right;
        }
        return sb.toString();
    }

    /**
     * 双栈实现，字母栈和数字栈
     * <p>
     * StringBuilder 和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）。
     * <p>
     * 由于 StringBuilder 相较于 StringBuffer 有速度优势，所以多数情况下建议使用 StringBuilder 类。然而在应用程序要求线程安全的情况下，则必须使用 StringBuffer 类。
     */
    public static String decodeString2Stack(String s) {
        StringBuilder ans = new StringBuilder();
        Deque<Integer> multiStack = new ArrayDeque<>();
        Deque<String> ansStack = new ArrayDeque<>();

        int multi = 0;
        for (char c : s.toCharArray()) {
            // 直到获取全部数字
            if (Character.isDigit(c)) multi = multi * 10 + (c - '0');

            // 遇见左括号，之前的字符串进栈，重复次数进栈，ans存放该括号中的重复内容
            else if (c == '[') {
                ansStack.push(ans.toString());
                multiStack.push(multi);
                ans = new StringBuilder();
                multi = 0;
            }

            // 独立字母直接拼在ans上
            else if (Character.isAlphabetic(c)) {
                ans.append(c);
            }

            // 遇到右括号，开始重复拼接ans的内容
            else {
                StringBuilder ansTmp = new StringBuilder(ansStack.pop());
                int tmp = multiStack.pop();
                for (int i = 0; i < tmp; i++) ansTmp.append(ans);
                ans = ansTmp;
            }
        }
        return ans.toString();
    }
}
