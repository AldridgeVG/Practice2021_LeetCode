package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 */
public class AllCombination {
    private static List<List<Integer>> ret = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(combine(new int[]{1, 2, 3, 4}));
    }

    public static List<List<Integer>> combine(int[] nums) {
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        combineLeft(numList, new ArrayList<>());
        return ret;
    }

    public static void combineLeft(List<Integer> numsRemain, List<Integer> curCombination) {
        for (Integer curElem : numsRemain) {
            List<Integer> listCopy = new ArrayList<>(numsRemain);
            List<Integer> combCopy = new ArrayList<>(curCombination);
            combCopy.add(curElem);
            listCopy.remove(curElem);
            if (listCopy.size() == 0) {
                ret.add(combCopy);
                break;
            }
            combineLeft(listCopy, combCopy);
        }
    }
}
