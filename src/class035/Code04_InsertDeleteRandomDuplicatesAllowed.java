package class035;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

// 實現插入、刪除和隨機訪問均為 O(1) 時間複雜度且允許重複元素的資料結構
// 測試連結 : https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/
public class Code04_InsertDeleteRandomDuplicatesAllowed {

    /**
     * 支援重複元素的隨機集合
     */
    class RandomizedCollection {

        // 值到索引集合的映射，對於相同的值，維護所有在列表中的位置
        private final Map<Integer, Set<Integer>> indexMap;
        // 儲存所有元素的列表，可透過索引隨機訪問
        private final List<Integer> values;

        /**
         * 初始化資料結構
         */
        public RandomizedCollection() {
            indexMap = new HashMap<>();
            values = new ArrayList<>();
        }

        /**
         * 插入元素 val，返回該元素在集合中是否首次出現
         * - 將元素加入列表尾部，並將位置加入映射
         */
        public boolean insert(int val) {
            values.add(val);
            int idx = values.size() - 1;
            indexMap.computeIfAbsent(val, k -> new HashSet<>()).add(idx);
            return indexMap.get(val).size() == 1;
        }

        /**
         * 刪除元素 val，返回是否成功
         * - 若 val 不存在，返回 false
         * - 否則取出任一 val 的索引，並用尾元素覆蓋，更新對應映射後移除尾部
         */
        public boolean remove(int val) {
            Set<Integer> idxSet = indexMap.get(val);
            if (idxSet == null || idxSet.isEmpty()) {
                return false;
            }
            // 取得一個 val 的存在位置
            int removeIdx = idxSet.iterator().next();
            int lastIdx = values.size() - 1;
            int lastVal = values.get(lastIdx);
            // 用尾元素覆蓋 removeIdx
            values.set(removeIdx, lastVal);
            // 更新尾元素在映射中的位置集合
            Set<Integer> lastSet = indexMap.get(lastVal);
            lastSet.add(removeIdx);
            lastSet.remove(lastIdx);
            // 移除原 val 的索引
            idxSet.remove(removeIdx);
            if (idxSet.isEmpty()) {
                indexMap.remove(val);
            }
            // 移除列表尾部
            values.remove(lastIdx);
            return true;
        }

        /**
         * 隨機獲取一個元素
         * - 使用 ThreadLocalRandom 以避免多執行緒競爭
         */
        public int getRandom() {
            int size = values.size();
            int randIdx = ThreadLocalRandom.current().nextInt(size);
            return values.get(randIdx);
        }
    }
}
