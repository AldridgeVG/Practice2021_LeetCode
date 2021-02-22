package LeetCode_100HotQ.Middle;

import java.util.*;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class PreRequestedCourses {
    public static void main(String[] args) {
        System.out.println(canFinishDFS(2, new int[][]{{0, 1}, {1, 0}}));
    }

    // 实际上是要求图内无环， 即寻找拓扑排序， dfs/bfs均可

    // 先后修课程其实表示图的边，edges代表以其下标 i 为起点，列表 edges[i] 内所有元素为终点的多条边
    static Map<Integer, List<Integer>> edges;
    // 记录是否访问过某个节点, 0:未访问， 1：搜索中， 2：已搜索
    static int[] visited;
    // 出现重复访问代表有环，立即返回
    static boolean valid = true;

    public static boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // 初始化边
        edges = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            edges.put(i, new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            edges.get(edge[0]).add(edge[1]);
        }
        // 初始化visit数组
        visited = new int[numCourses];
        for (int s : edges.keySet()) {
            dfs(s);
        }
        return valid;
    }

    public static void dfs(int start) {
        // not visited, visit all adjacent and set visit = 2
        if (visited[start] == 0 && valid) {
            visited[start] = 1;
            for (int nxt : edges.get(start)) {
                dfs(nxt);
            }
            visited[start] = 2;
        }
        // 搜索到当前正在搜索链上的
        else if (visited[start] == 1) {
            valid = false;
            return;
        }
        // visited = 2, do nothing
    }
}
