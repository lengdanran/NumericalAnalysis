package UNIT04;

import java.security.cert.CertStoreSpi;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Classname Dchotomy
 * @Description TODO
 * @Date 2020/4/24 16:12
 * @Created by ASUS
 */
public class Dchotomy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入初始区间下限：");
        double a = scanner.nextDouble();
        System.out.print("请输入初始区间上限：");
        double b = scanner.nextDouble();
        System.out.print("请输入允许最大区间长度：");
        double e = scanner.nextDouble();
        List<Double> result = getEstimates(a,b,e);
        System.out.println("计算得到的零点值为 x = " + result.get(0));
        System.out.println("该点的函数值为 y = " + result.get(1));
    }

    private static List<Double> getEstimates(double a,double b,double e) {
        if(b <= a){
            throw  new RuntimeException("上限<=下限，计算失败...");
        }
        List<Double> reslut = new ArrayList<>();
        double y0,x,y;
        x = y = 0;
        y0 = f(a);
        int i = 0;
        while ( (b - a) >= e ) {
            i++;
            x = (a + b)/2;
            y = f(x);
            if(y*y0 > 0){
                a = x;
            }else {
                b = x;
            }
            if(i == 1000){
                System.out.println("运行超时...检查while循环结构");
                break;
            }
        }
        reslut.add(x);
        reslut.add(y);
        return reslut;
    }

    private static double f(double a) {
        return Math.log(a) + a*a;
    }
}
