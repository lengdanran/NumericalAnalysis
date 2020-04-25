package UNIT02.math;

import java.util.Scanner;

/**
 * @Classname MainTest
 * @Description TODO
 * @Date 2020/4/23 14:39
 * @Created by Jason
 */
public class MainTest {
    public static void main(String[] args) {
        Tool tool = new Tool();
        Scanner scanner = new Scanner(System.in);
        double x1 = TiXing(scanner,tool);
        tool = new Tool();
        double x2 = Romberg(scanner,tool);
        scanner.close();
        double accurate = Math.log(tool.getB())-Math.log(tool.getA());
        System.out.println("积分的准确值为ln("+ tool.getB() +")= " + accurate);
        System.out.println("方式1误差：" + Math.abs((x1-accurate)));
        System.out.println("方式2误差：" + Math.abs((x2-accurate)));
    }
    private static double Romberg(Scanner scanner,Tool tool) {
        double e = scan(tool,scanner);
        long timeStart = System.currentTimeMillis();
        tool.setH(tool.getB() - tool.getA());//h = b -a
        tool.setT1((tool.getH()/2)*(f(tool.getA()) + f(tool.getB())));//t1 = h/2*(f(a)+f(b))
        for (int i = 0;true;i++){
            if(i != 0){
                operation(tool);
            }
            operation0(tool);
            if(i == 0) {
                operation1(tool);
                tool.setC2(tool.getS2() + (tool.getS2() - tool.getS1())/15);
                tool.setC1(tool.getC2());
                operation1(tool);
                tool.setR2(tool.getC2() + (tool.getC2() - tool.getC1())/63);
                tool.setC1(tool.getC2());
                operation1(tool);
                tool.setR1(tool.getR2());
                tool.setC1(tool.getC2());
                operation1(tool);
            }
            if(i != 0){
                tool.setC2(tool.getS2() + (tool.getS2() - tool.getS1())/15);
                tool.setR2(tool.getC2() - (tool.getC2() - tool.getC1())/63);
                if( Math.abs( (tool.getR1() - tool.getR2()) ) >= e ){
                    tool.setR1(tool.getR2());
                }else {
                    break;
                }
            }
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("计算用时（ms）:" + (timeEnd - timeStart));
        System.out.println("Romberg方式进行计算后的积分值为：" + tool.getR2());
        return tool.getR2();
    }

    private static void operation0(Tool tool) {
        tool.setS(0);
        tool.setX(tool.getA() + tool.getH()/2);
        while (tool.getX() < tool.getB()) {
            tool.setS(tool.getS() + f(tool.getX()));
            tool.setX(tool.getX() + tool.getH());
        }
        tool.setT2((tool.getT1() + tool.getS()*tool.getH())/2);
        tool.setS2(tool.getT2() + (tool.getT2() - tool.getT1())/3);
    }

    private static void operation1(Tool tool) {
        tool.setH(tool.getH()/2);
        tool.setT1(tool.getT2());
        tool.setS1(tool.getS2());
        operation0(tool);
    }

    private static void operation(Tool tool) {
        tool.setC1(tool.getC2());
        tool.setH(tool.getH()/2);
        tool.setT1(tool.getT2());
        tool.setS1(tool.getS2());
    }

    private static double TiXing(Scanner scanner,Tool tool) {
        double e = scan(tool,scanner);
        long timeStart = System.currentTimeMillis();
        double a = tool.getA();
        double b = tool.getB();
        double h = b - a;
        double S , x;
        double T1,T2;
        T1 =  (h/2)*(f(a) + f(b));
        do {
            S = 0;
            x = a + h/2;
            while (x < b){
                S = S + f(x);
                x = x + h;
            }
            T2 = (T1 + S*h)/2;
            h = h/2;
            if(Math.abs((T1-T2))>=e){
                h = h/2;
                T1 = T2;
            }else {
                break;
            }
        }while (true);
        long timeEnd = System.currentTimeMillis();
        System.out.println("计算用时（ms）:" + (timeEnd - timeStart));
        System.out.println("计算后的积分值为：" + T2);
        return T2;
    }

    private static double scan(Tool tool,Scanner scanner) {
        System.out.print("请输入积分下限：");
        double a = scanner.nextDouble();
        System.out.print("请输入积分上限：");
        double b = scanner.nextDouble();
        tool.setA(a);
        tool.setB(b);
        System.out.println("请输入精度e:");
        double e = scanner.nextDouble();
        tool.setH(b - a);
        tool.setT1((tool.getH()/2)*(f(a) + f(b)));
        return e;
    }

    private static double f(double a) {
        if(a == 0) throw new RuntimeException("函数f(x)中x应该大于0！！");
        return 1/a;
    }
}
