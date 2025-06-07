package class036;

import java.util.Map;
import java.util.HashMap;

// 利用先序與中序遍歷序列構造二叉樹
// 前序序列可定位根節點，中序序列可區分左右子樹
// 測試連結 : https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Code07_PreorderInorderBuildBinaryTree {

  /**
   * 構建二叉樹入口
   *
   * @param preorder 先序遍歷序列
   * @param inorder 中序遍歷序列
   * @return 構建後的樹根
   */
  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || inorder == null || preorder.length != inorder.length) {
      return null;
    }
    // 建立中序值->索引映射，快速定位根在中序序列的位置
    Map<Integer, Integer> inIndexMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inIndexMap.put(inorder[i], i);
    }
    // 以遞迴構建子樹
    return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inIndexMap);
  }

  /**
   * 遞迴構建子樹
   *
   * @param pre 先序陣列
   * @param preL 先序子範圍左界
   * @param preR 先序子範圍右界
   * @param in 中序陣列
   * @param inL 中序子範圍左界
   * @param inR 中序子範圍右界
   * @param inMap 中序值->索引映射
   * @return 構建後子樹根節點
   */
  private static TreeNode build(
      int[] pre, int preL, int preR, int[] in, int inL, int inR, Map<Integer, Integer> inMap) {
    if (preL > preR) {
      return null;
    }
    // 先序首位為根
    int rootVal = pre[preL];
    TreeNode root = new TreeNode(rootVal);
    // 中序序列定位根，計算左子樹大小
    int idx = inMap.get(rootVal);
    int leftSize = idx - inL;
    // 構建左子樹：先序 [preL+1, preL+leftSize], 中序 [inL, idx-1]
    root.left = build(pre, preL + 1, preL + leftSize, in, inL, idx - 1, inMap);
    // 構建右子樹：先序 [preL+leftSize+1, preR], 中序 [idx+1, inR]
    root.right = build(pre, preL + leftSize + 1, preR, in, idx + 1, inR, inMap);
    return root;
  }

  // 二叉樹節點定義
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      this.val = v;
    }
  }
}
