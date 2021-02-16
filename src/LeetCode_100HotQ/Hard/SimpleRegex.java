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

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
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

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /*
     * 双队列分别扫描s，p
     * xyz , xy. , xy* , x.y , x.. , x.* , x*y , x*.
     * .xy ,
     *
     * 简化方案：
     *  .*x -> .*
     *  x*x -> x*
     */
    public static boolean isMatchM(String s, String p) {
        // spec cond
        if (s == null && p == null) return true;                            // null matches null ?
        else if (s == null) return p.length() == 2 && p.charAt(1) == '*';   // x* and .* matches null
        else if (p == null) return false;                                   // null matches nothing else
        else if (p.equals(".*")) return true;                               // .* matches everything

        else {
            if (p.charAt(0) == '*') return false;                           // regex dont start with *

            char[] sca = s.toCharArray();
            char[] pca = p.toCharArray();
            int ps = 0, pp = 0;

            // traverse pattern
            while (pp < p.length() - 1 && ps < s.length()) {
                if (pca[pp] == '.') {

                } else if (pca[pp] == '.') {

                } else {
                    // x*
                    if (pca[pp + 1] == '*'){

                    }
                }
            }
        }
        return false;
    }
}
