package class037;

// 修剪二叉搜尋樹，使所有節點值保持在 [low, high] 範圍內。
// 對於每個節點，若其值低於 low，則整個左子樹都可捨棄；
// 若其值高於 high，則整個右子樹都可捨棄。
// 測試連結 : https://leetcode.cn/problems/trim-a-binary-search-tree/
public class Code06_TrimBinarySearchTree {

  /**
   * 遞迴修剪 BST： 1. 若當前節點為 null，返回 null 2. 若 cur.val < low，所有左子節點均 < low，應修剪掉 -> 返回修剪右子樹的結果 3. 若
   * cur.val > high，所有右子節點均 > high，應修剪掉 -> 返回修剪左子樹的結果 4. 否則節點值在範圍內，遞迴修剪其左右子樹，並返回 cur 本身
   *
   * <p>時間複雜度 O(n)，空間複雜度 O(h)
   *
   * @param cur 當前遍歷到的節點
   * @param low 值域下界
   * @param high 值域上界
   * @return 經過修剪後的子樹根節點
   */
  public static TreeNode trimBST(TreeNode cur, int low, int high) {
    if (cur == null) {
      return null;
    }
    if (cur.val < low) {
      // 當前節點及其左子樹均不符合條件，跳過到右子樹
      return trimBST(cur.right, low, high);
    }
    if (cur.val > high) {
      // 當前節點及其右子樹均不符合條件，跳過到左子樹
      return trimBST(cur.left, low, high);
    }
    // 節點值在 [low, high] 之間，保留並遞迴修剪左右子樹
    cur.left = trimBST(cur.left, low, high);
    cur.right = trimBST(cur.right, low, high);
    return cur;
  }

  // 標準 BST 節點定義
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      this.val = v;
    }
  }
}
