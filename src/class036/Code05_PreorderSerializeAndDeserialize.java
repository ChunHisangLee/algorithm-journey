package class036;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 二叉樹的先序序列化與反序列化
// 要求：可將二叉樹轉為字串並還原，支援任意結構
// 測試連結 : https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Code05_PreorderSerializeAndDeserialize {

  // 不提交这个类
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }

  // 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化
  // 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
  // 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
  // 比如如下两棵树
  //         __2
  //        /
  //       1
  //       和
  //       1__
  //          \
  //           2
  // 補足空位置的中序遍歷結果都是 { null, 1, null, 2, null }
  // 提交這個類
  public static class Codec {
    private static final String NULL = "#";
    private static final String SEP = ",";

    /**
     * 將二叉樹轉為字串
     *
     * @param root 樹根
     * @return 序列化後字串
     */
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      buildString(root, sb);
      return sb.toString();
    }

    // 先序遞迴構建字串
    private void buildString(TreeNode node, StringBuilder sb) {
      if (node == null) {
        sb.append(NULL).append(SEP);
      } else {
        sb.append(node.val).append(SEP);
        buildString(node.left, sb);
        buildString(node.right, sb);
      }
    }

    /**
     * 將字串還原為二叉樹
     *
     * @param data 序列化字串
     * @return 還原後的樹根
     */
    public TreeNode deserialize(String data) {
      if (data == null || data.isEmpty()) return null;
      String[] tokens = data.split(SEP);
      Queue<String> queue = new LinkedList<>(Arrays.asList(tokens));
      return buildTree(queue);
    }

    // 從隊列中按先序遞迴構建樹
    private TreeNode buildTree(Queue<String> queue) {
      String val = queue.poll();
      if (NULL.equals(val)) {
        return null;
      }
      TreeNode node = new TreeNode(Integer.parseInt(val));
      node.left = buildTree(queue);
      node.right = buildTree(queue);
      return node;
    }
  }
}
