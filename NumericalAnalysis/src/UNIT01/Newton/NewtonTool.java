package UNIT01.Newton;

import Data.InitDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname NewtonTool
 * @Description TODO
 * @Date 2020/4/22 15:13
 * @Created by ASUS
 */
public class NewtonTool {

    public NewtonTool() {}

    private static double f(double x) {
        return Math.sin(x);
    }
//  累乘积：(x-x0)***(x-xn)
    private static double Multiplication(double x,int n,List<Double> xi) {
        double result = 1.0 ;
        for (int i = 0 ; i < n; i++ ) {
            result *= (x - xi.get(i));
        }
        return result;
    }
    //  累乘积：(xk-x0)***(xk-xj)...j!=k
    private static double Multiplication(int n, int k,List<Double> xi) {
        double result = 1.0 ;
        for (int j = 0 ; j <= n; j++ ) {
            if(j != k){
                result *= (xi.get(k) - xi.get(j));
            }
        }
        return result;
    }
//  f(x0,x1,...,xn)  n阶差商
    private static double Quotient(int n,List<Double> xi) {
        double result = 0.0;
        for (int k = 0 ; k <= n ; k++) {
            result += f(xi.get(k))/Multiplication(n,k,xi);
        }
        return result;
    }
//  得到估计值
    public static double getEstimates(double x0) {
        double result = 0;
        List<Double> xi = InitDataSource.getInitValues();
        for (int i = 0 ; i < xi.size() ; i++ ) {
            result += Quotient(i,xi)*Multiplication(x0,i,xi);
        }
        return result;
    }
}
