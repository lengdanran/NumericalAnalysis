# 问题引入
利用计算机求解线性方程组，精度要求为$\varepsilon=0.001$：
$$
\left\{\begin{array}{l}10x_1-x_2-2x_3=7.2\\-x_1+10x_2-2x_3=8.3\\-x_1-x_2+5x_3=4.2\end{array}\right.
$$
精确解为：
$$
\left\{\begin{array}{l}x_1^\ast=1.1\\x_2^\ast=1.2\\x_3^\ast=1.3\end{array}\right.
$$
# 雅可比(Jacobi)迭代公式
考察一般线性方程组：
$$
{\textstyle\sum_{j=1}^n}a_{ij}x_j=b_i,i\in N^\ast(1)
$$
将式$(1)$改写为：
$$
x_i=\frac{b_i-\sum_{j=1}^na_{ij}x_j}{a_{ii}},i\in N^\ast,(j≠i).(2)
$$
根据上述两式，建立雅可比(Jacobi)迭代公式：
$$
x_i^{(k+1)}=\frac{b_i-\sum_{j=1}^na_{ij}x_j^{(k)}}{a_{ii}},i\in N^\ast,(j≠i).
$$
## 算法描述
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425174305619.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## 程序运行结果截图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425174353303.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)


## Source Codes([github](https://github.com/Jason-danran0612/NumericalAnalysis))

```java
/**
 * @Classname Jacobi
 * @Description TODO
 * @Date 2020/4/24 19:04
 * @Created by Jason
 */
public class Jacobi {
    private static double[][] datas = {
            {10.0,-1.0,-2.0,7.2},
            {-1.0,10.0,-2.0,8.3},
            {-1.0,-1.0,5.0,4.2}
    };
    private static double[] x = {0.0,0.0,0.0};

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
                y[i] = (datas[i][length-1] - F(i,x,datas))/datas[i][i];
            }
            if(isConditionsEstablished(x,y,e)){
                System.out.println("迭代" + k + "次得到结果");
                return y;
            }
            if( k == N ){
                throw new RuntimeException("达到最大迭代次数，仍未得到预期结果...");
            }
            for (int i = 0; i < x.length; i++ ) {
                x[i] = y[i];
            }
        }
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

```
# Gauss-Seidel法
## 高斯-塞德尔(Gauss-Seidel)迭代公式
$$
x_i^{(k+1)}=\frac{b_i-\sum_{j=1}^{i-1}a_{ij}x_j^{(k+1)}-\sum_{j=i+1}^na_{ij}x_j^{(k)}}{a_{ii}},i\in N^\ast,(j≠i).
$$
## 算法描述
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425175246614.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## 程序运行结果截图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425175311577.png)
## Source Codes([github](https://github.com/Jason-danran0612/NumericalAnalysis))
由于代码实现更改较小，这里不提供代码，可到github查看详细代码。
# 超松弛法
## 计算公式
$$
x_i^{(k+1)}=(1-\omega)x_i^{(k)}+\frac{\omega(b_i-\sum_{j=1}^{i-1}a_{ij}x_j^{(k+1)}-\sum_{j=i+1}^na_{ij}x_j^{(k)})}{a_{ii}},i\in N^\ast\\\omega\mathrm{为松弛因子}，且\omega\in(0,2),\mathrm{以保证收敛}.
$$
## 算法描述
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425180104332.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## Source Codes([github](https://github.com/Jason-danran0612/NumericalAnalysis))
由于代码实现更改较小，这里不提供代码，可到github查看详细代码。
