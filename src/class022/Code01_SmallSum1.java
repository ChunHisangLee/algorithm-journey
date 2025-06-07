package class022;

import java.io.*;

/**
 * Code01_SmallSum1 類別實作「小和問題」的高效合併排序解法，適用於 NowCoder 題目：
 * https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469
 * <p>
 * 本程式採用 ACM 練習風格的輸入輸出方式，請同學在提交時將類別名改為 "Main"。
 * </p>
 *
 * <p><strong>原始註解：</strong></p>
 * <ul>
 *   <li> // 小和问题，java版</li>
 *   <li> // 测试链接 : https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469</li>
 *   <li> // 请同学们务必参考如下代码中关于输入、输出的处理</li>
 *   <li> // 这是输入输出处理效率很高的写法</li>
 *   <li> // 提交以下的code，提交时请把类名改成"Main"，可以直接通过</li>
 * </ul>
 *
 * @author Jack Lee
 */
public class Code01_SmallSum1 {

	/**
	 * 最大陣列長度，上限設定為 100001。若輸入長度超過此值，需自行調整。
	 */
	public static int MAX_N = 100001;

	/**
	 * 用來存放待計算「小和」的整數陣列。
	 */
	public static int[] arr = new int[MAX_N];

	/**
	 * 輔助合併時使用的暫存陣列，大小與 MAX_N 相同。
	 */
	public static int[] help = new int[MAX_N];

	/** 實際輸入的資料筆數。 */
	public static int n;

	/**
	 * 程式進入點，反覆讀取一組測試資料（n 與 n 個整數），計算小和並輸出。
	 * <p>
	 * 輸入格式：多組測試，直到 EOF 為止。<br>
	 * 每組測試先讀取整數 n，接著讀取 n 個整數作為陣列元素。<br>
	 * 輸出格式：每組測試輸出一行答案（long 型小和）。
	 * </p>
	 *
	 * @param args 未使用
	 * @throws IOException 若 IO 過程發生例外
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		// 反覆讀取 n，直到檔案結尾
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			for (int i = 0; i < n; i++) {
				in.nextToken();
				arr[i] = (int) in.nval;
			}
			out.println(smallSum(0, n - 1));
		}
		out.flush();
		out.close();
	}

	/**
	 * 遞迴計算 arr[l..r] 區間內所有元素構成的小和總和，同時將該區間排序。
	 * <p>
	 * 小和定義：對於每個元素 arr[i]，計算左側所有小於 arr[i] 的元素之和，累加到答案中。<br>
	 * 透過分治合併排序，在 merge 過程中累計跨左右產生的小和。
	 * </p>
	 *
	 * @param l 左邊界索引（包含）
	 * @param r 右邊界索引（包含）
	 * @return 該區間內所有元素的小和累加總和
	 */
	public static long smallSum(int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = (l + r) / 2;
		return smallSum(l, m) + smallSum(m + 1, r) + merge(l, m, r);
	}

	/**
	 * 合併 arr[l..m] 與 arr[m+1..r]，並計算「跨左右」的小和累加。
	 * <p>
	 * 1. 統計部分：假設左右子區均已排序，用兩指標分別掃描，當左子區元素小於右子區元素時，
	 *    該左元素對右子區當前元素及之後所有元素都貢獻相同值的小和累計，依據此思路累加。<br>
	 * 2. 合併部分：標準的兩路合併，將排序後的結果寫入 help，再複製回 arr[l..r]。
	 * </p>
	 *
	 * @param l 左子區起始索引（inclusive）
	 * @param m 左子區結束索引（inclusive），右子區從 m+1 開始
	 * @param r 右子區結束索引（inclusive）
	 * @return 此次合併所計算出的小和增量
	 */
	public static long merge(int l, int m, int r) {
		// 統計部分：計算跨左右產生的小和
		long ans = 0;
		int i = l;
		// 用 sum 累計當前可貢獻的小和
		for (int j = m + 1, sum = 0; j <= r; j++) {
			while (i <= m && arr[i] <= arr[j]) {
				sum += arr[i++];
			}
			ans += sum;
		}
		// 合併部分：標準兩路合併
		i = l;
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
		// 將 help[l..r] 複製回 arr[l..r]
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
		return ans;
	}
}
