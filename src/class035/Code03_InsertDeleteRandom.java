package class035;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

// 實現插入、刪除和隨機訪問均為 O(1) 時間複雜度的資料結構
// 測試連結 : https://leetcode.cn/problems/insert-delete-getrandom-o1/
public class Code03_InsertDeleteRandom {

    /**
     * 隨機集合
     */
    class RandomizedSet {

        // 值到索引的映射，用於快速判定存在與獲取位置
        private final Map<Integer, Integer> indexMap;
        // 儲存當前集合中的值，可通過索引隨機訪問
        private final List<Integer> values;

        public RandomizedSet() {
            indexMap = new HashMap<>();
            values = new ArrayList<>();
        }

        /**
         * 插入元素 val
         * 若 val 已存在，返回 false；否則將其加入尾端並記錄索引，返回 true
         */
        public boolean insert(int val) {
            if (indexMap.containsKey(val)) {
                return false;
            }
            values.add(val);
            indexMap.put(val, values.size() - 1);
            return true;
        }

        /**
         * 刪除元素 val
         * 若 val 不存在，返回 false；否則將尾元素替換到被刪除位置，再移除尾端元素，並更新映射，返回 true
         */
        public boolean remove(int val) {
            Integer idx = indexMap.get(val);
            if (idx == null) {
                return false;
            }
            int lastIdx = values.size() - 1;
            int lastVal = values.get(lastIdx);
            // 將尾元素移到被刪除位置
            values.set(idx, lastVal);
            indexMap.put(lastVal, idx);
            // 移除原尾元素
            values.remove(lastIdx);
            indexMap.remove(val);
            return true;
        }

        /**
         * 獲取隨機元素
         * 利用內建的 ThreadLocalRandom 獲取範圍內隨機索引
         */
        public int getRandom() {
            int size = values.size();
            int randIdx = ThreadLocalRandom.current().nextInt(size);
            return values.get(randIdx);
        }
    }
}
