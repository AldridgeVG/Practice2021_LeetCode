package LeetCode_100HotQ.Simple;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 */
public class BracketMatchingCheck {

    public static void main(String[] args) {
        System.out.println(isValid("{}{[((()))][()]{}}()"));
        System.out.println(isValid("{}{((()))][()]{}()"));
    }

    public static boolean isValid(String s) {
        int len = s.length();
        Deque<Character> bdq = new ArrayDeque<>();
        List<Character> leftBracket = Arrays.asList('(', '[', '{');
        List<Character> rightBracket = Arrays.asList(')', ']', '}');
        // spec cond
        if (len < 2) return false;
        if (s.charAt(0) == ']' || s.charAt(0) == '}' || s.charAt(0) == ')') return false;
        // norm cond
        for (int i = 0; i < len; i++) {
            // left B push
            if (leftBracket.contains(s.charAt(i)))
                bdq.push(s.charAt(i));
            // right B pop if not Empty
            else if (rightBracket.contains(s.charAt(i))) {
                if (bdq.isEmpty())
                    return false;
                else {
                    switch (bdq.pop()) {
                        case '{':
                            if (s.charAt(i) != '}') return false;
                            else continue;
                        case '[':
                            if (s.charAt(i) != ']') return false;
                            else continue;
                        case '(':
                            if (s.charAt(i) != ')') return false;
                            else continue;
                        default:
                            return false;
                    }
                }
            }
        }
        // if Stack emptied, true
        return bdq.isEmpty();
    }
}
