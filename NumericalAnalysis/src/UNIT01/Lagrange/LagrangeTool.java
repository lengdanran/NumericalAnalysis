package UNIT01.Lagrange;

import Data.InitDataSource;

import java.util.List;

/**
 * @Classname LagrangeTool
 * @Description the functions of the Lagrange methods
 * @Date 2020/4/22 10:55
 * @Created by Jason
 * 用拉格朗日（Lagrange）插值多项式计算f(x0)的近似值
 * 以sin(x)为研究函数
 */
public class LagrangeTool {

//    real values
    private double f(double x) {
        return Math.sin(x);
    }
//    获取Lagrange的近似估计值
    public double getEstimates(double x0){
        List<Double> xi = InitDataSource.getInitValues();
        int length = xi.size();
        double estimates = 0;
        for (int k = 0 ; k < length ; k++ ) {
            estimates += f(xi.get(k))*Lk(x0,k,xi);
        }
        return estimates;
    }

    private static double Lk(double x,int k,List<Double> xi) {
        double value = 1.0;
        for (int j = 0; j < xi.size(); j++) {
            if (j != k) {
                value *= (x - xi.get(j)) / (xi.get(k) - xi.get(j));
            }
        }
        return value;
    }

}
