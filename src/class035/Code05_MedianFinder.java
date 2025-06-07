package class035;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

// 快速獲取資料流中位數的資料結構
// 要求：addNum 和 findMedian 操作平均時間複雜度為 O(log n)
// 測試連結 : https://leetcode.cn/problems/find-median-from-data-stream/
public class Code05_MedianFinder {

  /**
   * 利用兩個堆： - maxHeap (小根堆的逆序)：保存左半部較小元素 - minHeap (自然順序小根堆)：保存右半部較大元素 使兩堆大小之差不超過 1， 中位數即為兩堆頂元素或其平均值
   */
  class MedianFinder {

    // 左半部：最大堆，用於快速取得全部較小元素中的最大值
    private final Queue<Integer> maxHeap;
    // 右半部：最小堆，用於快速取得全部較大元素中的最小值
    private final Queue<Integer> minHeap;

    public MedianFinder() {
      // maxHeap 使用逆序自然比較器
      maxHeap = new PriorityQueue<>(Collections.reverseOrder());
      // minHeap 使用預設自然順序
      minHeap = new PriorityQueue<>();
    }

    /**
     * 向資料流中新增一個數字 1. 若 maxHeap 為空或 num <= maxHeap 頂，則放入 maxHeap；否則放入 minHeap 2. 呼叫 balance()
     * 保持兩堆大小差不超過 1
     */
    public void addNum(int num) {
      if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
        maxHeap.offer(num);
      } else {
        minHeap.offer(num);
      }
      balance();
    }

    /** 取得目前所有已加入數字的中位數 - 當兩堆大小相等時，中位數為兩堆頂元素的平均 - 否則，中位數為較大堆(元素較多)的堆頂 */
    public double findMedian() {
      if (maxHeap.size() == minHeap.size()) {
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
      }
      return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }

    // 平衡兩堆：如任一堆大小超過另一堆超過 1，將堆頂移至對方
    private void balance() {
      if (maxHeap.size() - minHeap.size() > 1) {
        minHeap.offer(maxHeap.poll());
      } else if (minHeap.size() - maxHeap.size() > 1) {
        maxHeap.offer(minHeap.poll());
      }
    }
  }
}
