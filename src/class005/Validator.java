package class005;

/** 验证基础排序算法的正确性：随机生成数组并与多种排序方法结果比对。 */
public class Validator {

  /**
   * 程序入口，执行多次随机测试，比较选择、冒泡和插入排序结果是否一致。
   *
   * @param args 未使用的命令行参数
   */
  public static void main(String[] args) {
    // 随机数组最大长度
    int N = 200;
    // 随机数组每个值，在1~V之间等概率随机
    int V = 1000;
    // testTimes : 测试次数
    int testTimes = 50000;
    System.out.println("测试开始");
    for (int i = 0; i < testTimes; i++) {
      // 随机得到一个长度，长度在[0~N-1]
      int n = (int) (Math.random() * N);
      // 得到随机数组
      int[] arr = randomArray(n, V);
      int[] arr1 = copyArray(arr);
      int[] arr2 = copyArray(arr);
      int[] arr3 = copyArray(arr);
      selectionSort(arr1);
      bubbleSort(arr2);
      insertionSort(arr3);
      if (!sameArray(arr1, arr2) || !sameArray(arr1, arr3)) {
        System.out.println("出错了!");
        // 当有错了
        // 打印是什么例子，出错的
        // 打印三个功能，各自排序成了什么样
        // 可能要把例子带入，每个方法，去debug！
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
      // Math.random() -> double -> [0,v) 小数
      // (int)(Math.random() * v) + 1 -> 1 ~ v
      arr[i] = (int) (Math.random() * v) + 1;
    }
    return arr;
  }

  /**
   * 复制数组。
   *
   * @param arr 原数组
   * @return 新的数组副本
   */
  public static int[] copyArray(int[] arr) {
    int n = arr.length;
    int[] ans = new int[n];
    System.arraycopy(arr, 0, ans, 0, n);
    return ans;
  }

  /**
   * 比较两个数组元素是否逐一相同。
   *
   * @param arr1 数组1
   * @param arr2 数组2
   * @return 若两个数组内容完全一致返回 true，否则返回 false
   */
  public static boolean sameArray(int[] arr1, int[] arr2) {
    int n = arr1.length;
    for (int i = 0; i < n; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  // 数组中交换i和j位置的数
  /**
   * 交换数组中索引 i、j 两个位置的元素。
   *
   * @param arr 待交换的数组
   * @param i 索引 i
   * @param j 索引 j
   */
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // 选择排序
  /**
   * 选择排序：每次选出未排序部分的最小元素，与起始位置交换。
   *
   * @param arr 待排序数组
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
   * 冒泡排序：相邻元素两两比较，逆序时交换，大值逐步冒到末尾。
   *
   * @param arr 待排序数组
   */
  public static void bubbleSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    for (int end = arr.length - 1; end > 0; end--) {
      for (int i = 0; i < end; i++) {
        if (arr[i] > arr[i + 1]) {
          swap(arr, i, i + 1);
        }
      }
    }
  }

  // 插入排序
  /**
   * 插入排序：将当前元素向前插入已排序部分，直到正确位置。
   *
   * @param arr 待排序数组
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
