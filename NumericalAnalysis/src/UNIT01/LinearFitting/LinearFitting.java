package UNIT01.LinearFitting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Classname LinearFitting
 * @Description TODO
 * @Date 2020/4/22 16:52
 * @Created by ASUS
 */
public class LinearFitting {

    private static Double [] data_x = {165.0,123.0,150.0,123.0,141.0} ;
    private static Double [] data_y = {187.0,126.0,172.0,125.0,148.0} ;

    private static List<Double> x = Arrays.asList(data_x);
    private static List<Double> y = Arrays.asList(data_y);

//    输入集合数据，并累计求和
    private static double getSumOf(List<Double> datas) {
        double restlt = 0.0;
        for (int i = 0 ; i < datas.size() ; i++ ) {
            restlt += datas.get(i);
        }
        return restlt;
    }
//  整合数据项
    private static List<Double> IntegrateData(List<Double> x,List<Double> y) {
        if(x.size() != y.size()){
            throw new RuntimeException("传入的两个数据集合长度不一致") ;
        }
        List<Double> res = new ArrayList<>() ;
        for (int i = 0 ; i < x.size(); i++ ) {
            res.add(x.get(i)*y.get(i));
        }
        return res;
    }
//  返回得到的拟合曲线 y = a + bx;的a与b的值，存在集合中
    public static List<Double> getLineCoefficient() {
        double a,b;
        b = getB();
        a = getA(b);
        List<Double> res = new ArrayList<>();
        res.add(a);
        res.add(b);
//        System.out.println("线性拟合曲线为：y = " +
//                res.get(0) + " + " + res.get(1) + "x");
        return res;
    }
//  获得系数a
    private static double getA(double b) {
        double N,T,S;
        N = y.size();
        T = getSumOf(y);
        S = getSumOf(x);
        return (T - b*S)/N;
    }
//  获得系数b
    private static double getB() {
        double b =0;
        double N,F,T,S,K;
        N = y.size();
        F = getSumOf(IntegrateData(x,y));
        T = getSumOf(y);
        S = getSumOf(x);
        K = getSumOf(IntegrateData(x,x));
        b = (N*F - T*S)/(N*K - S*S);
        return b;
    }

    //  得到估计值
    public static double getEstimates(double x0) {
        List<Double> coefficient = getLineCoefficient();
        return coefficient.get(0) + coefficient.get(1)*x0;
    }
}
