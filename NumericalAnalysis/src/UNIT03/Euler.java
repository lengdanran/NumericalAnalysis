package UNIT03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Euler
 * @Description TODO
 * @Date 2020/4/24 8:47
 * @Created by ASUS
 */
public class Euler {
    public static void main(String[] args) {
        List<List<Double>> result = getEstimates(0.0,1.0,0.2,10);
        List<Double> xi = result.get(0);
        List<Double> yi = result.get(1);
        List<Double> reals = result.get(2);
        List<Double> miscount = result.get(3);
        for (int i = 0; i < xi.size(); i++ ) {
            System.out.println("x"+i+"="+xi.get(i)+"\t"+"y"+i+"="+yi.get(i)+"\t"+"real="+reals.get(i)+"\t"+"mis="+miscount.get(i));
        }
    }
    public static List<List<Double>> getEstimates(double x0,double y0,double h,int N) {
        List<List<Double>> result = new ArrayList<>();
        List<Double> xi = new ArrayList<>();
        List<Double> yi = new ArrayList<>();
        List<Double> reals = new ArrayList<>();
        List<Double> miscount = new ArrayList<>();
        double x1,y1,yp,yc,real,mis,K1,K2,K3,K4;
        for (int i = 0 ; i < N ; i++ ) {
            x1 = x0 + h;
//            yp = y0 + h*f(x0,y0);
//            yc = y0 + h*f(x1,yp);
//            y1 = (yp + yc)/2;
            K1 = f(x0,y0);
            K2 = f(x0+h/2,y0+h*K1/2);
            K3 = f(x0+h/2,y0+h*K2/2);
            K4 = f(x1,y0+h*K3);
            y1 = y0 + h*(K1 + 2*K2 + 2*K3 + K4)/6;


            real = getReal(x0);
            mis = Math.abs(real - y1);
            xi.add(x1);
            yi.add(y1);
            reals.add(real);
            miscount.add(mis);
            x0 = x1;
            y0 = y1;
        }
        result.add(xi);
        result.add(yi);
        result.add(reals);
        result.add(miscount);
        return result;
    }

    private static double getReal(double x) {
//        return (Math.exp(-x)*x*x)/2 + 1.0;
        return Math.exp(x*x);
    }

    private static double f(double x0, double y0) {
//        return x0*Math.exp(-x0) - y0 + 1;
        return 2*x0*y0;
    }
}
