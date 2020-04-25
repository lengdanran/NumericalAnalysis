package UNIT04;

import java.util.Scanner;

/**
 * @Classname Newton
 * @Description TODO
 * @Date 2020/4/24 17:35
 * @Created by ASUS
 */
public class Newton {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入近似解：");
        double x = scanner.nextDouble();
        System.out.print("请输入最大允许区间长度：");
        double e = scanner.nextDouble();
        System.out.print("请输入最大迭代次数：");
        int N = scanner.nextInt();
        scanner.close();
        double res = getEistimate(x,e,N);
        System.out.println("Newton方式得到的解为：" + res);
    }

    private static double getEistimate(double x,double e,int N) {
        double result,x1;
        for (int k = 1; true ; k++ ) {
            if(f(x) == 0){
                throw new RuntimeException("出现奇异情况....");
            }
            x1 = x - F(x)/f(x);
            if(Math.abs(x1 - x) < e ){
                System.out.println("迭代" + k + "次得到结果");
                return x1;
            }
            if(k == N){
                throw new RuntimeException("迭代失败，已达最大迭代次数N....");
            }
            x = x1;
        }
    }

    private static double F(double x) {
        return Math.log(x) + x*x;
    }

    private static double f(double x) {
        return 2*x + 1/x;
    }

}
