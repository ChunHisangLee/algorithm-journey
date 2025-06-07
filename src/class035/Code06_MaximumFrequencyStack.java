package class035;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 最大頻率棧 (FreqStack)
// 要求：push、pop 操作均為 O(1) 平均時間
// 測試連結 : https://leetcode.cn/problems/maximum-frequency-stack/
public class Code06_MaximumFrequencyStack {

  /** 支援根據元素出現頻率取出最高頻元素的棧結構 */
  class FreqStack {

    // 當前最頂層頻率
    private int maxFreq = 0;
    // 頻率到該頻率元素棧 (List 模擬棧；保證後進先出)
    private final Map<Integer, List<Integer>> freqStacks;
    // 值到其當前頻率的映射
    private final Map<Integer, Integer> freqMap;

    public FreqStack() {
      freqStacks = new HashMap<>();
      freqMap = new HashMap<>();
    }

    /**
     * 壓入元素 val，並更新其頻率與對應棧 1. 在 freqMap 中將 val 的頻率加 1 2. 在 freqStacks 中將 val 推入對應頻率的棧尾 3. 更新 maxFreq
     */
    public void push(int val) {
      int freq = freqMap.getOrDefault(val, 0) + 1;
      freqMap.put(val, freq);
      freqStacks.computeIfAbsent(freq, k -> new ArrayList<>()).add(val);
      if (freq > maxFreq) {
        maxFreq = freq;
      }
    }

    /**
     * 彈出並返回當前頻率最高、最靠近棧頂的元素 1. 從 freqStacks.get(maxFreq) 的棧尾彈出元素 2. 在 freqMap 中將該元素頻率減 1，若為 0 剔除映射
     * 3. 如果該頻率層棧為空，maxFreq--
     */
    public int pop() {
      List<Integer> stack = freqStacks.get(maxFreq);
      int val = stack.remove(stack.size() - 1);
      // 更新 val 的頻率映射
      int freq = freqMap.get(val) - 1;
      if (freq == 0) {
        freqMap.remove(val);
      } else {
        freqMap.put(val, freq);
      }
      // 若當前頻率層棧為空，則 maxFreq--
      if (stack.isEmpty()) {
        freqStacks.remove(maxFreq);
        maxFreq--;
      }
      return val;
    }
  }
}
