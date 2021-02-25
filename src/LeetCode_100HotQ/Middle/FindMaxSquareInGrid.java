package LeetCode_100HotQ.Middle;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 */
public class FindMaxSquareInGrid {
    public static void main(String[] args) {
        System.out.println(maximalSquare(new char[][]{
                {'0', '0', '0', '1'},
                {'1', '1', '0', '1'},
                {'1', '1', '1', '1'},
                {'0', '1', '1', '1'},
                {'0', '1', '1', '1'}
        }));
    }

    public static int maximalSquare(char[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        if (height == 0 || width == 0) return 0;
        int maxSide = 0;

        // dp[i][j] signifies the max ---SIDE LENGTH--- of square whose SE corner is (i,j)
        int[][] dp = new int[height][width];
        // init dp matrix of edge
        for (int i = 0; i < width; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxSide = 1;
            }
        }
        for (int i = 1; i < height; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxSide = 1;
            }
        }

        // dp
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                // if '0' no need to calc
                if (matrix[i][j] == '0') continue;
                // if this is '1'
                int preSide = dp[i - 1][j - 1];
                // 确定这个东南角的正方形将以 (i-1,j-1) 为东南角的正方形包起来的范围
                // 从当前位置开始寻找南边的长度，最大长度为 dp[i-1][j-1] + 1
                int nsSide = 1;
                for (int sSide = j - 1; sSide >= j - preSide; sSide--) {
                    if (matrix[i][sSide] == '0') {
                        break;
                    }
                    nsSide++;
                }
                // 同理寻找东边的长度
                int neSide = 1;
                for (int eSide = i - 1; eSide >= i - preSide; eSide--) {
                    if (matrix[eSide][j] == '0') {
                        break;
                    }
                    neSide++;
                }
                // 东边和南边的最小值为当前长度
                /*
                 * 例：
                 *  0,1,1,0,0
                 *  0,1,1,1,0
                 *  0,0,1,1,0
                 *
                 *  的情况下，dp[1][2] = 2
                 *  但是 dp[2][3] != 3, 因为东北角和西南角可能存在缺失，实际上是东边上和南边上都有可能缺失 1
                 *  如：
                 *   1, 1, 1, 1
                 *   1, 1, 1, 0  (2)
                 *   1, 1, 1, 1   |
                 *   0, 1, 1, 1   |
                 *   (3)———————
                 *
                 *   的情况下，dp[2][2] = 3
                 *   也就是说 dp[3][3] 最大为 4，即以全 1 的东边和南边将3，3正方形包起来
                 *   但是东边值为 2， 南边值为 3， 所以 dp[3][3] = 2
                 */
                dp[i][j] = Math.min(nsSide, neSide);
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        return maxSide * maxSide;
    }
}
