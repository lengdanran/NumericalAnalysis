package UNIT01.Newton;

import UNIT04.Newton;

import java.util.Scanner;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/4/22 16:01
 * @Created by Jason
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("研究函数f(x)=sin(x),请输入点x0的值：");
        Scanner scanner = new Scanner(System.in);
        double x0 = scanner.nextDouble();
        double value = NewtonTool.getEstimates(x0);
        System.out.println("用牛顿(Newton)插值公式计算f("+ x0 +")的近似值为：" + value);
        System.out.println( "f("+ x0 +")精确值为：" + Math.sin(x0));
        System.out.println("误差：" + Math.abs(Math.sin(x0) - value));
        double datas[] = {0.0,0.2,0.4,0.6,0.8,1.0,1.2,1.4,1.6,1.8,2.0,2.2,2.4,2.6,2.8,3.0};
        for (int i = 0 ;i < datas.length;i++) {
            System.out.println( "f("+ datas[i] +")精确值为：" + NewtonTool.getEstimates(datas[i]));
        }
    }
}
