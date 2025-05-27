package class004;

/** 提供三种基础排序算法：选择排序、冒泡排序和插入排序，以及数组元素交换工具方法。 */
public class SelectBubbleInsert {

  // 数组中交换 i 和 j 位置的数
  /**
   * 交换数组中两个元素的位置。
   *
   * @param arr 待交换元素的数组
   * @param i 第一个元素索引
   * @param j 第二个元素索引
   */
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // 选择排序
  /**
   * 选择排序，对数组进行原地排序。
   *
   * <p>每次从未排序部分选取最小值并与当前起始位置交换。 时间复杂度 O(n^2)，空间复杂度 O(1)。
   *
   * @param arr 待排序的数组
   */
  public static void selectionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[minIndex]) {
          minIndex = j;
        }
      }
      swap(arr, i, minIndex);
    }
  }

  // 冒泡排序
  /**
   * 冒泡排序，对数组进行原地排序。
   *
   * <p>相邻元素两两比较并交换，大元素逐步 "冒" 到末尾。 时间复杂度 O(n^2)，空间复杂度 O(1)。
   *
   * @param arr 待排序的数组
   */
  public static void bubbleSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int n = arr.length;
    for (int end = n - 1; end > 0; end--) {
      for (int i = 0; i < end; i++) {
        if (arr[i] > arr[i + 1]) {
          swap(arr, i, i + 1);
        }
      }
    }
  }

  // 插入排序
  /**
   * 插入排序，对数组进行原地排序。
   *
   * <p>通过向前比较并交换，将元素插入已排序部分的正确位置。 时间复杂度 O(n^2)，最优情况 O(n)，空间复杂度 O(1)。
   *
   * @param arr 待排序的数组
   */
  public static void insertionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
        swap(arr, j, j + 1);
      }
    }
  }
}
