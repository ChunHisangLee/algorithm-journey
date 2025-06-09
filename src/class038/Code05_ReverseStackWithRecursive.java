package class038;

import java.util.Stack;

/**
 * 類 Code05_ReverseStackWithRecursive
 *
 * 提供一個使用遞迴方式反轉整數棧（Stack&lt;Integer&gt;）的方法，無需額外數據結構。
 * <p>
 * 透過不斷移除棧底元素，再依序將其推回棧頂，達到反轉效果。
 * </p>
 *
 * <p>測試示例：</p>
 * <pre>
 *   輸入棧（自底至頂）：1,2,3,4,5
 *   輸出（依次 pop）：1,2,3,4,5（已反轉）
 * </pre>
 */
public class Code05_ReverseStackWithRecursive {

    /**
     * 遞迴反轉棧中所有元素。
     *
     * <p>核心思路：先遞迴移除並取得棧底元素 bottom，
     * 對剩餘元素繼續反轉，最後將 bottom 放回棧頂。</p>
     *
     * <p>時間複雜度 O(n^2)：每次遞迴都要走訪剩餘元素，
     * 空間複雜度 O(n)（遞歸調用棧深度）。</p>
     *
     * @param stack 待反轉的整數棧
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int bottom = bottomOut(stack);
        reverse(stack);
        stack.push(bottom);
    }

    /**
     * 移除並返回棧底元素，同時保持原有順序。
     *
     * <p>遞迴彈出頂部元素直到棧空，最後返回最底元素，
     * 回溯時將之前彈出的元素依序推回，使原棧頂元素回到原位。</p>
     *
     * <p>時間複雜度 O(n)，空間複雜度 O(n)。</p>
     *
     * @param stack 操作的整數棧
     * @return 原棧底元素
     */
    public static int bottomOut(Stack<Integer> stack) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            // top 為原本的棧底
            return top;
        } else {
            // 先遞迴取出更底層元素
            int bottom = bottomOut(stack);
            // 回溯時將本層彈出值重新壓入
            stack.push(top);
            return bottom;
        }
    }

    /**
     * 測試示例：構造棧並反轉後依次輸出。
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        // 初始化棧：底 -> 頂 = 1,2,3,4,5
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }

        reverse(stack);

        // 輸出反轉後棧內元素（應依次為原棧底到棧頂）
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
