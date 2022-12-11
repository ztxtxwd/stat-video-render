package xyz.tapeless.util;

import xyz.tapeless.entity.人;

import java.util.List;

public class 抗锯齿器 {
    public static void 抗锯齿(List<人> 人List, int level){
        if (level<2){
            return;
        }
        for (int k = 0; k < 人List.size(); k++) {
            List<Integer> 人气数据 =人List.get(k).get人气数据();
            for (int i = 0; i < 人气数据.size(); i+=level) {
                int start=人气数据.get(i);
                int endIndex=i+level<人气数据.size()?i+level:人气数据.size()-1;
                int end=人气数据.get(endIndex);
                int step=(end-start)/(endIndex-i-1);
                for (int j = i+1; j < endIndex-1; j++) {
                    人气数据.set(j,人气数据.get(j-1)+step);
                }
            }
        }
    }
}
