package class006;

import java.util.Arrays;

// 有序数组中找<=num的最右位置
public class Code03_FindRight {

  /**
   * 程序入口，进行多次随机测试，验证反向线性扫描与二分查找的一致性。
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
      if (right(arr, num) != findRight(arr, num)) {
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
   * @return 随机生成的整型数组
   */
  public static int[] randomArray(int n, int v) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = (int) (Math.random() * v) + 1;
    }
    return arr;
  }

  /**
   * 反向线性扫描：返回数组中最后一个<=num的最右索引。
   *
   * @param arr 已排序的整型数组
   * @param num 目标值
   * @return 若存在返回最右索引，否则返回 -1
   */
  public static int right(int[] arr, int num) {
    for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i] <= num) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 二分查找：在有序数组中找到最后一个<=num的最右位置。
   *
   * @param arr 有序整型数组
   * @param num 目标值
   * @return 若存在返回最右索引，否则返回 -1
   */
  public static int findRight(int[] arr, int num) {
    int l = 0, r = arr.length - 1;
    int ans = -1;
    while (l <= r) {
      int m = l + ((r - l) >> 1);
      if (arr[m] <= num) {
        ans = m;
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return ans;
  }
}
