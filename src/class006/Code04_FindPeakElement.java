package class006;

// 峰值元素是指其值严格大于左右相邻值的元素
// 给你一个整数数组 nums，已知任何两个相邻的值都不相等
// 找到峰值元素并返回其索引
// 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
// 你可以假设 nums[-1] = nums[n] = 无穷小
// 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
public class Code04_FindPeakElement {
    // 测试链接 : https://leetcode.cn/problems/find-peak-element/
    class Solution {
        public int findPeakElement(int[] arr) {
            int left = 0;
            int right = arr.length - 1;
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (arr[mid] > arr[mid + 1]) {
                    // 峰在左邊（包含 mid）
                    right = mid;
                } else {
                    // 峰在右邊（不包含 mid）
                    left = mid + 1;
                }
            }
            // left==right 時一定指向一個 peak
            return left;
        }
    }
}
