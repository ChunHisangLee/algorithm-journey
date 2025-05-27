package class002;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// 一开始有100个人，每个人都有100元
// 在每一轮都做如下的事情 :
// 每个人都必须拿出1元钱给除自己以外的其他人，给谁完全随机
// 如果某个人在这一轮的钱数为0，那么他可以不给，但是可以接收
// 发生很多很多轮之后，这100人的社会财富分布很均匀吗？
public class Experiment {

  /**
   * 程序入口，运行财富分配实验并计算基尼系数。
   *
   * @param args 命令行参数（未使用）
   */
  public static void main(String[] args) {
    System.out.println("一个社会的基尼系数是一个在0~1之间的小数");
    System.out.println("基尼系数为0代表所有人的财富完全一样");
    System.out.println("基尼系数为1代表有1个人掌握了全社会的财富");
    System.out.println("基尼系数越小，代表社会财富分布越均衡；越大则代表财富分布越不均衡");
    System.out.println("在2022年，世界各国的平均基尼系数为0.44");
    System.out.println("目前普遍认为，当基尼系数到达 0.5 时");
    System.out.println("就意味着社会贫富差距非常大，分布非常不均匀");
    System.out.println("社会可能会因此陷入危机，比如大量的犯罪或者经历社会动荡");
    System.out.println("测试开始");
    int n = 100;
    int t = 1_000_000;
    System.out.println("人数 : " + n);
    System.out.println("轮数 : " + t);
    experiment(n, t);
    System.out.println("测试结束");
  }

  /**
   * 完全按照描述进行实验：每轮每个有钱的人随机给出1元至其他人。
   *
   * <p>最终排序并打印每个人的财富，以及计算该社会的基尼系数。
   *
   * @param n 社会总人数
   * @param t 轮次
   */
  public static void experiment(int n, int t) {
    double[] wealth = new double[n];
    Arrays.fill(wealth, 100);
    boolean[] hasMoney = new boolean[n];
    for (int round = 0; round < t; round++) {
      Arrays.fill(hasMoney, false);
      for (int i = 0; i < n; i++) {
        if (wealth[i] > 0) {
          hasMoney[i] = true;
        }
      }
      for (int i = 0; i < n; i++) {
        if (hasMoney[i]) {
          int other;
          do {
            // 随机选取受赠者，范围 0~n-1
            other = ThreadLocalRandom.current().nextInt(n);
          } while (other == i);
          wealth[i]--;
          wealth[other]++;
        }
      }
    }
    Arrays.sort(wealth);
    System.out.println("列出每个人的财富(贫穷到富有) : ");
    for (int i = 0; i < n; i++) {
      System.out.print((int) wealth[i] + " ");
      if (i % 10 == 9) {
        System.out.println();
      }
    }
    System.out.println();
    System.out.println("这个社会的基尼系数为 : " + calculateGini(wealth));
  }

  /**
   * 计算给定财富数组的基尼系数。
   *
   * @param wealth 已排序或未排序的财富数组
   * @return 基尼系数，范围 [0,1]
   */
  public static double calculateGini(double[] wealth) {
    double sumOfAbsoluteDifferences = 0;
    double sumOfWealth = 0;
    int n = wealth.length;
    for (int i = 0; i < n; i++) {
      sumOfWealth += wealth[i];
      for (int j = 0; j < n; j++) {
        sumOfAbsoluteDifferences += Math.abs(wealth[i] - wealth[j]);
      }
    }
    // 基尼系数公式：绝对差值总和 / (2 * n * 总财富)
    return sumOfAbsoluteDifferences / (2 * n * sumOfWealth);
  }
}
