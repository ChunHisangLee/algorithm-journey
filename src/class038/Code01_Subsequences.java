package class038;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 提供字串所有子序列的生成方法，並移除重複結果。
 *
 * <p>子序列指的是從原始字串中，依序選取任意數量的字元所組成的字串， 包含空字串，但不改變字元相對順序。
 */
public class Code01_Subsequences {

  /**
   * 方法一：透過遞迴與 StringBuilder 建構子序列，並使用 Set 去重。
   *
   * @param str 原始字串
   * @return 包含所有子序列（含空字串），不重複的字串陣列
   */
  public static String[] generateSubsequencesRec1(String str) {
    char[] s = str.toCharArray();
    // LinkedHashSet 保證去重且保持插入順序
    Set<String> result = new LinkedHashSet<>();
    dfsRec1(s, 0, new StringBuilder(), result);
    return result.toArray(new String[0]);
  }

  /**
   * 遞迴函式：對 s[i..] 做決策，選擇加入或不加入當前字元。
   *
   * @param s 字元陣列
   * @param index 當前處理位置
   * @param path 臨時儲存目前子序列的 StringBuilder
   * @param result 用於去重並收集所有結果的 Set
   */
  private static void dfsRec1(char[] s, int index, StringBuilder path, Set<String> result) {
    if (index == s.length) {
      result.add(path.toString());
    } else {
      // 選擇包含當前字元
      path.append(s[index]);
      dfsRec1(s, index + 1, path, result);
      // 回溯：移除最後一個字元
      path.deleteCharAt(path.length() - 1);
      // 選擇不包含當前字元
      dfsRec1(s, index + 1, path, result);
    }
  }

  /**
   * 方法二：透過遞迴與固定長度陣列 path 建構子序列，並使用 Set 去重。
   *
   * @param str 原始字串
   * @return 包含所有子序列（含空字串），不重複的字串陣列
   */
  public static String[] generateSubsequencesRec2(String str) {
    char[] s = str.toCharArray();
    Set<String> result = new LinkedHashSet<>();
    dfsRec2(s, 0, new char[s.length], 0, result);
    return result.toArray(new String[0]);
  }

  /**
   * 遞迴函式：使用 path 陣列儲存當前選中的字元，size 表示已選長度。
   *
   * @param s 字元陣列
   * @param index 當前處理位置
   * @param path 用來暫存子序列字元的陣列
   * @param size 已選字元數量
   * @param result 用於去重並收集所有結果的 Set
   */
  private static void dfsRec2(char[] s, int index, char[] path, int size, Set<String> result) {
    if (index == s.length) {
      result.add(new String(path, 0, size));
    } else {
      // 選擇包含 s[index]
      path[size] = s[index];
      dfsRec2(s, index + 1, path, size + 1, result);
      // 選擇不包含 s[index]
      dfsRec2(s, index + 1, path, size, result);
    }
  }

  /** 測試範例：輸出 "abc" 的所有子序列。 */
  public static void main(String[] args) {
    String input = "abc";
    System.out.println("方法一結果：");
    for (String s : generateSubsequencesRec1(input)) {
      System.out.println(s);
    }

    System.out.println("\n方法二結果：");
    for (String s : generateSubsequencesRec2(input)) {
      System.out.println(s);
    }
  }
}
