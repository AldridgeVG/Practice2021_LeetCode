package LeetCode_ForOffer.Simple;

public class Fib100 {
    public static void main(String[] args) {
        System.out.println(fib(100));
    }

    public static int fib(int n) {
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % ((int) Math.pow(10, 9) + 7);
        }
        return dp[n];
    }
}
