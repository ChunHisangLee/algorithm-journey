package class036;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 二叉樹按層序列化和反序列化
// 測試連結 : https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Code06_LevelorderSerializeAndDeserialize {

  // 標準二叉樹節點定義
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      this.val = v;
    }
  }

  /**
   * Codec 提供按層(廣度優先)的 serialize 和 deserialize 方法 - 序列化：使用 BFS，將節點值或 "#"(空) 加入列表，以 "," 連接 - 反序列化：先
   * split 字串為陣列，再按隊列構建樹
   */
  public static class Codec {
    private static final String NULL = "#";
    private static final String SEP = ",";

    /**
     * 將二叉樹按層序列化為字串
     *
     * @param root 樹根
     * @return 序列化後的字串
     */
    public String serialize(TreeNode root) {
      if (root == null) return "";
      StringBuilder sb = new StringBuilder();
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      sb.append(root.val).append(SEP);
      while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          sb.append(node.left.val).append(SEP);
          queue.offer(node.left);
        } else {
          sb.append(NULL).append(SEP);
        }
        if (node.right != null) {
          sb.append(node.right.val).append(SEP);
          queue.offer(node.right);
        } else {
          sb.append(NULL).append(SEP);
        }
      }
      return sb.toString();
    }

    /**
     * 將字符串還原為二叉樹
     *
     * @param data serialize 生成的字串
     * @return 還原後的樹根
     */
    public TreeNode deserialize(String data) {
      if (data == null || data.isEmpty()) return null;
      String[] tokens = data.split(SEP);
      int idx = 0;
      TreeNode root = createNode(tokens[idx++]);
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node == null) continue;
        node.left = createNode(tokens[idx++]);
        node.right = createNode(tokens[idx++]);
        queue.offer(node.left);
        queue.offer(node.right);
      }
      return root;
    }

    // 將 token 轉為節點，"#" 代表 null
    private TreeNode createNode(String val) {
      return NULL.equals(val) ? null : new TreeNode(Integer.parseInt(val));
    }
  }
}
