# 数值积分
在实际的应用设计过程中，比如进行桥梁，摩天大楼等的设计时，会出现许多无法直接求出积分的函数，如：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415093742126.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)

而这些积分值对我们又十分重要，就可以利用计算机的高速的计算能力为我们提供一个达到我们预设的精度ε的数值值，这就叫数值积分。
## 积分的方法
计算数值积分的方式有很多，比如机械求积公式、牛顿——柯特斯公式（Newton-Cotes）等。
# Romberg算法举例
首先数值积分的公式有很多，但存在一些它的收敛速度慢，对于一个特定的精度ε的值的计算可能计算次数多，效率不高，这里我就简单介绍一种算法**Romberg算法**,它可以让数值快速收敛。并且与另一个加速算法**梯形变步长算法**进行比较。
选取一个可以求出精确值的积分函数研究：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415095408232.png)
## Romberg算法框图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415104911809.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70#pic_center)
### 代码实现

```java
 private static double Romberg(Scanner scanner,Tool tool) {
        int count = scan(tool,scanner);
        long timeStart = System.currentTimeMillis();
        tool.setH(tool.getB() - tool.getA());
        tool.setT1((tool.getH()/2)*(f(tool.getA()) + f(tool.getB())));
        for (int i = 0;i < count;i++){
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
                tool.setR1();
                tool.setC1(tool.getC2());
                operation1(tool);
            }
            if(i != 0){
                tool.setC2(tool.getS2() + (tool.getS2() - tool.getS1())/15);
                tool.setR2(tool.getC2() - (tool.getC2() - tool.getC1())/63);
            }
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("计算用时（ms）:" + (timeEnd - timeStart));
        System.out.println("Romberg方式进行" + count +"次计算后的积分值为：" + tool.getR2());
        return tool.getR2();
    }
```

## 梯形变步长算法框图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415094837920.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70#pic_center)
### 代码实现

```java
private static double TiXing(Scanner scanner,Tool tool) {
        int count = scan(tool,scanner);
        long timeStart = System.currentTimeMillis();
        double a = tool.getA();
        double b = tool.getB();
        double h = b - a;
        double T;
        T = (h/2)*(f(a) + f(b));
        double S , x;
        for (int i = 0;i < count-1;i++){
            S = 0;
            x = a + h/2;
            while (x < b){
                S = S + f(x);
                x = x + h;
            }
            T = (T + S*h)/2;
            h = h/2;
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("计算用时（ms）:" + (timeEnd - timeStart));
        System.out.println("二分" + count +"次计算后的积分值为：" + T);
        return T;
    }
```

## 结果分析
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415095529199.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1NzQ0NTAx,size_16,color_FFFFFF,t_70)

很容易看出来，利用Romberg算法得到的结果在精度上高出梯形变步长算法两个数量级，而且用时比为**1：1485**，加工次数比为**1：3**，可见Romberg算法的收敛速度远远高于后者，这也就反映出，在运用计算机解决问题时，算法优化的重要性，计算机它内部只能进行简单的算术和逻辑运算，它的强项是运算速度快，但是在设计算法时，我们也要考虑高效的运算方式，不能说利用它高速运算的能力，却让它进行许多效率不高的运算，从而降低了它应该具有的性能。
#### 关于本文的算法实现（Java）
详细代码可以下载
