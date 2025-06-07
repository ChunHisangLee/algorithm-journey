package class035;

// setAll 功能的 HashMap
// 要求：支持以下操作，所有操作時間複雜度均為 O(1)
// 1. put(k, v)：設置鍵 k 的值為 v
// 2. get(k)：獲取鍵 k 的當前值，如果不存在則返回 -1
// 3. setAll(v)：將當前所有鍵的值統一設置為 v
// 測試連結 : https://www.nowcoder.com/practice/7c4559f138e74ceb9ba57d76fd169967

import java.io.*;
import java.util.HashMap;

public class Code01_SetAllHashMap {

    // 儲存每個鍵對應的值及其最後修改時間戳
    // map.get(k) = [v, time]
    public static HashMap<Integer, int[]> map = new HashMap<>();

    // 記錄最近一次 setAll 的值與時間
    public static int setAllValue;
    public static int setAllTime;

    // 全局遞增的操作計數，作為時間戳
    public static int cnt;

    /**
     * 將鍵 k 的值設為 v，並更新其時間戳
     */
    public static void put(int k, int v) {
        if (map.containsKey(k)) {
            int[] entry = map.get(k);
            entry[0] = v;
            entry[1] = cnt++;
        } else {
            map.put(k, new int[]{v, cnt++});
        }
    }

    /**
     * 將所有鍵的值統一設置為 v，僅更新全局時間戳與值
     */
    public static void setAll(int v) {
        setAllValue = v;
        setAllTime = cnt++;
    }

    /**
     * 獲取鍵 k 的值：如果 k 不存在，返回 -1；
     * 否則比較 k 的時間戳與 setAllTime
     * - 如果 k 的時間戳更新於 setAllTime 之後，返回其自身值
     * - 否則返回最近一次 setAllValue
     */
    public static int get(int k) {
        if (!map.containsKey(k)) {
            return -1;
        }
        int[] entry = map.get(k);
        return (entry[1] > setAllTime) ? entry[0] : setAllValue;
    }

    public static int n, op, a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        // 連續讀取多組測試（直到 EOF）
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            map.clear();
            setAllValue = 0;
            setAllTime = -1;
            cnt = 0;

            // n 表示操作次數
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                op = (int) in.nval;
                switch (op) {
                    case 1:
                        // put 操作：1 k v
                        in.nextToken();
                        a = (int) in.nval;
                        in.nextToken();
                        b = (int) in.nval;
                        put(a, b);
                        break;
                    case 2:
                        // get 操作：2 k
                        in.nextToken();
                        a = (int) in.nval;
                        out.println(get(a));
                        break;
                    case 3:
                        // setAll 操作：3 v
                        in.nextToken();
                        a = (int) in.nval;
                        setAll(a);
                        break;
                    default:
                        // 不會進入
                        break;
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
