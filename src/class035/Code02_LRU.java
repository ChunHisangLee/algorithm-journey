package class035;

import java.util.HashMap;

// 實現 LRU Cache 結構
// 要求：get 和 put 操作均為 O(1) 時間複雜度
// 測試連結 : https://leetcode.cn/problems/lru-cache/
public class Code02_LRU {

    class LRUCache {

        // 雙向節點定義
        class Node {
            int key;
            int val;
            Node prev;
            Node next;

            public Node(int k, int v) {
                key = k;
                val = v;
            }
        }

        // 採用虛擬頭尾節點，簡化插入和移除邏輯
        private Node head;
        private Node tail;
        // 快速存取節點的 Map
        private HashMap<Integer, Node> cache;
        private int capacity;
        private int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            cache = new HashMap<>();
            head = new Node(-1, -1);  // 虛擬頭
            tail = new Node(-1, -1);  // 虛擬尾
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 獲取 key 的值，若存在則移動到尾部（最近使用），否則返回 -1
         */
        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) return -1;
            moveToTail(node);
            return node.val;
        }

        /**
         * 插入或更新 key 對應的值
         * 若已存在，更新值並移動到尾部；否則創建新節點，並在超出容量時移除最近最少使用節點
         */
        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node != null) {
                node.val = value;
                moveToTail(node);
            } else {
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addToTail(newNode);
                size++;
                if (size > capacity) {
                    Node lru = removeHead();
                    cache.remove(lru.key);
                    size--;
                }
            }
        }

        // 將節點移動到尾部
        private void moveToTail(Node node) {
            removeNode(node);
            addToTail(node);
        }

        // 將節點加入到尾部
        private void addToTail(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }

        // 移除節點
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 移除頭部（最久未使用）的實際節點，返回該節點
        private Node removeHead() {
            Node first = head.next;
            removeNode(first);
            return first;
        }
    }
}
