package class038;

import java.util.ArrayList;
import java.util.List;

/**
 * 類 Code03_Permutations
 *
 * 提供對不含重複元素的整數陣列進行全排列生成的方法。
 * 每種排列順序皆為不同組合，結果可按任意順序返回。
 *
 * <p>測試連結：https://leetcode.cn/problems/permutations/</p>
 */
public class Code03_Permutations {

    /**
     * 方法一：基於交換（swap）機制的原地全排列生成。
     * <p>
     * 核心思路：通過遞迴將當前索引 i 與之後每個位置 j 交換，
     * 完成一種排列後再回溯（恢復交換），以遍歷所有排列。
     * 時間複雜度 O(n! × n)，空間複雜度 O(n! × n)（結果存儲）+ O(n)（遞歸深度）。
     * </p>
     *
     * @param nums 不含重複元素的整數陣列
     * @return 所有可能的排列列表
     */
    public static List<List<Integer>> permuteWithSwap(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrackSwap(nums, 0, ans);
        return ans;
    }

    /**
     * 輔助遞迴方法（swap 版本）。
     *
     * @param nums  被排列的整數陣列
     * @param index 從該索引開始進行交換生成排列
     * @param ans   結果列表，收集所有排列
     */
    private static void backtrackSwap(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> cur = new ArrayList<>(nums.length);
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
            return;
        }
        for (int j = index; j < nums.length; j++) {
            swap(nums, index, j);
            backtrackSwap(nums, index + 1, ans);
            swap(nums, index, j); // 回溯：恢復原狀
        }
    }

    /**
     * 方法二：基於使用標記陣列的回溯生成，全程不改動原始陣列。
     * <p>
     * 使用一個 boolean[] used 標記已選元素，並通過暫存列表 path 保存當前排列，
     * 結束時將 path 複製並加入結果。
     * </p>
     *
     * @param nums 不含重複元素的整數陣列
     * @return 所有可能的排列列表
     */
    public static List<List<Integer>> permuteWithUsed(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackUsed(nums, used, new ArrayList<>(nums.length), ans);
        return ans;
    }

    /**
     * 輔助遞迴方法（used 版本）。
     *
     * @param nums  被排列的整數陣列
     * @param used  標記陣列，true 表示對應索引已被選入當前路徑
     * @param path  暫存當前排列的列表
     * @param ans   結果列表，收集所有排列
     */
    private static void backtrackUsed(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtrackUsed(nums, used, path, ans);
            path.remove(path.size() - 1);
            used[i] = false; // 回溯：重置標記
        }
    }

    /**
     * 交換陣列中兩個元素。
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

    /** 測試範例：示範兩種方法對 [1,2,3] 生成全排列。 */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        System.out.println("使用 swap 版本：");
        for (List<Integer> list : permuteWithSwap(nums)) {
            System.out.println(list);
        }

        System.out.println("\n使用 used 版本：");
        for (List<Integer> list : permuteWithUsed(nums)) {
            System.out.println(list);
        }
    }
}
