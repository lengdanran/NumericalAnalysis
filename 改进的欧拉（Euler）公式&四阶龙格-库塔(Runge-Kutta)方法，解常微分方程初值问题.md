# 改进的欧拉（Euler）公式
## 欧拉公式
$$
\begin{array}{l}y_{n+1}=y_n+hf\left(x_n,y_n\right)n\in N\end{array}
$$
## 梯形公式
$$
\begin{array}{l}y_{n+1}=y_n+\frac h2\lbrack f\left(x_n,y_n\right)+f(x_{n+1},y_{n+1})], n\in N\end{array}
$$
该方法综合l欧拉方法和梯形方法，欧拉方法是一个显式的算法，它的计算量少，但是精度低；而梯形方法虽然可以提高精度，但是它是一种隐式算法，需要借助不断地迭代求解，它的计算量相对较大。
改进的欧拉（Euler）公式综合二者的优势，先用欧拉方法求得一个初步的近似值$\overline y_{n+1}$称之为预报值，预报值的精度不高，我们用它直接代替梯形公式右端的$\ y_{n+1}$得到校正值$\ y_{n+1}$，建立预报-校正系统，又称为改进的欧拉（Euler）公式.
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020042514542433.png)
#### 嵌套格式
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425145721850.png)
## 算法框图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425145858413.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)

### 变量说明
$x_0,y_0$:初值条件
$h$:步长
$N$:步数

## 研究对象
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425150228254.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425150309838.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## 程序运行结果截图
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020042515043166.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## 数据分析
### 数据统计表格
$y_n为计算值，f(x_n)为精确值，R(x_n)为余项值，可以理解为误差.(所有数据来自上图）$
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425150608277.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
### 数据绘图
蓝色线为计算值：$y_n$；黄色线为准确值：$f(x_n)$
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425151006143.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## Source Codes(完整代码见[github](https://github.com/Jason-danran0612/NumericalAnalysis))


```java
public static List<List<Double>> getEstimates(double x0,double y0,double h,int N) {
        List<List<Double>> result = new ArrayList<>();
        List<Double> xi = new ArrayList<>();
        List<Double> yi = new ArrayList<>();
        List<Double> reals = new ArrayList<>();
        List<Double> miscount = new ArrayList<>();
        double x1,y1,yp,yc,real,mis,K1,K2,K3,K4;
        for (int i = 0 ; i < N ; i++ ) {
            x1 = x0 + h;
            yp = y0 + h*f(x0,y0);
            yc = y0 + h*f(x1,yp);
            y1 = (yp + yc)/2;

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
```

# 四阶龙格-库塔(Runge-Kutta)方法
## 研究对象
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425152049654.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## 算法框图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425152333701.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)


### 变量说明
$x_0,y_0$:初值条件
$h$:步长
$N$:步数
## 程序运行截图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425152654670.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
## 数据分析
### 数据统计表格
$y_n为计算值，f(x_n)为精确值，R(x_n)为余项值，可以理解为误差.(所有数据来自上图）$
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425152920890.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
### 数据绘图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425153345406.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200425153407393.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020042515342884.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)
蓝色线为计算值：$y_n$；黄色线为准确值：$f(x_n)$；灰色线为余项值：$R(x_n).$
## Source Codes(完整代码见[github](https://github.com/Jason-danran0612/NumericalAnalysis))

```java
public static List<List<Double>> getEstimates(double x0,double y0,double h,int N) {
        List<List<Double>> result = new ArrayList<>();
        List<Double> xi = new ArrayList<>();
        List<Double> yi = new ArrayList<>();
        List<Double> reals = new ArrayList<>();
        List<Double> miscount = new ArrayList<>();
        double x1,y1,yp,yc,real,mis,K1,K2,K3,K4;
        for (int i = 0 ; i < N ; i++ ) {
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
```

# 小结
初值问题他们都有一个共同的特点，采用步进式，即是过程中顺着节点的排列顺序一步一步的向前推进。通过本次实验，虽然说这种方式理论上是可以得到任何精度要求下的数据值，但是，随之带来的是巨大的计算量。
步长$h$是决定性因素，步长小，精度高，但是前进速度慢；步长大，前进速度快，精度较差。所以在实际应用过程中，对步长的选择就显得十分重要。
