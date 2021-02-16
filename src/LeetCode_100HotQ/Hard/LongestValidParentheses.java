package LeetCode_100HotQ.Hard;

public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(longestValidParenthesesDP2D("(()()))()"));
        System.out.println(longestValidParenthesesDP1D("(()()))()"));
    }

    /**
     * O(n^2) and O(n^2)  --  my answer mem out of limit
     * dp[i][j] 代表 dp[i..j] 需要多少个右括号来保证有效
     * 正数 ： 所需右括号数量
     * -1  ： 自身和其后续都不可能有效
     * 0   ： S[i..j]是有效串
     */
    public static int longestValidParenthesesDP2D(String s) {
        int len = s.length();
        int maxLen = 0;
        // spec cond
        if (len < 2) return maxLen;

        // norm cond, dp chart, positive dp[i][j] means from i to j is a bracket needs dp[i][j] right bracket, and 0 means valid, -1 means no chance to be valid anymore
        short[][] dp = new short[len][len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') dp[i][i] = 1;    // need 1 right bracket
            else dp[i][i] = -1;                     // -1 means invalid
        }

        // dynamic programme
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                // former invalid
                if (dp[i][j - 1] == -1) {
                    dp[i][j] = -1;
                }
                // former can be valid
                else {
                    // left bracket
                    if (s.charAt(j) == '(') dp[i][j] = (short) (dp[i][j - 1] + 1);
                        // right bracket
                    else {
                        dp[i][j] = (short) (dp[i][j - 1] - 1);                                // match one l bracket
                        if (dp[i][j] == 0) maxLen = Math.max(maxLen, j - i + 1);    // find valid
                    }
                }
            }
        }
        return maxLen;
    }

    /**
     * O(n) and O(n)  --  reference answer
     *
     * 我们定义 dp[i]dp[i] 表示以下标 ii 字符结尾的最长有效括号的长度。我们将 dp 数组全部初始化为 0 。显然有效的子串一定以 ‘)’ 结尾，因此我们可以知道以 ‘(’ 结尾的子串对应的 dp 值必定为 0 ，我们只需要求解 ')’ 在 dp 数组中对应位置的值。
     *
     * 我们从前往后遍历字符串求解 dp 值，我们每两个字符检查一次：
     *
     * s[i]=‘)’且 s[i−1]=‘(’，也就是字符串形如 “……()”，我们可以推出：
     * dp[i]=dp[i-2]+2
     *
     * s[i]=‘)’ 且 s[i−1]=‘)’，也就是字符串形如 “……))”，我们可以推出：
     * 如果 s[i−dp[i−1]−1]=‘(’，
     * 那么 dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2
     *
     * 我们考虑如果倒数第二个 ‘)’ 是一个有效子字符串的一部分（记作 sub_s），
     * 对于最后一个 ‘)’ ，如果它是一个更长子字符串的一部分，那么它一定有一个对应的‘(’ ，且它的位置在倒数第二个 \‘)’ 所在的有效子字符串的前面（也就是 sub_s的前面）。因此，如果子字符串 sub_s 的前面恰好是‘(’ ，那么我们就用 2 加上 sub_s的长度（dp[i−1]）去更新 dp[i]。
     * 同时，我们也会把有效子串 “(sub_s)” 之前的有效子串的长度也加上，也就是再加上 dp[i-dp[i-1]-2]dp[i−dp[i−1]−2]。
     *
     * 最后的答案即为 dp 数组中的最大值。
     */
    public static int longestValidParenthesesDP1D(String s) {
        int len = s.length();
        int maxLen = 0;
        // spec cond
        if (len < 2) return maxLen;

        // norm cond
        // new int[] filled with 0, so dp[0] is 0
        int[] dp = new int[len];
        // start with 1, 1st letter cant be valid
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                // add a pair of brackets: .....()
                if (s.charAt(i - 1) == '(')
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                // meet 2 adjacent right bracket
                // a form like:
                //         |len:dp[i-dp[i-1]-2]|                         |len:dp[i-1]|
                // ......  (...................)             (           (...........)      )  ......
                //                       i-dp[i-1]-2    i-dp[i-1]-1                 i-1     i
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(')
                    dp[i] = dp[i - 1] +
                            ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) +
                            2;
                // update maxLen
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
}
