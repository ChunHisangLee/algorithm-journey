package class020;

/**
 * GetMaxValue 類別示範如何透過遞迴的方式在陣列範圍 [l..r] 上取得最大值。
 * <p>
 * 主要邏輯：遞迴地將區間不斷對半拆分，直到區間長度為 1，回傳該元素值；然後向上合併
 * 時比較左右子區的最大值並回傳較大者。程式執行時可觀察遞迴呼叫的展開與合併過程。
 * </p>
 *
 * <p><strong>原始註解（已於下方翻譯成繁體中文）：</strong></p>
 * <ul>
 *   <li>// 用这个例子讲解递归如何执行 → // 用這個例子講解遞迴如何執行</li>
 *   <li>// arr[l....r]范围上的最大值 → // arr[l....r]範圍上的最大值</li>
 * </ul>
 *
 * @author Jack Lee
 */
public class GetMaxValue {

	/**
	 * 回傳整個陣列 arr 中的最大值。
	 *
	 * @param arr 欲計算最大值的整數陣列
	 * @return 陣列中的最大值
	 * @throws IllegalArgumentException 當傳入的陣列為空或為 null 時拋出
	 */
	public static int maxValue(int[] arr) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("陣列不可為空");
		}
		return f(arr, 0, arr.length - 1);
	}

	/**
	 * 計算 arr[l..r] 範圍上的最大值。
	 * <p>
	 * 遞迴邏輯：若 l == r，表示區間只有一個元素，直接回傳 arr[l]。<br>
	 * 否則計算中點 m，遞迴呼叫 f(arr, l, m) 與 f(arr, m+1, r) 分別取得左半區與右半區的最大值，
	 * 再回傳兩者中的較大值。
	 * </p>
	 *
	 * @param arr 整數陣列
	 * @param l   左邊界索引（包含）
	 * @param r   右邊界索引（包含）
	 * @return 指定區間內的最大值
	 */
	public static int f(int[] arr, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		int m = (l + r) / 2;
		int lmax = f(arr, l, m);
		int rmax = f(arr, m + 1, r);
		return Math.max(lmax, rmax);
	}

	/**
	 * 主程式，建立範例陣列並印出最大值。
	 *
	 * @param args 未使用
	 */
	public static void main(String[] args) {
		int[] arr = { 3, 8, 7, 6, 4, 5, 1, 2};
		System.out.println("陣列最大值 : " + maxValue(arr));
	}
}
