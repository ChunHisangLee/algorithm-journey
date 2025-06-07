package class035;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashSet;

// 全 O(1) 的資料結構 (AllOne)
// 支援以下操作，皆為 O(1) 平均時間複雜度：
// 1. inc(key)：將 key 的計數加 1
// 2. dec(key)：將 key 的計數減 1，如果變為 0，移除該 key
// 3. getMaxKey()：返回當前計數最高的 key
// 4. getMinKey()：返回當前計數最低的 key
// 測試連結 : https://leetcode.cn/problems/all-oone-data-structure/
public class Code07_AllO1 {

  static class AllOne {

    // 定義雙向鏈表節點 Bucket
    static class Bucket {
      int cnt; // 計數值
      Set<String> keys; // 擁有相同計數的 key 集合 (LinkedHashSet 保持插入順序)
      Bucket prev, next; // 前驅與後繼節點

      Bucket(int count) {
        this.cnt = count;
        this.keys = new LinkedHashSet<>();
      }
    }

    // 雙向鏈表的哨兵頭尾，分別代表負無限和正無限
    private final Bucket head;
    private final Bucket tail;
    // map: key -> 所在的 Bucket 節點
    private final Map<String, Bucket> keyBucketMap;

    public AllOne() {
      head = new Bucket(Integer.MIN_VALUE);
      tail = new Bucket(Integer.MAX_VALUE);
      head.next = tail;
      tail.prev = head;
      keyBucketMap = new HashMap<>();
    }

    /** 將 key 的計數加 1 - 如果 key 不存在，視同從 0 -> 1 - 否則移動到下一個 cnt+1 的 Bucket */
    public void inc(String key) {
      if (!keyBucketMap.containsKey(key)) {
        // 從 head 開始，插入到 cnt=1 的 Bucket
        Bucket bucket1 = head.next;
        if (bucket1.cnt != 1) {
          bucket1 = insertBucketAfter(head, 1);
        }
        bucket1.keys.add(key);
        keyBucketMap.put(key, bucket1);
      } else {
        Bucket cur = keyBucketMap.get(key);
        Bucket nextBucket = cur.next;
        if (nextBucket.cnt != cur.cnt + 1) {
          nextBucket = insertBucketAfter(cur, cur.cnt + 1);
        }
        nextBucket.keys.add(key);
        keyBucketMap.put(key, nextBucket);
        // 從原 Bucket 移除 key
        removeKeyFromBucket(cur, key);
      }
    }

    /** 將 key 的計數減 1 - 如果計數變為 0，從結構中移除 - 否則移動到前一個 cnt-1 的 Bucket */
    public void dec(String key) {
      Bucket cur = keyBucketMap.get(key);
      if (cur == null) return;

      if (cur.cnt == 1) {
        // 移除 key
        removeKeyFromBucket(cur, key);
        keyBucketMap.remove(key);
      } else {
        Bucket prevBucket = cur.prev;
        if (prevBucket.cnt != cur.cnt - 1) {
          prevBucket = insertBucketAfter(cur.prev, cur.cnt - 1);
        }
        prevBucket.keys.add(key);
        keyBucketMap.put(key, prevBucket);
        removeKeyFromBucket(cur, key);
      }
    }

    /** 返回任意一個計數最高的 key */
    public String getMaxKey() {
      if (tail.prev == head) return "";
      // 取尾部前一個 Bucket 中任意一個 key
      return tail.prev.keys.iterator().next();
    }

    /** 返回任意一個計數最低的 key */
    public String getMinKey() {
      if (head.next == tail) return "";
      // 取頭部後一個 Bucket 中任意一個 key
      return head.next.keys.iterator().next();
    }

    // 在 prevNode 後插入一個新的 Bucket，計數為 count，並返回該節點
    private Bucket insertBucketAfter(Bucket prevNode, int count) {
      Bucket bucket = new Bucket(count);
      bucket.next = prevNode.next;
      prevNode.next.prev = bucket;
      prevNode.next = bucket;
      bucket.prev = prevNode;
      return bucket;
    }

    // 從 Bucket 移除 key，並在 keys 為空時刪除該節點
    private void removeKeyFromBucket(Bucket bucket, String key) {
      bucket.keys.remove(key);
      if (bucket.keys.isEmpty()) {
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
      }
    }
  }
}
