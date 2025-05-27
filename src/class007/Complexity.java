package class007;

import java.util.ArrayList;

/** 演示不同代码结构的時間複雜度： - 冒泡排序（O(N^2)) - 動態數組操作（均攤 O(1)) - 基於調和級數的雙層循環（O(N log N)) - 普通雙層循環（O(N^2)) */
public class Complexity {

  /**
   * 冒泡排序：使用单个循环结构实现，但時間複雜度仍為 O(N^2)。 每輪將當前未排序範圍中的最大（或最小）元素「冒」到末端。
   *
   * @param arr 待排序的整型數組
   */
  public static void bubbleSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int n = arr.length;
    int end = n - 1;
    int i = 0;
    while (end > 0) {
      if (arr[i] > arr[i + 1]) {
        swap(arr, i, i + 1);
      }
      if (i < end - 1) {
        i++;
      } else {
        end--;
        i = 0;
      }
    }
  }

  /**
   * 交換數組中兩個元素。
   *
   * @param arr 數組
   * @param i 索引 i
   * @param j 索引 j
   */
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * 主方法，演示隨機數組生成、動態數組操作及時間複雜度測試。
   *
   * @param args 未使用的命令行參數
   */
  public static void main(String[] args) {
    // 隨機生成長度為 n，值在 [0, v) 之間且相鄰不相等的數組
    int n = 10;
    int v = 4;
    int[] arr1 = new int[n];
    arr1[0] = (int) (Math.random() * v);
    for (int idx = 1; idx < n; idx++) {
      do {
        arr1[idx] = (int) (Math.random() * v);
      } while (arr1[idx] == arr1[idx - 1]);
    }
    for (int num : arr1) {
      System.out.print(num + " ");
    }
    System.out.println();
    System.out.println("=========");

    // Java 中的動態數組：ArrayList，均攤添加操作為 O(1)
    ArrayList<Integer> arr2 = new ArrayList<>();
    arr2.add(5);
    arr2.add(4);
    arr2.add(9);
    arr2.set(1, 6);
    System.out.println(arr2.get(1));
    System.out.println("=========");

    // 演示冒泡排序
    int[] arr = {64, 31, 78, 0, 5, 7, 103};
    bubbleSort(arr);
    for (int num : arr) {
      System.out.print(num + " ");
    }
    System.out.println();
    System.out.println("=========");

    // 測試 O(N log N) 雙層循環：基於調和級數
    int N = 200_000;
    long start, end;
    System.out.println("測試 O(N log N) 開始");
    start = System.currentTimeMillis();
    for (int i = 1; i <= N; i++) {
      for (int j = i; j <= N; j += i) {
        // 每輪 j 增量為 i，總操作次數約為 N/1 + N/2 + ... + N/N = N * H_N = O(N log N)
      }
    }
    end = System.currentTimeMillis();
    System.out.println("測試結束，運行時間 : " + (end - start) + " 毫秒");

    // 測試 O(N^2) 雙層循環
    System.out.println("測試 O(N^2) 開始");
    start = System.currentTimeMillis();
    for (int i = 1; i <= N; i++) {
      for (int j = i; j <= N; j++) {
        // 等差數列累加，操作次數 ~ N^2 / 2 = O(N^2)
      }
    }
    end = System.currentTimeMillis();
    System.out.println("測試結束，運行時間 : " + (end - start) + " 毫秒");
  }
}
