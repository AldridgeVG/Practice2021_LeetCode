package LeetCode_100HotQ.Middle;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */
public class DesignCacheLRU {
    // 修改 LinkedHashMap 即可
    private class LRUCache extends LinkedHashMap<Integer, Integer>{
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        // 修改 LRU 方法，只要大于了我们设定的容量就进行 LRU
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(10, 1.0f, true);
        for (int i = 0; i < 11; i++) {
            map.put(i, 1);
        }
        System.out.println(map);
        map.put(5, 2);
        System.out.println(map);
        map.put(11, 1);
        System.out.println(map);
        for (int i = 100; i < 111; i++) {
            map.put(i, 1);
        }
        System.out.println(map);
    }
}
