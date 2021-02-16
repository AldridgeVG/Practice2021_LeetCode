package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 */
public class CombinationSum {
    // List to store results
    private static List<List<Integer>> ret = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
    }

    // 回溯算法
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        lookForSum(path, candidates, 0, target);
        return ret;
    }

    public static void lookForSum(List<Integer> prePath, int[] candidates, int level, int nTarget) {
        // end
        if (level == candidates.length) return;

        List<Integer> tmp = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();
        int curElem = candidates[level];
        int maxNum = nTarget / curElem;
        // cant add this number or sum will be bigger than target
        if (maxNum == 0) return;
        for (int i = 0; i <= maxNum; i++) {
            curPath.addAll(prePath);
            int nxtTarget = nTarget - i * curElem;
            // found
            if (nxtTarget == 0) {
                for (int j = 0; j < i; j++) {
                    curPath.add(curElem);
                }
                ret.add(curPath);
                break;
            }
            // add to Path
            for (int j = 0; j < i; j++) {
                curPath.add(curElem);
            }
            lookForSum(curPath, candidates, level + 1, nxtTarget);
            curPath.clear();
        }
    }
}
