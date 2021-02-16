package LeetCode_100HotQ.Middle;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 */
public class FindAnagrams {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"ate", "eat", "tea", "ant", "tan", "eee", "eee", "rtm", "rmt"}));
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{"ddddddddddg", "dgggggggggg"}));
        System.out.println(groupAnagramsUsingPrime(new String[]{"ddddddddddg", "dgggggggggg"}));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        // spec cond
        if (strs.length == 0) return ret;
        if (strs.length == 1) {
            ret.add(Arrays.asList(strs));
            return ret;
        }

        // norm cond
        List<String> strArr = new ArrayList<>();
        List<String> curComb = new ArrayList<>();
        List<Character> sampleCharSet = new ArrayList<>();
        List<Character> objectCharSet = new ArrayList<>();
        strArr.addAll(Arrays.asList(strs));
        while (!strArr.isEmpty()) {
            // set sample
            curComb.add(strArr.get(0));
            for (char curChar : strArr.get(0).toCharArray()) {
                sampleCharSet.add(curChar);
            }
            // compare one by one
            for (int i = 1; i < strArr.size(); i++) {
                for (char curChar : strArr.get(i).toCharArray()) {
                    objectCharSet.add(curChar);
                }
                if (compareLists(sampleCharSet, objectCharSet)) {
                    curComb.add(strArr.get(i));
                }
                objectCharSet.clear();
            }
            // add to ret and remove from list
            ret.add(new ArrayList<>(curComb));      // COPY
            strArr.removeAll(curComb);
            curComb.clear();
            sampleCharSet.clear();
        }
        return ret;
    }

    public static boolean compareLists(List<Character> l1, List<Character> l2) {
        // first ensure elements are the same
        if (!l1.containsAll(l2) || !l2.containsAll(l1)) return false;

        //then ensure the number of each elem
        Map<Character, Integer> charMap = new HashMap<>();
        for (Character c : l1) {
            if (charMap.containsKey(c))
                charMap.put(c, charMap.get(c) + 1);
            else
                charMap.put(c, 1);
        }
        for (Character c : l2) {
            if (charMap.containsKey(c))
                charMap.put(c, charMap.get(c) - 1);
            else
                return false;
        }
        for (Integer i : charMap.values()) {
            if (i != 0) return false;
        }
        return true;
    }

    /**
     * 算术基本定理解法
     *
     * 任何一个大于1的自然数N，如果N不为质数，那么N可以唯一分解成有限个质数的乘积
     * [a, z]Unicode编码 - 97=[0, 25] 对应26个质数。每字母代表质数的乘积表示字符串
     * 乘法交换律忽略字母顺序。算术基本定理保证不同质数 或 相同质数不同个数，乘积唯一
     */
    public static List<List<String>> groupAnagramsUsingPrime(String[] strs) {
        // represents 26 character
        int[] z = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        // key represents the result of certain chars' combination, using the value above, product of multiplication will be unique
        HashMap<Long, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            long sum = 1;
            for (char curChar : chars) {
                // unicode of each character (char-97) represents their value's position in z array
                sum = sum * z[curChar - 97];
            }
            // find anagrams so update certain list
            if (hashMap.containsKey(sum)) {
                List<String> list = hashMap.get(sum);
                list.add(str);
                hashMap.put(sum, list);
            }
            // find new combination so add a new list
            else {
                List<String> list = new ArrayList<>();
                list.add(str);
                hashMap.put(sum, list);
            }
        }
        return new ArrayList<>(hashMap.values());
    }
}
