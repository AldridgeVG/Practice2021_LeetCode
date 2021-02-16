package LeetCode_100HotQ.Middle;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class RotateMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[][] data = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = length * i + (j + 1);
            }
        }
        rotate(data);
        for(int[] cur:data) {
            System.out.println(Arrays.toString(cur));
        }
    }

    public static void rotate(int[][] matrix) {
        int sideLength = matrix.length;
        // spec cond
        if (sideLength <= 1) return;

        // if 3 -> (1,1) ; if 4 -> (1.5,1.5)
        int centreLim = (sideLength - 1) / 2;
        float centralOrd = (sideLength - 1) / 2.0f;
        // rotate NE corner of the matrix
        int[][] ordinates = new int[4][2];
        // even length
        if (sideLength % 2 == 0) {
            for (int x = 0; x <= centreLim; x++) {
                for (int y = 0; y <= centreLim; y++) {
                    ordinates[0] = new int[]{x, y};
                    ordinates[2] = new int[]{(int) (2 * centralOrd - x), (int) (2 * centralOrd - y)}; // 对称点坐标
                    ordinates[1] = new int[]{(int) (2 * centralOrd - y), x};
                    ordinates[3] = new int[]{y, (int) (2 * centralOrd - x)};
                    // one round of rotate
                    for (int i = 0; i < 3; i++) {
                        swap(matrix, ordinates[i][0], ordinates[i][1], ordinates[i + 1][0], ordinates[i + 1][1]);
                    }
                }
            }
        }
        // odd length
        else {
            for (int x = 0; x <= centreLim; x++) {
                for (int y = 0; y < centreLim; y++) {
                    ordinates[0] = new int[]{x, y};
                    ordinates[2] = new int[]{(int) (2 * centralOrd - x), (int) (2 * centralOrd - y)}; // 对称点坐标
                    ordinates[1] = new int[]{(int) (2 * centralOrd - y), x};
                    ordinates[3] = new int[]{y, (int) (2 * centralOrd - x)};
                    // one round of rotate
                    for (int i = 0; i < 3; i++) {
                        swap(matrix, ordinates[i][0], ordinates[i][1], ordinates[i + 1][0], ordinates[i + 1][1]);
                    }
                }
            }
        }
    }

    public static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }
}
