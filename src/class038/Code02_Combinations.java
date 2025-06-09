package class038;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 類 Code02_Combinations
 * 
 * 提供一個方法來生成給定整數陣列所有可能的組合，該陣列可能包含重複元素，
 * 且結果中不能包含重複的組合。組合的順序不做限制。
 * 
 * <p>例如，輸入 nums = [1, 2, 2]，輸出：
 * [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]</p>
 * 
 * <p>題目鏈接：https://leetcode.cn/problems/subsets-ii/</p>
 */
public class Code02_Combinations {

    /**
     * 返回整數陣列 nums 的所有不重複組合。
     *
     * @param nums 包含可能重複元素的整數陣列
     * @return 所有可能的組合，每個組合為 List<Integer>，整體以 List<List<Integer>> 封裝
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 先對陣列進行排序，便於後續跳過重複元素
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * 回溯函數，用於構造所有組合。
     *
     * @param nums     已排序的整數陣列
     * @param start    下一步可選元素的起始索引
     * @param tempList 當前已構造的組合
     * @param ans      最終結果集，收集所有不重複組合
     */
    private static void backtrack(int[] nums, int start, List<Integer> tempList, List<List<Integer>> ans) {
        // 每次進入即添加當前組合的拷貝
        ans.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            // 如果當前元素與前一元素相同，且前一元素已在同一層被處理過，就跳過，以避免重複
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            tempList.add(nums[i]);
            backtrack(nums, i + 1, tempList, ans);
            tempList.remove(tempList.size() - 1);
        }
    }
}
