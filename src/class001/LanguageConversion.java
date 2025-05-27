package class001;

import java.util.concurrent.ThreadLocalRandom;

// 测试链接 : https://leetcode.cn/problems/sort-an-array/
// 此时不要求掌握，因为这些排序后续的课都会讲到的
// 这里只是想说明代码语言的转换并不困难
// 整个系列虽然都是java讲的，但使用不同语言的同学听懂思路之后，想理解代码真的不是问题
// 语言问题并不是学习算法的障碍，有了人工智能工具之后，就更不是障碍了

/**
 * 提供多种经典排序算法的实现，包括归并排序、随机快速排序和堆排序。
 *
 * <p>算法时间复杂度均为 O(n log n)，空间复杂度根据具体算法有所不同。
 */
public class LanguageConversion {

  /**
   * 对输入数组进行原地排序，示例使用归并排序。
   *
   * @param nums 待排序的整型数组
   * @return 已排序的数组引用，与输入数组相同
   */
  public static int[] sortArray(int[] nums) {
    // 如果数组长度不足 2，则直接返回
    if (nums == null || nums.length < 2) {
      return nums;
    }
    mergeSort(nums);
    return nums;
  }

  /**
   * 归并排序的非递归实现。
   *
   * @param arr 待排序的整型数组
   */
  private static void mergeSort(int[] arr) {
    int n = arr.length;
    int[] aux = new int[n];
    // 步长依次翻倍
    for (int width = 1; width < n; width <<= 1) {
      int l = 0;
      // 对每一段长度为 width 的子区间合并
      while (l + width < n) {
        int m = l + width - 1;
        int r = Math.min(l + (width << 1) - 1, n - 1);
        merge(arr, aux, l, m, r);
        l = r + 1;
      }
    }
  }

  /**
   * 将 arr[l..m] 和 arr[m+1..r] 合并到 aux，再复制回 arr。
   *
   * @param arr 原数组
   * @param aux 辅助数组，长度应 >= arr.length
   * @param l 左区间起点
   * @param m 左区间终点
   * @param r 右区间终点
   */
  private static void merge(int[] arr, int[] aux, int l, int m, int r) {
    int p1 = l, p2 = m + 1, k = l;
    // 合并两部分
    while (p1 <= m && p2 <= r) {
      aux[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    // 剩余部分
    while (p1 <= m) {
      aux[k++] = arr[p1++];
    }
    while (p2 <= r) {
      aux[k++] = arr[p2++];
    }
    // 复制回原数组
    System.arraycopy(aux, l, arr, l, r - l + 1);
  }

  /**
   * 随机快速排序入口。
   *
   * @param arr 待排序的整型数组
   */
  public static void quickSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    sort(arr, 0, arr.length - 1);
  }

  /**
   * 快速排序递归方法。
   *
   * @param arr 数组
   * @param l 区间左边界
   * @param r 区间右边界
   */
  private static void sort(int[] arr, int l, int r) {
    if (l >= r) {
      return;
    }
    // 随机选取基准值，避免退化
    int pivot = arr[l + ThreadLocalRandom.current().nextInt(r - l + 1)];
    int[] bounds = partition(arr, l, r, pivot);
    sort(arr, l, bounds[0] - 1);
    sort(arr, bounds[1] + 1, r);
  }

  /**
   * 三路划分：将小于、等于和大于 pivot 的元素分别分区。
   *
   * @param nums 数组
   * @param l 区间左边界
   * @param r 区间右边界
   * @param pivot 基准值
   * @return 长度为 2 的数组，[等于区间起点, 等于区间终点]
   */
  private static int[] partition(int[] nums, int l, int r, int pivot) {
    int first = l, last = r;
    int i = l;
    while (i <= last) {
      if (nums[i] < pivot) {
        swap(nums, first++, i++);
      } else if (nums[i] > pivot) {
        swap(nums, i, last--);
      } else {
        i++;
      }
    }
    return new int[] {first, last};
  }

  /**
   * 堆排序实现。
   *
   * @param nums 待排序的整型数组
   */
  public static void heapSort(int[] nums) {
    if (nums == null || nums.length < 2) {
      return;
    }
    int n = nums.length;
    // 构建大顶堆
    for (int i = (n >> 1) - 1; i >= 0; i--) {
      heapify(nums, i, n);
    }
    // 交换堆顶和末尾元素，缩小堆
    for (int end = n - 1; end > 0; end--) {
      swap(nums, 0, end);
      heapify(nums, 0, end);
    }
  }

  /**
   * 对以 i 为根的子树执行下沉操作，使其满足大顶堆性质。
   *
   * @param nums 数组
   * @param i 根节点索引
   * @param n 堆的有效长度
   */
  private static void heapify(int[] nums, int i, int n) {
    int largest = i;
    int l = (i << 1) + 1;
    int r = l + 1;
    if (l < n && nums[l] > nums[largest]) {
      largest = l;
    }
    if (r < n && nums[r] > nums[largest]) {
      largest = r;
    }
    if (largest != i) {
      swap(nums, i, largest);
      heapify(nums, largest, n);
    }
  }

  /**
   * 交换数组中两个元素的位置。
   *
   * @param arr 数组
   * @param i 索引 i
   * @param j 索引 j
   */
  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
