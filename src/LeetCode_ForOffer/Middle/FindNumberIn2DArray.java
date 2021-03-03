package LeetCode_ForOffer.Middle;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class FindNumberIn2DArray {
    public static void main(String[] args) {
        System.out.println(findNumberIn2DArray(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 14));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        // spec cond
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;

        // norm cond
        int height = matrix.length;
        int width = matrix[0].length;
        int tgtLine = 0;

        // 找到可能的目标行
        for (int i = 0; i < height; i++) {
            if (matrix[i][0] == target) return true;
            if (matrix[i][0] > target) {
                tgtLine = i - 1;
                break;
            }
        }
        // 左上角比target大，直接失败
        if (tgtLine < 0) return false;
        // 全部行开头比target小，可能在任意行， 从最后一行开始搜索
        if (tgtLine == 0) tgtLine = height - 1;

        // 在目标行顺序搜索
        for (int cur : matrix[tgtLine]) {
            if (cur == target) return true;
        }
        // 在前序行倒序搜索
        for (int i = tgtLine - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                if (matrix[i][j] == target) return true;
            }
        }
        // 均未找到
        return false;
    }
}
