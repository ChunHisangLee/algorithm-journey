package class022;

/**
 * Code02_ReversePairs 類別實作「翻转对数量」的高效合併排序解法，適用於 LeetCode 題目：
 * https://leetcode.cn/problems/reverse-pairs/
 * <p>
 * 本程式採用分治思路，透過合併排序過程中統計「翻转对」（reverse pairs）的數量。
 * </p>
 *
 * <p><strong>原始註解：</strong></p>
 * <ul>
 *   <li>// 翻转对数量</li>
 *   <li>// 测试链接 : https://leetcode.cn/problems/reverse-pairs/</li>
 * </ul>
 *
 * @author Jack Lee
 */
public class Code02_ReversePairs {

	/**
	 * 用於合併排序的輔助陣列，大小設定為 50001，以符合 LeetCode 上
	 * arr.length 最多為 50000 的限制。如輸入陣列長度超過 50000，
	 * 需適當調整此大小。
	 */
	public static int MAX_N = 50001;

	/** 輔助合併時使用的暫存陣列。 */
	public static int[] help = new int[MAX_N];

	/**
	 * 計算並返回輸入陣列中的翻转对數量。
	 *
	 * @param arr 要計算翻转对的整數陣列
	 * @return 翻转对的總數
	 */
	public static int reversePairs(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return counts(arr, 0, arr.length - 1);
	}

	/**
	 * 遞迴地統計 arr[l..r] 區間內的翻转对數量，同時將該區間排序。
	 * <p>
	 * 翻转对定義：若 i < j 且 arr[i] > 2 * arr[j]，則 (i, j) 為一個翻转对。
	 * 使用分治合併排序，在 merge 過程中累計跨左右的翻转对數量。
	 * </p>
	 *
	 * @param arr 原始陣列
	 * @param l   左邊界索引（包含）
	 * @param r   右邊界索引（包含）
	 * @return 该区间内翻转对的数量
	 */
	public static int counts(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = (l + r) / 2;
		return counts(arr, l, m)
				+ counts(arr, m + 1, r)
				+ merge(arr, l, m, r);
	}

	/**
	 * 合併 arr[l..m] 与 arr[m+1..r] 两个已排序子区间，並统计跨左右的翻转对数量。
	 * <p>
	 * 1. 统计部分：使用双指针 i, j 分别指向左右子区的起始位置，当 arr[i] > 2 * arr[j] 时，
	 *    说明当前 i 与此时的 j 及其之后的所有位置构成翻转对，累加 (j_start - (m+1))。<br>
	 * 2. 合併部分：標準兩路合併，將排序後的結果放入 help，再複製回 arr[l..r]。
	 * </p>
	 *
	 * @param arr 原始陣列
	 * @param l   左子区起始索引（包含）
	 * @param m   左子区结束索引（包含），右子区从 m+1 开始
	 * @param r   右子区结束索引（包含）
	 * @return 此次合併過程中計算出的翻转对增量
	 */
	public static int merge(int[] arr, int l, int m, int r) {
		// 统计部分：计算跨左右产生的翻转对数量
		int ans = 0;
		int j = m + 1;
		for (int i = l; i <= m; i++) {
			while (j <= r && (long) arr[i] > (long) arr[j] * 2) {
				j++;
			}
			ans += j - (m + 1);
		}
		// 正常 merge：两路合并已排序子区
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
		// 将 help[l..r] 复制回 arr[l..r]
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
		return ans;
	}
}
