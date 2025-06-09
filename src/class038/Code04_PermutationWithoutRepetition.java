package class038;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 類 Code04_PermutationWithoutRepetition
 *
 * 提供對可能含重複元素的整數陣列生成不重複全排列的方法。
 * 每種排列順序唯一，結果可按任意順序返回。
 *
 * <p>測試鏈接：https://leetcode.cn/problems/permutations-ii/</p>
 */
public class Code04_PermutationWithoutRepetition {

    /**
     * 返回 nums 的所有不重複排列。
     *
     * @param nums 可能包含重複元素的整數陣列
     * @return 所有不重複排列列表
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, ans);
        return ans;
    }

    /**
     * 輔助遞迴方法：在位置 index 處選擇元素並生成排列，通過 HashSet 避免同層重複。
     *
     * @param nums  被排列的整數陣列（原地修改）
     * @param index 當前固定位置索引
     * @param ans   結果列表，收集所有排列
     */
    private static void backtrack(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> cur = new ArrayList<>(nums.length);
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
            return;
        }
        // 記錄本層已經嘗試過的元素值，避免重複換位
        HashSet<Integer> used = new HashSet<>();
        for (int j = index; j < nums.length; j++) {
            if (used.add(nums[j])) { // 只有首次遇到才處理
                swap(nums, index, j);
                backtrack(nums, index + 1, ans);
                swap(nums, index, j); // 回溯：恢復原狀
            }
        }
    }

    /**
     * 交換陣列中兩個索引位置的值。
     *
     * @param nums 整數陣列
     * @param i    索引 i
     * @param j    索引 j
     */
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /** 測試示例：生成 [1,2,2] 的所有不重複排列。 */
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> res = permuteUnique(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
