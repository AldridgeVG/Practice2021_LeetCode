package LeetCode_100HotQ.Hard;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class MinimumDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "rose"));
    }

    public static int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();

        // dp[i][j]表示 word1 的前i  个字母转换成 word2 的前 j 个字母所使用的最少操作。
        int[][] dp = new int[len1 + 1][len2 + 1];

        // dp[i][0]表示 word1 的前 i 个字符全部删除转化为 word2 的 0 个字符
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        // dp[0][j]表示 word1 为空时，添加 j 个字符转化为 word2 的前 j 个字符
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        /*
         * 若当前字母相同，则dp[i][j] = dp[i - 1][j - 1];
         *
         * 否则取增删替三个操作的最小值 + 1， 即:
         *
         * dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1。
         */
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 下个字符出现匹配的情况
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 下个字符不必配，取 增、删、改 三种操作之前步数的的最小值
                // dp[i-1][j] + 1   -> dp[i][j] ： word1 的前i-1个字符本可匹配 word2 的前j个，多一步为 word1 的前i个字符删除一个字符（变为前i-1个）以像之前一样匹配前j个字符
                // dp[i][j-1] + 1   -> dp[i][j] ： word1 的前i个字符本可匹配 word2 的前j-1个，多一步为 word1 的前i个字符后面增加一个字符以匹配 word2 的前j个字符
                // dp[i-1][j-1] + 1 -> dp[i][j] ： word1 的前i-1个字符本可匹配 word2 的前j-1个，多一步为 word1 的第i个因为不匹配 word2 的第j个，需要修改该字符使 word1 的前i个与 word2 的前j个匹配
                else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
