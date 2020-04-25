package Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname InitDataSource
 * @Description TODO
 * @Date 2020/4/22 15:18
 * @Created by ASUS
 */
public class InitDataSource {
    private final static double PI = Math.PI;
    private static List<Double> InitValues = null;

    public InitDataSource(){
    }
    //    init values获得初始值点集x...
    public static List<Double> getInitValues(){
        if(InitValues == null){
            InitValues = new ArrayList<>();
            InitValues.add(0.0);
            InitValues.add(PI/6);
            InitValues.add(PI/3);
            InitValues.add(PI/2);
            return InitValues;
        }
        return InitValues;
    }
}
