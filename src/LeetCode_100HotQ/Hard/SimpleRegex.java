package LeetCode_100HotQ.Hard;

/**
 * UNSOLVED
 */

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class SimpleRegex {
    public static void main(String[] args) {
        System.out.println(isMatch("mississippi", "mis*is*i.*"));
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配, f[0][0]表示空空可以匹配
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        /*
         * 1. 如果 p 的第 j 个字符是一个小写字母，那么我们必须在 s 中匹配一个相同的小写字母
         *
         * 2. 字母 + 星号的组合在匹配的过程中，本质上只会有两种情况
         *          a. 匹配 s 末尾的一个字符，将该字符扔掉，而该组合还可以继续进行匹配
         *          b. 不匹配字符，将该组合扔掉，不再进行匹配
         *    如果按照这个角度进行思考，我们可以写出很精巧的状态转移方程:
         *      f[i][j] = f[i-1][j] or f[i][j-2] when s[i]  = p[j-1]
         *      f[i][j] = f[i][j-2]              when s[i] != p[j-1]
         *
         * 3. 在任意情况下，只要 p[j] 是 . 那么 p[j] 一定成功匹配 s 中的任意一个小写字母。
         */
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    // matches(x,y) 判断两个字符是否匹配的辅助函数。只有当 y 是 . 或者 x 和 y 本身相同时，这两个字符才会匹配
    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
