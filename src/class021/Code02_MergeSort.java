package class021;

/**
 * Code02_MergeSort 提供了兩種合併排序的實作（遞迴版與非遞迴版），
 * 可用於 LeetCode 「Sort an Array」（https://leetcode.cn/problems/sort-an-array/）題目，
 * 並以「填函數練習」風格編寫。原本的註解已完整保留。
 * <p>
 * Usage:
 * <pre>
 * int[] nums = {5, 2, 3, 1};
 * int[] sorted = Code02_MergeSort.sortArray(nums);
 * // sorted 會是 [1, 2, 3, 5]
 * </pre>
 * </p>
 *
 * <p><strong>原始註解：</strong></p>
 * <ul>
 *   <li>// 归并排序，填函数练习风格</li>
 *   <li>// 测试链接 : https://leetcode.cn/problems/sort-an-array/</li>
 * </ul>
 *
 * @author Jack Lee
 */
public class Code02_MergeSort {

	/**
	 * Sorts the given array using merge sort (either recursive or iterative).
	 * If the array length is greater than 1, calls mergeSort2 (iterative) by default.
	 *
	 * @param nums the input array to be sorted
	 * @return the same array instance, now sorted in ascending order
	 */
	public static int[] sortArray(int[] nums) {
		if (nums.length > 1) {
			// mergeSort1 为递归方法
			// mergeSort2 为非递归方法
			// 用哪个都可以
			// mergeSort1(nums);
			mergeSort2(nums);
		}
		return nums;
	}

	/**
	 * 用於合併排序的輔助陣列，大小設定為 50001，以符合 LeetCode 上
	 * nums.length 最多為 50000 的限制。如輸入陣列長度超過 50000，
	 * 需適當調整此大小。
	 */
	public static int MAXN = 50001;

	/** 輔助合併時使用的暫存陣列。 */
	public static int[] help = new int[MAXN];

	/**
	 * 归并排序递归版入口函數。
	 *
	 * @param arr the array to be sorted
	 */
	public static void mergeSort1(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	/**
	 * 递归拆分與合併的輔助方法。
	 * <p>
	 * 假設對陣列 arr 的 [l..r] 區間進行排序：<br>
	 * 1. 若 l == r，表示只有一個元素，無須排序，直接 return。<br>
	 * 2. 否則計算中點 m，遞迴排序 [l..m] 與 [m+1..r]，再呼叫 merge 合併兩段已排序子區間。
	 * </p>
	 *
	 * @param arr the array containing the segment to sort
	 * @param l   left index of the current segment (inclusive)
	 * @param r   right index of the current segment (inclusive)
	 */
	public static void sort(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		int m = (l + r) / 2;
		sort(arr, l, m);
		sort(arr, m + 1, r);
		merge(arr, l, m, r);
	}

	/**
	 * 归并排序非递归版（Iterative / Bottom-up）。
	 * <p>
	 * 以 step 為每次要合併的子陣列長度，從 1 開始，每輪將相鄰兩段長度為 step 的子陣列合併，
	 * step 倍增直至 >= n。<br>
	 * 例如：初始 step=1，合併 [0..0] 與 [1..1]、[2..2] 與 [3..3] ……<br>
	 * 再 step=2，合併 [0..1] 與 [2..3]、[4..5] 與 [6..7] ……，依此類推。
	 * </p>
	 *
	 * @param arr the array to be sorted
	 */
	public static void mergeSort2(int[] arr) {
		int n = arr.length;
		for (int l, m, r, step = 1; step < n; step <<= 1) {
			l = 0;
			while (l < n) {
				m = l + step - 1;
				if (m + 1 >= n) {
					break; // 如果右半段不存在可合併的部分，跳出
				}
				r = Math.min(l + (step << 1) - 1, n - 1);
				merge(arr, l, m, r);
				l = r + 1;
			}
		}
	}

	/**
	 * 合併兩個已排序的子區間 [l..m] 與 [m+1..r]，合併後的結果寫回 arr[l..r]。
	 * <p>
	 * 用兩個指標 a, b 分別指向左右子區的開始位置，依序比較 arr[a] 與 arr[b]，
	 * 將較小者放入 help，直到其中一邊耗盡；再把剩餘元素全部複製。最後，
	 * 將 help[l..r] 中的值覆蓋回 arr[l..r]。<br>
	 * 此方法的時間複雜度為 O(r-l+1)，空間複雜度為 O(r-l+1)（使用 help 暫存陣列）。
	 * </p>
	 *
	 * @param arr the array包含待合併的段
	 * @param l   左側子區起始索引（inclusive）
	 * @param m   左側子區結束索引（inclusive），右側子區自 m+1 開始
	 * @param r   右側子區結束索引（inclusive）
	 */
	public static void merge(int[] arr, int l, int m, int r) {
		int i = l;
		int a = l;
		int b = m + 1;
		while (a <= m && b <= r) {
			help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
		}
		while (a <= m) {
			help[i++] = arr[a++];
		}
		while (b <= r) {
			help[i++] = arr[b++];
		}
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
	}
}
