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
        // 存放每个身高对应的第二个属性，即其之前的人数
        Map<Integer, List<Integer>> hpmap = new HashMap<>();
        for (int[] p : people) {
            if (hpmap.get(p[0]) == null)
                hpmap.put(p[0], new ArrayList<>(Collections.singletonList(p[1])));
            else
                hpmap.get(p[0]).add(p[1]);
        }
        // 从高到低开始插入返回列表
        List<Integer> heights = new ArrayList<>(hpmap.keySet());
        heights.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        List<List<Integer>> ret = new LinkedList<>();
        for (Integer curHeight : heights) {
            // 获取当前身高所有人的位置
            // 由于从最高身高开始，比起高或一样高的人只在这一组里以及之前已插入的人中
            /*
             * 例如从7开始，7，0和 7，1 分别在下标 0，1 上
             * 7，7
             *
             * 再到6，如果有 6，0，说明其之前没有7，直接放在下标 0 上
             * 6，7，7
             * 再判断到 6，2，说明其之前有两个 6 或 7，放在下标 2 即可
             * 6，7，6，7
             *
             * 依此类推，只要按照身高递减、第二属性递增的顺序插入，可以直接将第二属性作为下标
             * 因为 List 按照 add(index, Object) 的方式插入后，之后的元素都将依次后退。
             */
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
