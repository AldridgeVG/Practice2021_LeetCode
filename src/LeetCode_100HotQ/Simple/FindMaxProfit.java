package LeetCode_100HotQ.Simple;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
 */
public class FindMaxProfit {
    public static void main(String[] args) {
        System.out.println(maxProfitQ(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;

        int maxProfit = 0;
        for (int in = 0; in < len - 1; in++) {
            for (int out = in + 1; out < len; out++) {
                if (prices[out] > prices[in]) maxProfit = Math.max(maxProfit, prices[out] - prices[in]);
            }
        }
        return maxProfit;
    }

    public static int maxProfitQ(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        // 队列，队头为买入点，依次入队卖出点直到遇到更小的买入点
        Deque<Integer> queue = new ArrayDeque<>();
        int maxProfit = 0;
        for (int price : prices) {
            if (queue.isEmpty()) {
                queue.offer(price);
                continue;
            }
            // 预见比队头更大的则入队，计算利润（）
            if (price > queue.peek()) {
                queue.offer(price);
                int profit = queue.getLast() - queue.getFirst();
                maxProfit = Math.max(maxProfit, profit);
            }
            // 遇见更小的作为新买入点，之后遇见高点比之前的买入点利润高
            else {
                queue.clear();
                queue.offer(price);
            }
        }
        return maxProfit;
    }
}
