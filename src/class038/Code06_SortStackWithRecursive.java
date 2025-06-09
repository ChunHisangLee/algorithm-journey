package class038;

import java.util.Stack;

/**
 * 類 Code06_SortStackWithRecursive
 *
 * 提供一個使用遞迴方式對整數棧（Stack<Integer>）進行排序的方法，
 * 排序後從棧頂到棧底元素按升序排列。
 *
 * <p>限制：僅可使用 Stack 的 push、pop、isEmpty 三個方法和遞迴函數，
 * 且返回值最多為單個整數，不能使用其他容器或陣列。</p>
 *
 * <p>測試示例：</p>
 * <pre>
 *   原始棧（自底至頂）：[1,5,4,5,3,2,3,1,4,2]
 *   排序後 pop 輸出（自棧頂到棧底）：2,3,3,4,4,5,5,1,1,?（自底不打印）
 * </pre>
 */
public class Code06_SortStackWithRecursive {

    /**
     * 遞迴排序棧中的所有元素。
     *
     * <p>核心思路：重複多輪將當前未排序區域的最大值移到底部，
     * 縮小未排序深度直至排序完成。</p>
     *
     * <p>時間複雜度 O(n^2)，空間複雜度 O(n)（遞歸棧深度）。</p>
     *
     * @param stack 待排序的整數棧
     */
    public static void sort(Stack<Integer> stack) {
        int depth = getDepth(stack);
        // 無序區域深度依次減少
        while (depth > 0) {
            int max = findMax(stack, depth);
            int count = countMax(stack, depth, max);
            sinkMax(stack, depth, max, count);
            depth -= count;
        }
    }

    /**
     * 計算棧的深度（元素數量），不改變棧內容。
     *
     * @param stack 整數棧
     * @return 棧內元素總數
     */
    public static int getDepth(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        int top = stack.pop();
        int depth = getDepth(stack) + 1;
        stack.push(top);
        return depth;
    }

    /**
     * 在棧頂開始，往下數 depth 層，找出該範圍內的最大值，不改變棧內容。
     *
     * @param stack 整數棧
     * @param depth 待檢查的深度
     * @return 最大元素
     */
    public static int findMax(Stack<Integer> stack, int depth) {
        if (depth == 0) {
            return Integer.MIN_VALUE;
        }
        int top = stack.pop();
        int restMax = findMax(stack, depth - 1);
        int max = Math.max(top, restMax);
        stack.push(top);
        return max;
    }

    /**
     * 統計指定範圍內最大值出現的次數，不改變棧內容。
     *
     * @param stack 整數棧
     * @param depth 待檢查的深度
     * @param max   最大值
     * @return 出現次數
     */
    public static int countMax(Stack<Integer> stack, int depth, int max) {
        if (depth == 0) {
            return 0;
        }
        int top = stack.pop();
        int restCount = countMax(stack, depth - 1, max);
        int total = restCount + (top == max ? 1 : 0);
        stack.push(top);
        return total;
    }

    /**
     * 將指定範圍內的所有最大值「下沉」到底部，保持其他元素相對順序。
     *
     * @param stack 整數棧
     * @param depth 待處理的深度
     * @param max   最大值
     * @param count 最大值出現次數
     */
    public static void sinkMax(Stack<Integer> stack, int depth, int max, int count) {
        if (depth == 0) {
            // 將所有最大值推回棧頂（實際下沉至底部）
            for (int i = 0; i < count; i++) {
                stack.push(max);
            }
        } else {
            int top = stack.pop();
            sinkMax(stack, depth - 1, max, count);
            if (top != max) {
                stack.push(top);
            }
        }
    }

    /**
     * 隨機生成測試用的整數棧。
     *
     * @param n 元素數量
     * @param v 最大值範圍（不含）
     * @return 隨機棧
     */
    public static Stack<Integer> randomStack(int n, int v) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push((int) (Math.random() * v));
        }
        return stack;
    }

    /**
     * 驗證棧是否已從棧頂到棧底呈升序排列。
     *
     * @param stack 整數棧
     * @return 若升序則返回 true，否則 false
     */
    public static boolean isSorted(Stack<Integer> stack) {
        int prev = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (prev > cur) {
                return false;
            }
            prev = cur;
        }
        return true;
    }

    /**
     * 測試示例：包含固定測試與隨機測試。
     */
    public static void main(String[] args) {
        // 固定測試
        Stack<Integer> test = new Stack<>();
        test.push(1); test.push(5); test.push(4); test.push(5);
        test.push(3); test.push(2); test.push(3); test.push(1);
        test.push(4); test.push(2);
        sort(test);
        System.out.println("固定測試結果：");
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

        // 隨機測試
        System.out.println("隨機測試開始");
        int N = 20, V = 20, times = 20000;
        for (int i = 0; i < times; i++) {
            Stack<Integer> stk = randomStack((int) (Math.random() * N), V);
            sort(stk);
            if (!isSorted(stk)) {
                System.out.println("出錯了！");
                break;
            }
        }
        System.out.println("隨機測試結束");
    }
}
