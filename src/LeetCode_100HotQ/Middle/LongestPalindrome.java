package LeetCode_100HotQ.Middle;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        //System.out.println(getLP("abb"));
        //System.out.println(getLP("ccc"));
        System.out.println(getLP("aaaa"));
        //System.out.println(getLP("abba"));
        //System.out.println(getLP("aabaa"));
        //System.out.println(getLP("caaaabbbaac"));

        System.out.println(getLP_DP("aabaaa"));
    }

    public static String getLP(String s) {
        int len = s.length();

        // spec cond
        if (len <= 1) {
            return s;
        }
        if (len == 2) {
            if (s.charAt(0) == s.charAt(1)) return s;
            else return s.substring(0, 1);
        }

        // var dec
        int maxLengtho = 0;
        int maxLengthe = 0;
        int poso = 0;
        int pose = 0;

        // norm odd (head 0 and tail len-1 is the same)
        for (int i = 0; i < len - 1; i++) {
            int tmpLength = 1;
            // l len < r len
            if (i < len / 2) {
                for (int j = 0; j <= i - 0; j++) {
                    if (s.charAt(i - j) == s.charAt(i + j)) tmpLength += (j == 0 ? 0 : 2);
                    else break;
                }
            }
            // l len > r len
            else {
                for (int j = 0; j <= len - 1 - i; j++) {
                    if (s.charAt(i - j) == s.charAt(i + j)) tmpLength += (j == 0 ? 0 : 2);
                    else break;
                }
            }
            // update length and pos
            if (maxLengtho <= tmpLength) {
                poso = i;
                maxLengtho = tmpLength;
            }
        }

        // norm even
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                int tmpLength = 2;
                if (i < len / 2) {
                    for (int j = 0; j <= i - 0; j++) {
                        if (s.charAt(i - j) == s.charAt(i + 1 + j)) tmpLength += (j == 0 ? 0 : 2);
                        else {
                            // update length and pos
                            if (maxLengthe <= tmpLength) {
                                pose = i;
                                maxLengthe = tmpLength;
                            }
                            break;
                        }
                        // update length and pos
                        if (maxLengthe <= tmpLength) {
                            pose = i;
                            maxLengthe = tmpLength;
                        }
                    }
                } else {
                    for (int j = 0; j <= len - 1 - (i + 1); j++) {
                        if (s.charAt(i - j) == s.charAt(i + 1 + j)) tmpLength += (j == 0 ? 0 : 2);
                        else {
                            // update length and pos
                            if (maxLengthe <= tmpLength) {
                                pose = i;
                                maxLengthe = tmpLength;
                            }
                            break;
                        }
                        // update length and pos
                        if (maxLengthe <= tmpLength) {
                            pose = i;
                            maxLengthe = tmpLength;
                        }
                    }
                }
            }
        }

        // judge even/ odd
        if (maxLengtho > maxLengthe) {
            return s.substring(poso - maxLengtho / 2, poso + maxLengtho / 2 + 1);
        } else {
            return s.substring(pose - (maxLengthe / 2 - 1), pose + 1 + (maxLengthe / 2 - 1) + 1);
        }
    }

    public static String getLP_DP(String s) {
        int len = s.length();
        //spec cond
        if (len == 1) return s;
        if (len == 2) {
            if (s.charAt(0) == s.charAt(1)) return s;
            else return s.substring(0, 1);
        }

        // dp table, dpt[i][j] is 1 means s[i...j] is palindrome
        int[][] dpt = new int[len][len];

        // 1. initiation, 1 single character / 2 same adjacent char is always palindrome
        for (int i = 0; i < len; i++) {
            dpt[i][i] = 1;
        }
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) dpt[i][i + 1] = 1;
        }

        // 2. dynamic programming, s[i][j] = 1 when s[i+1][j-1] = 1 and charAt i equals charAt j
        int maxLen = 1;
        int pos = 0;
        // move right border
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                // calc dp
                if (s.charAt(l) != s.charAt(r))
                    dpt[l][r] = 0;
                else {
                    // length is 2 or 3, l = r, palindrome
                    if (r - l < 3) dpt[l][r] = 1;
                        // length >= 4, depends on inside
                    else dpt[l][r] = dpt[l + 1][r - 1];
                }

                // update maxLen
                if (dpt[l][r] == 1 && r - l + 1 > maxLen) {
                    maxLen = r - l + 1;
                    pos = l;
                }
            }
        }

        // 3. get substring
        return s.substring(pos, pos + maxLen);
    }
}
