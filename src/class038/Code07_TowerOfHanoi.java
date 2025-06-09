package class038;

/**
 * 類 Code07_TowerOfHanoi
 *
 * 打印 n 層漢諾塔問題的最優移動軌跡。
 *
 * <p>漢諾塔問題：有三根柱子（from, to, other），初始所有圓盤按大小自上而下疊在柱子 from 上，
 * 每次只能移動一個圓盤，且大盤不能壓在小盤上。</p>
 *
 * <p>時間複雜度 O(2^n)，空間複雜度 O(n)（遞歸調用深度）。</p>
 */
public class Code07_TowerOfHanoi {

    /**
     * 入口方法：打印 n 層漢諾塔的最優移動步驟。
     *
     * @param n 圓盤數量
     */
    public static void hanoi(int n) {
        if (n <= 0) {
            System.out.println("圓盤數量應大於0");
            return;
        }
        moveDisks(n, "左", "右", "中");
    }

    /**
     * 輔助遞歸方法：將 i 個圓盤從柱子 from 移動到柱子 to，使用 other 作為輔助柱。
     *
     * <p>遞歸思路：</p>
     * <ol>
     *   <li>先將 i-1 個圓盤從 from 移到 other</li>
     *   <li>將第 i 個（最大的）圓盤從 from 移到 to</li>
     *   <li>再將 i-1 個圓盤從 other 移到 to</li>
     * </ol>
     *
     * @param i     當前需要移動的圓盤數量
     * @param from  起始柱子名稱
     * @param to    目標柱子名稱
     * @param other 輔助柱子名稱
     */
    private static void moveDisks(int i, String from, String to, String other) {
        if (i == 1) {
            System.out.println("移動圓盤 1 從 " + from + " 到 " + to);
            return;
        }
        moveDisks(i - 1, from, other, to);
        System.out.println("移動圓盤 " + i + " 從 " + from + " 到 " + to);
        moveDisks(i - 1, other, to, from);
    }

    /**
     * 測試示例：n = 3 時打印移動步驟。
     *
     * @param args 命令行參數（不使用）
     */
    public static void main(String[] args) {
        int n = 3;
        hanoi(n);
    }
}
