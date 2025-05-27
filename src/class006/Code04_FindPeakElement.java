package class006;

// 峰值元素是指其值严格大于左右相邻值的元素
// 给你一个整数数组 nums，已知任何两个相邻的值都不相等
// 找到峰值元素并返回其索引
// 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
// 你可以假设 nums[-1] = nums[n] = 无穷小
// 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
public class Code04_FindPeakElement {

  /**
   * 测试链接 : https://leetcode.cn/problems/find-peak-element/
   *
   * <p>在无相邻元素相等、边界视为 -∞ 的情况下，二分查找任意一个峰值的索引。 时间复杂度：O(log n)。
   */
  public static class Solution {

    /**
     * 查找峰值元素的索引。
     *
     * @param arr 输入的整数数组，长度至少为1，且任意相邻元素不相等
     * @return 任一峰值元素的索引
     */
    public static int findPeakElement(int[] arr) {
      int n = arr.length;
      // 唯一元素或首元素即为峰
      if (n == 1 || arr[0] > arr[1]) {
        return 0;
      }
      // 尾部元素为峰值
      if (arr[n - 1] > arr[n - 2]) {
        return n - 1;
      }
      int l = 1, r = n - 2;
      while (l <= r) {
        int m = l + ((r - l) >>> 1);
        // 左侧高，峰值在左区间
        if (arr[m - 1] > arr[m]) {
          r = m - 1;
        }
        // 右侧高，峰值在右区间
        else if (arr[m] < arr[m + 1]) {
          l = m + 1;
        }
        // m 即为峰值
        else {
          return m;
        }
      }
      return -1; // 理论上不会到达此处
    }
  }
}
