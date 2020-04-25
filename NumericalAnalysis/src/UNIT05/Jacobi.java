package UNIT05;

import java.util.Scanner;

/**
 * @Classname Jacobi
 * @Description TODO
 * @Date 2020/4/24 20:04
 * @Created by ASUS
 */
public class Jacobi {
    private static double[][] datas = {
            {10.0,-1.0,-2.0,7.2},
            {-1.0,10.0,-2.0,8.3},
            {-1.0,-1.0,5.0,4.2}
    };
    private static double[] x = {0.0,0.0,0.0};
    private static double w = 1.2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入最大允许区间长度e：");
        double e = scanner.nextDouble();
        System.out.print("请输入最大迭代次数N:");
        int N = scanner.nextInt();
        scanner.close();
        double[] result = getEstimates(datas,e,N,x);
        System.out.println("x1=" + result[0] + "\tx2=" + result[1] + "\tx3=" + result[2]);
    }
    
    private static double[] getEstimates(double[][] datas, double e,int N,double[] x) {
        int length = datas[0].length;
        double [] y = new double[x.length];
        for (int k = 1 ; true; k++ ) {
            for (int i = 0; i < x.length ; i++ ) {
//                y[i] = (datas[i][length-1] - F(i,x,datas))/datas[i][i];
                y[i] = (datas[i][length-1] - F(i,x,y,datas))/datas[i][i];
                y[i] = w*y[i] + (1 - w)*x[i];
            }
            if(isConditionsEstablished(x,y,e)){
                System.out.println("迭代" + k + "次得到结果");
                return y;
            }
            if( k == N ){
                throw new RuntimeException("达到最大迭代次数，仍未得到预期结果...");
            }
//            Jacobi 方式
            for (int i = 0; i < x.length; i++ ) {
                x[i] = y[i];
            }
        }
    }

    private static double F(int i, double[] x,double[] y, double[][] datas) {
        double sum = 0;
        for (int j = 0; j < datas[0].length - 1 ; j++ ) {
            if(j < i){
                sum += datas[i][j]*y[j];
            }else if(j > i){
                sum += datas[i][j]*x[j];
            }
        }
        return sum;
    }

    private static double F(int i, double[] x, double[][] datas) {
        double sum = 0;
        for (int j = 0; j < datas[0].length - 1 ; j++ ) {
            if(j != i){
                sum += datas[i][j]*x[j];
            }
        }
        return sum;
    }

    //    max|xi - yi| < e : true
    private static boolean isConditionsEstablished(double[] x,double[] y,double e) {
        boolean flag = true;
        for (int i = 0 ; i < x.length ; i ++ ) {
            if(Math.abs(x[i] - y[i]) >= e){
                flag = false;
            }
        }
        return flag;
    }
}
