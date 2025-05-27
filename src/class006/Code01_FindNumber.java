package class006;

import java.util.Arrays;

// 有序数组中是否存在一个数字
public class Code01_FindNumber {

  /**
   * 程序入口，进行多次随机测试，验证线性查找与二分查找的一致性。
   *
   * @param args 未使用的命令行参数
   */
  public static void main(String[] args) {
    int N = 100;
    int V = 1000;
    int testTime = 500_000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int n = (int) (Math.random() * N);
      int[] arr = randomArray(n, V);
      Arrays.sort(arr);
      int num = (int) (Math.random() * V);
      if (right(arr, num) != exist(arr, num)) {
        System.out.println("出错了!");
        break;
      }
    }
    System.out.println("测试结束");
  }

  /**
   * 生成长度为 n、值在 [1, v] 范围内的随机数组。
   *
   * @param n 数组长度
   * @param v 随机值上限
   * @return 随机生成的数组
   */
  public static int[] randomArray(int n, int v) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = (int) (Math.random() * v) + 1;
    }
    return arr;
  }

  /**
   * 线性查找：在有序数组中逐个检查是否存在目标值。
   *
   * @param sortedArr 已排序的整型数组
   * @param num 目标值
   * @return 若数组中存在 num 则返回 true，否则返回 false
   */
  public static boolean right(int[] sortedArr, int num) {
    for (int cur : sortedArr) {
      if (cur == num) {
        return true;
      }
    }
    return false;
  }

  /**
   * 二分查找：在有序数组中查找目标值。
   *
   * @param arr 有序整型数组
   * @param num 目标值
   * @return 若数组中存在 num 则返回 true，否则返回 false
   */
  public static boolean exist(int[] arr, int num) {
    if (arr == null || arr.length == 0) {
      return false;
    }
    int l = 0, r = arr.length - 1;
    while (l <= r) {
      int m = (l + r) >>> 1;
      if (arr[m] == num) {
        return true;
      } else if (arr[m] > num) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return false;
  }
}
