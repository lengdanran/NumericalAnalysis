package UNIT04;

import java.util.Scanner;

/**
 * @Classname Chordcutmethod
 * @Description TODO
 * @Date 2020/4/24 18:51
 * @Created by ASUS
 */
public class Chordcutmethod {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入初值1：");
        double x0 = scanner.nextDouble();
        System.out.print("请输入初值2：");
        double x1 = scanner.nextDouble();
        System.out.print("请输入最大允许区间长度：");
        double e = scanner.nextDouble();
        System.out.print("请输入最大迭代次数：");
        int N = scanner.nextInt();
        scanner.close();
        double res = getEistimate(x0,x1,e,N);
        System.out.println("快速弦截法方式得到的解为：" + res);
    }

    private static double getEistimate(double x0,double x1,double e,int N) {
        double x2;
        for (int k = 0; true ; k++ ) {
            if(f(x0,x1) == 0){
                throw new RuntimeException("出现奇异情况....");
            }
            x2 = x1 - F(x1)/f(x0,x1);
            if(Math.abs(x2 - x1) < e){
                System.out.println("迭代" + k + "次得到结果");
                return x2;
            }
            if(k == N){
                throw new RuntimeException("迭代失败，已达最大迭代次数N....");
            }
            x0 = x1;
            x1 = x2;
        }
    }

    private static double f(double x0, double x1) {
        return (F(x1) - F(x0))/(x1 - x0);
    }

    private static double F(double x) {
        return Math.log(x) + x*x;
    }

}
