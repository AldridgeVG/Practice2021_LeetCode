package LeetCode_100HotQ.Middle;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母
 *
 * 2 - ABC
 * 3 - DEF
 * 4 - GHI
 * 5 - JKL
 * 6 - MNO
 * 7 - PQRS
 * 8 - TUV
 * 9 - WXYZ
 */
public class KeyboardLetterCombination {
    public static void main(String[] args) {
        System.out.println(getCombinations("23"));
    }

    public static List<String> getCombinations(String digits) {
        Map<Character, List<String>> charMap = new HashMap<>();
        Set<String> temp = new HashSet<>();
        Set<String> rmv = new HashSet<>();
        Set<String> add = new HashSet<>();

        //init map
        charMap.put('2', Arrays.asList("a", "b", "c"));
        charMap.put('3', Arrays.asList("d", "e", "f"));
        charMap.put('4', Arrays.asList("g", "h", "i"));
        charMap.put('5', Arrays.asList("j", "k", "l"));
        charMap.put('6', Arrays.asList("m", "n", "o"));
        charMap.put('7', Arrays.asList("p", "q", "r", "s"));
        charMap.put('8', Arrays.asList("t", "u", "v"));
        charMap.put('9', Arrays.asList("w", "x", "y", "z"));

        for (int i = 0; i <= digits.length(); i++) {
            // end
            if (i == digits.length()) {
                return new ArrayList<>(temp);
            }

            // spec
            if (digits.charAt(i) == '1') continue;

            // first letter
            if (temp.isEmpty()) {
                temp.addAll(charMap.get(digits.charAt(i)));
            }

            // add letter
            else {
                for (String cur : temp) {
                    for (String nxt : charMap.get(digits.charAt(i))) {
                        add.add(cur + nxt);
                    }
                    rmv.add(cur);
                }
            }

            // add and delete
            temp.addAll(add);
            temp.removeAll(rmv);
            add.clear();
            rmv.clear();
        }
        return null;
    }
}
