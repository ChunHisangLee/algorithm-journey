package class036;

import java.util.ArrayDeque;
import java.util.Deque;

// 二叉樹的最大特殊寬度
// 給定一棵二元樹，計算每層的「位置寬度」，返回所有層中的最大值
// 範例連結 : https://leetcode.cn/problems/maximum-width-of-binary-tree/
public class Code03_WidthOfBinaryTree1 {

  /**
   * 用 BFS 記錄每個節點在完全二元樹中的假想「序號」 - 根節點序號設為 1 - 若節點序號為 idx，則左子為 idx*2，右子為 idx*2+1 同層最寬度 = 最後節點序號 -
   * 首節點序號 + 1
   */
  public static int widthOfBinaryTree(TreeNode root) {
    if (root == null) return 0;

    // 存放當前層節點與其序號
    Deque<NodeIndex> queue = new ArrayDeque<>();
    queue.offer(new NodeIndex(root, 1L));
    int maxWidth = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      long levelHeadIndex = queue.peek().index; // 本層的第一個序號
      long firstIndex = 0, lastIndex = 0;
      for (int i = 0; i < size; i++) {
        NodeIndex ni = queue.poll();
        TreeNode node = ni.node;
        // 減去 levelHeadIndex 後縮小數值範圍，避免 long 過快溢位
        long currIndex = ni.index - levelHeadIndex;
        if (i == 0) firstIndex = currIndex;
        if (i == size - 1) lastIndex = currIndex;

        if (node.left != null) {
          queue.offer(new NodeIndex(node.left, currIndex * 2 + 1));
        }
        if (node.right != null) {
          queue.offer(new NodeIndex(node.right, currIndex * 2 + 2));
        }
      }
      // 計算本層寬度（offset 後的 idx 差值 +1）
      int width = (int) (lastIndex - firstIndex + 1);
      maxWidth = Math.max(maxWidth, width);
    }
    return maxWidth;
  }

  // 內部輔助類：綁定節點與其序號
  private static class NodeIndex {
    final TreeNode node;
    final long index;

    NodeIndex(TreeNode n, long idx) {
      node = n;
      index = idx;
    }
  }

  // 標準二叉樹節點定義
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }
}
