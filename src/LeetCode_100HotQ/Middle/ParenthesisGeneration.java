package LeetCode_100HotQ.Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParenthesisGeneration {
    public static void main(String[] args) {
        System.out.println(generate(2));
        System.out.println(generate(5));
    }

    public static List<String> generate(int n) {
        List<String> ret = new ArrayList<>();
        List<Integer> syms = new ArrayList<>();

        // spec cond
        if (n == 0) return ret;
        if (n == 1) return Arrays.asList("()");

        // norm cond
        // ( [0]<=1 ( [1]<=2 ( [2]<=3
        // ( 1 ( 2 ( 0  or ( 0 ( 0 ( 3  => 120 / 003
        // n/2 left bracket, init
        int len = n / 2;
        int depth = 1;
        enumPossibility(0, 1, 0, n, n, 1, syms);
        ret = translateSym(n, syms);
        return ret;
    }

    private static void enumPossibility(int oldValue, int maxAllowed, int symbolicNum, int target, int maxDepth, int depth, List<Integer> syms) {
        for (int i = 0; i <= maxAllowed; i++) {
            if (oldValue + i > depth) continue;
            // keep looking
            if (oldValue + i < target) {
                // not to bottom
                if (depth < maxDepth) {
                    int newSN = symbolicNum + (int) Math.pow(10, depth - 1) * i;
                    enumPossibility(oldValue + i, maxAllowed + 1, newSN, target, maxDepth, depth + 1, syms);
                }
            }
            // found one and continue
            else if (oldValue + i == target) {
                if (i > 0 && depth == maxDepth) {
                    int newSN = symbolicNum + (int) Math.pow(10, depth - 1) * i;
                    syms.add(newSN);
                }
            }
            // too big, no need to go on
            else return;
        }
    }

    private static List<String> translateSym(int level, List<Integer> syms) {
        List<String> ret = new ArrayList<>();
        for (Integer curSym : syms) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= level; i++) {
                sb.append('(');
                int num = (curSym % ((int) Math.pow(10, i))) / (int) Math.pow(10, i - 1);
                for (int j = 0; j < num; j++) {
                    sb.append(')');
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }
}
