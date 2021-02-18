package LeetCode_100HotQ.Middle;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * e.g. board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 */
public class FindWordInGrids {
    public static void main(String[] args) {

    }

    public static boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 从每个可能的起始位置开始找
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    // 搜索的四个方向
    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * check(i,j,k) 判断以网格的 (i,j) 位置出发，能否搜索到单词 word[k..]，其中 word[k..] 表示字符串 word 从第 k 个字符开始的后缀子串
     * <p>
     * 为了防止重复遍历相同的位置，需要额外维护一个与 board 等大的 visited 数组，用于标识每个位置是否被访问过。每次遍历相邻位置时，需要跳过已经被访问的位置
     */
    public static boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        // 当前字符不匹配
        if (board[i][j] != s.charAt(k)) return false;
        // 当前字符匹配且已经完全匹配
        else if (k == s.length() - 1) return true;

        // 需要继续匹配
        visited[i][j] = true;
        boolean res = false;
        int imax = board.length;
        int jmax = board[0].length;
        for (int[] dir : directions) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            // 如果移动后的坐标合法，且下一个位置没有被访问过
            if (ni < imax && ni >= 0 && nj >= 0 && nj < jmax && !visited[ni][nj]) {
                // 找到后立即结束
                if (check(board, visited, ni, nj, s, k + 1)) {
                    res = true;
                    break;
                }
            }
        }
        // 当前位置判断结束后，如果没找到要回退一步，相当于没来过这个位置
        visited[i][j] = false;
        return res;
    }
}
