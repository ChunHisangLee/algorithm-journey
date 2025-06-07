package class021;

import java.io.*;

/**
 * Code01_MergeSort 類別提供了兩種合併排序的實作（遞迴版與非遞迴版），
 * 以及在 ACM 練習環境中高效處理輸入輸出的範例程式。
 * <p>
 * 本程式針對洛谷 P1177 題目進行實作，在提交前請將類別名改為 "Main"。
 * </p>
 * <p>
 * 原始註解：
 * // 归并排序，acm练习风格
 * // 测试链接 : https://www.luogu.com.cn/problem/P1177
 * // 请同学们务必参考如下代码中关于输入、输出的处理
 * // 这是输入输出处理效率很高的写法
 * // 提交以下的code，提交时请把类名改成"Main"，可以直接通过
 */
public class Code01_MergeSort {

	/** 最大陣列長度，原始設定為 100001。 */
	public static int MAXN = 100001;

	/** 用來存放待排序的整數陣列。 */
	public static int[] arr = new int[MAXN];

	/** 輔助合併時使用的暫存陣列。 */
	public static int[] help = new int[MAXN];

	/** 實際要排序的資料筆數（由輸入決定）。 */
	public static int n;

	/**
	 * 程式進入點，讀取輸入並執行非遞迴版合併排序，最後輸出排序結果。
	 *
	 * @param args 指令列參數（本程式未使用）
	 * @throws IOException 若輸入輸出過程發生例外
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		// 讀取第一行的 n
		in.nextToken();
		n = (int) in.nval;
		// 讀取後續 n 個整數到 arr[]
		for (int i = 0; i < n; i++) {
			in.nextToken();
			arr[i] = (int) in.nval;
		}

		// mergeSort1(0, n - 1);
		mergeSort2();

		// 輸出排序後的結果，數字間以空格隔開
		for (int i = 0; i < n - 1; i++) {
			out.print(arr[i] + " ");
		}
		out.println(arr[n - 1]);
		out.flush();
		out.close();
		br.close();
	}

	/**
	 * 合併排序遞迴版（Merge Sort - Recursive）。
	 * <p>
	 * 時間複雜度：T(n) = 2T(n/2) + O(n)，根據 Master 定理為 O(n log n)。<br>
	 * 空間複雜度：O(n)（需要額外的暫存陣列 help）。
	 * </p>
	 *
	 * @param l 要排序區間的左邊界索引（包含）
	 * @param r 要排序區間的右邊界索引（包含）
	 */
	public static void mergeSort1(int l, int r) {
		if (l == r) {
			return;
		}
		int m = (l + r) / 2;
		mergeSort1(l, m);
		mergeSort1(m + 1, r);
		merge(l, m, r);
	}

	/**
	 * 合併排序非遞迴版（Merge Sort - Iterative / Bottom-up）。
	 * <p>
	 * 使用步長（step）從 1、2、4、8 … 不斷翻倍，每次將相鄰區間合併。<br>
	 * 時間複雜度：O(n log n)。<br>
	 * 空間複雜度：O(n)。
	 * </p>
	 */
	public static void mergeSort2() {
		// 一共發生 O(log n) 次合併
		for (int l, m, r, step = 1; step < n; step <<= 1) {
			// 內部分組 merge，時間複雜度 O(n)
			l = 0;
			while (l < n) {
				m = l + step - 1;
				if (m + 1 >= n) {
					break; // 若右半段不存在，跳出
				}
				r = Math.min(l + (step << 1) - 1, n - 1);
				merge(l, m, r);
				l = r + 1;
			}
		}
	}

	/**
	 * 將區間 [l..m] 和 [m+1..r] 進行合併，合併後的結果放回 arr[l..r]。
	 * <p>
	 * l..r 一共有 (r - l + 1) 個數，分為左區間與右區間。<br>
	 * 合併時使用 help[] 作為暫存空間，最終再將幫助陣列的值拷貝回原陣列。
	 * </p>
	 *
	 * @param l 左區間的起始索引（包含）
	 * @param m 左區間的結束索引（包含），右區間從 m + 1 開始
	 * @param r 右區間的結束索引（包含）
	 */
	public static void merge(int l, int m, int r) {
		int i = l;
		int a = l;
		int b = m + 1;
		// 同時掃描左右兩個子區間，將值較小者放入 help
		while (a <= m && b <= r) {
			help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
		}
		// 左半區仍有剩餘，全部複製至 help
		while (a <= m) {
			help[i++] = arr[a++];
		}
		// 右半區仍有剩餘，全部複製至 help
		while (b <= r) {
			help[i++] = arr[b++];
		}
		// 將 help[l..r] 的值拷回 arr[l..r]
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
	}
}
