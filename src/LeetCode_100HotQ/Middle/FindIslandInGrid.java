package LeetCode_100HotQ.Middle;

import java.util.Arrays;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class FindIslandInGrid {
    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '0', '1', '0'}
        }));
    }

    // 上下左右四个方向，用于寻找相邻陆地
    private static final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // 记录岛屿个数
    private static int number = 0;

    public static int numIslands(char[][] grid) {
        // surround the sea map wih water
        char[][] extGrid = extendGrid(grid);
        int height = extGrid.length;
        int width = extGrid[0].length;

        // only land can be visit
        boolean[][] visit = new boolean[height][width];
        // start extension from every possible location
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                // find a new land, island+1 and visit all adjacent land
                if (extGrid[i][j] == '1' && !visit[i][j]) {
                    number++;
                    bfs(i, j, extGrid, visit);
                }
            }
        }
        return number;
    }

    public static void bfs(int x, int y, char[][] grid, boolean[][] visit) {
        visit[x][y] = true;
        for (int[] dir : directions) {
            if (grid[x + dir[0]][y + dir[1]] == '1' && !visit[x + dir[0]][y + dir[1]]) {
                visit[x + dir[0]][y + dir[1]] = true;
                bfs(x + dir[0], y + dir[1], grid, visit);
            }
        }
    }

    public static char[][] extendGrid(char[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        char[][] extGrid = new char[height + 2][width + 2];
        Arrays.fill(extGrid[0], 0, width + 2, '0');
        Arrays.fill(extGrid[height + 1], 0, width + 2, '0');
        for (int i = 0; i < height; i++) {
            System.arraycopy(grid[i], 0, extGrid[i + 1], 1, width);
            extGrid[i + 1][0] = '0';
            extGrid[i + 1][width + 1] = '0';
        }
        return extGrid;
    }
}
