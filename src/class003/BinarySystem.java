package class003;


// 本文件的实现是用int来举例的
// 对于long类型完全同理
// 不过要注意，如果是long类型的数字num，有64位
// num & (1 << 48)，这种写法不对
// 因为1是一个int类型，只有32位，所以(1 << 48)早就溢出了，所以无意义
// 应该写成 : num & (1L << 48)
public class BinarySystem {

  /**
   * 打印一个int类型的数字的二进制表示（32位），左侧为高位，右侧为低位。
   *
   * <p>例如，若某位置为1，则 (num & (1 << i)) != 0 输出 "1"，否则输出 "0"。 不能使用 (num & (1 << i)) == 1 进行判断，因为位值可能为
   * 2^i 而非 1。
   *
   * @param num 要打印二进制的整数
   */
  public static void printBinary(int num) {
    for (int i = 31; i >= 0; i--) {
      // 下面这句写法，可以改成 :
      // System.out.print((a & (1 << i)) != 0 ? "1" : "0");
      // 但不可以改成 :
      // System.out.print((a & (1 << i)) == 1 ? "1" : "0");
      // 因为a如果第i位有1，那么(a & (1 << i))是2的i次方，而不一定是1
      System.out.print((num & (1 << i)) == 0 ? "0" : "1");
    }
    System.out.println();
  }

  /**
   * 程序入口，展示各种整数的二进制操作示例，并演示逻辑运算与移位。
   *
   * @param args 未使用的命令行参数
   */
  public static void main(String[] args) {
    // 非负数
    int a = 78;
    System.out.println(a);
    printBinary(a);
    System.out.println("===a===");

    // 负数
    int b = -6;
    System.out.println(b);
    printBinary(b);
    System.out.println("===b===");

    // 直接写二进制的形式定义变量
    int c = 0b1001110;
    System.out.println(c);
    printBinary(c);
    System.out.println("===c===");

    // 直接写十六进制的形式定义变量
    // 0100 -> 4
    // 1110 -> e
    // 0x4e -> 01001110
    int d = 0x4e;
    System.out.println(d);
    printBinary(d);
    System.out.println("===d===");

    // ~、相反数
    System.out.println(a);
    printBinary(a);
    printBinary(~a);
    int e = ~a + 1;
    System.out.println(e);
    printBinary(e);
    System.out.println("===e===");

    // int、long的最小值，取相反数、绝对值，都是自己
    int f = Integer.MIN_VALUE;
    System.out.println(f);
    printBinary(f);
    System.out.println(-f);
    printBinary(-f);
    System.out.println(~f + 1);
    printBinary(~f + 1);
    System.out.println("===f===");

    // | & ^
    int g = 0b0001010;
    int h = 0b0001100;
    printBinary(g | h);
    printBinary(g & h);
    printBinary(g ^ h);
    System.out.println("===g、h===");

    // 可以这么写 : int num = 3231 | 6434;
    // 可以这么写 : int num = 3231 & 6434;
    // 不能这么写 : int num = 3231 || 6434;
    // 不能这么写 : int num = 3231 && 6434;
    // 因为 ||、&& 是 逻辑或、逻辑与，只能连接boolean类型
    // 不仅如此，|、& 连接的两侧一定都会计算
    // 而 ||、&& 有穿透性的特点
    System.out.println("test1测试开始");
    boolean test1 = returnTrue() | returnFalse();
    System.out.println("test1结果，" + test1);
    System.out.println("test2测试开始");
    boolean test2 = returnTrue() || returnFalse();
    System.out.println("test2结果，" + test2);
    System.out.println("test3测试开始");
    boolean test3 = returnFalse() & returnTrue();
    System.out.println("test3结果，" + test3);
    System.out.println("test4测试开始");
    boolean test4 = returnFalse() && returnTrue();
    System.out.println("test4结果，" + test4);
    System.out.println("===|、&、||、&&===");

    // <<
    int i = 0b0011010;
    printBinary(i);
    printBinary(i << 1);
    printBinary(i << 2);
    printBinary(i << 3);
    System.out.println("===i << ===");

    // 非负数 >> >>>，效果一样
    printBinary(i);
    printBinary(i >> 2);
    printBinary(i >>> 2);
    System.out.println("===i >> >>>===");

    // 负数 >> >>>，效果不一样
    int j = 0b11110000000000000000000000000000;
    printBinary(j);
    printBinary(j >> 2);
    printBinary(j >>> 2);
    System.out.println("===j >> >>>===");

    // 非负数移位等同于乘除以2的幂
    int k = 10;
    System.out.println(k);
    System.out.println(k << 1);
    System.out.println(k << 2);
    System.out.println(k << 3);
    System.out.println(k >> 1);
    System.out.println(k >> 2);
    System.out.println(k >> 3);
    System.out.println("===k===");
  }

  /**
   * 返回 true 并打印提示。
   *
   * @return true
   */
  public static boolean returnTrue() {
    System.out.println("进入了returnTrue函数");
    return true;
  }

  /**
   * 返回 false 并打印提示。
   *
   * @return false
   */
  public static boolean returnFalse() {
    System.out.println("进入了returnFalse函数");
    return false;
  }
}
