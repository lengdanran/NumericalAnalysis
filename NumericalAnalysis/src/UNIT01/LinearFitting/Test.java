package UNIT01.LinearFitting;

import java.util.Scanner;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/4/22 17:20
 * @Created by ASUS
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("请输入预测点横坐标x0:");
        Scanner scanner = new Scanner(System.in);
        double x0 = scanner.nextDouble();
        System.out.println("预测值为：y = " + LinearFitting.getEstimates(x0));
        scanner.close();
        double [] data = {120,125,130,135,140,145,150,155,160,165,170};
        for (int i = 0; i < data.length ; i++ ) {
            System.out.println("预测值为：y = " + LinearFitting.getEstimates(data[i]));
        }
    }
}
