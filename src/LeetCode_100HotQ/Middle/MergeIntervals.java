package LeetCode_100HotQ.Middle;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start[i], end[i]] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] res = merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}, {7, 9}});
        for (int[] interval : res) {
            System.out.println(Arrays.toString(interval));
        }

        System.out.println();
        res = merge(new int[][]{{2, 3}, {2, 2}, {3, 4}});
        for (int[] interval : res) {
            System.out.println(Arrays.toString(interval));
        }
    }

    /**
     * 先对区间排序，按照开始的大小升序排列，结束值以Map key存储
     * 依次判断是否合并即可，打标记跳过合并过的队列
     *
     * 注意：
     * 相同起点的区间，终点存储为最大的那个即可，相当于前期先做一次小合并
     */
    public static int[][] merge(int[][] intervals) {
        int length = intervals.length;
        // spec cond
        if (length < 2) return intervals;

        // norm cond
        int[] begins = new int[length];
        boolean[] flags = new boolean[length];
        int[] res = new int[2];
        List<List<Integer>> retList = new ArrayList<>();

        // get intervals' begins and ends
        Map<Integer, Integer> intervalMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            begins[i] = intervals[i][0];
            // early merge, for the intervals with same begin, only store the biggest ending value in map
            if (intervalMap.get(begins[i]) == null) {
                intervalMap.put(begins[i], intervals[i][1]);
            } else {
                intervalMap.put(begins[i], Math.max(intervalMap.get(begins[i]), intervals[i][1]));
            }
        }

        // order intervals by ascending begin value
        Arrays.sort(begins);

        for (int i = 0; i < length; i++) {
            // skip added intervals
            if (flags[i]) continue;
            // add current interval
            flags[i] = true;
            int begin = begins[i];
            int end = intervalMap.get(begins[i]);
            // begin looking from next interval ( for condition restricts that we'll only be looking if there's next
            for (int j = i + 1; j < length; j++) {
                // begin shall be settled, only update end, and flag this interval as added
                if (begins[j] <= end) {
                    end = Math.max(end, intervalMap.get(begins[j]));
                    flags[j] = true;
                }
            }
            res[0] = begin;
            res[1] = end;
            // int[] -> List<Integer>
            List<Integer> resList = Arrays.stream(res).boxed().collect(Collectors.toList());
            retList.add(resList);
        }

        // List<List<Integer>> -> int[][]
        int retLength = retList.size();
        int[][] ret = new int[retLength][2];
        for (int i = 0; i < retLength; i++) {
            ret[i][0] = retList.get(i).get(0);
            ret[i][1] = retList.get(i).get(1);
        }
        return ret;
    }
}
