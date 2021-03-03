package LeetCode_100HotQ.Middle;

import java.util.*;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 */
public class RebuildQueueByHeights {
    public static void main(String[] args) {
        int[][] ret = reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        for (int[] cur : ret)
            System.out.println(Arrays.toString(cur));
    }

    /**
     * 1. 从高的人开始插入，因为再插入比他矮的人不影响其第二个属性
     * 2. 从第二属性为 0 的人开始插入，因为可以直接放在队头
     */
    public static int[][] reconstructQueue(int[][] people) {
        Map<Integer, List<Integer>> hpmap = new HashMap<>();
        for (int[] p : people) {
            if (hpmap.get(p[0]) == null)
                hpmap.put(p[0], new ArrayList<>(Collections.singletonList(p[1])));
            else
                hpmap.get(p[0]).add(p[1]);
        }
        List<Integer> heights = new ArrayList<>(hpmap.keySet());
        heights.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        List<List<Integer>> ret = new LinkedList<>();
        for (Integer curHeight : heights) {
            List<Integer> poses = hpmap.get(curHeight);
            poses.sort(null);
            for (Integer pos : poses) {
                ret.add(pos, Arrays.asList(curHeight, pos));
            }
        }
        int[][] retArr = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            retArr[i][0] = ret.get(i).get(0);
            retArr[i][1] = ret.get(i).get(1);
        }
        return retArr;
    }
}
