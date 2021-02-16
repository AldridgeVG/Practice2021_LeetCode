package LeetCode_100HotQ.Hard;

import java.util.ArrayList;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 */
public class RainCapacity {
    public static void main(String[] args) {
        System.out.println(getCapacity(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int getCapacity(int[] height) {
        int len = height.length;

        // spec cond
        if (len < 3) return 0;

        // norm cond
        int maxSquare, pillarSquare = 0;
        int maxHeight = 0;

        // get maxHeight and pillars' square
        for (int h : height) {
            maxHeight = Math.max(maxHeight, h);
            pillarSquare += h;
        }
        maxSquare = maxHeight * len;

        // get all pos of max height
        ArrayList<Integer> maxPoses = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (height[i] == maxHeight)
                maxPoses.add(i);
        }
        int firstPos = maxPoses.get(0);
        int lastPos = maxPoses.get(maxPoses.size() - 1);

        // from left to first max pos, calc head space Square
        int formerHeight = 0;
        int leftHeadSpace = 0;
        for (int curPos = 0; curPos <= firstPos; curPos++) {
            int curHeight = height[curPos];
            if (curHeight > formerHeight) {
                // ignore if the left is flat
                leftHeadSpace += (curHeight - formerHeight) * curPos;
                formerHeight = curHeight;
            }
        }
        // from right to last max pos, calc head space Square
        formerHeight = 0;
        int rightHeadSpace = 0;
        for (int curPos = len - 1; curPos >= lastPos; curPos--) {
            int curHeight = height[curPos];
            if (curHeight > formerHeight) {
                // ignore if the right is flat
                rightHeadSpace += (curHeight - formerHeight) * (len - 1 - curPos);
                formerHeight = curHeight;
            }
        }

        // water square = maxSquare - pillarSquare - headSpaceSquare
        return maxSquare - pillarSquare - (leftHeadSpace + rightHeadSpace);
    }
}
