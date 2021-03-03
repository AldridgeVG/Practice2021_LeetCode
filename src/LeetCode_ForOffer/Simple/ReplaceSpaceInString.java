package LeetCode_ForOffer.Simple;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class ReplaceSpaceInString {
    public static void main(String[] args) {
        System.out.println(replaceSpace("aaa1 "));
    }

    public static String replaceSpace(String s) {
        String[] div = s.split(" ", -1);
        if (div.length == 0) return s;
        StringBuilder sb = new StringBuilder(div[0]);
        for (int i = 1; i < div.length; i++) {
            sb.append("%20");
            sb.append(div[i]);
        }
        return sb.toString();
    }
}
