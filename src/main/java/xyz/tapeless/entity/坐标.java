package xyz.tapeless.entity;

import lombok.Data;

@Data
public class 坐标 {
    private int x;
    private int y;

    public 坐标 offset(int x,int y){
        return new 坐标(this.x+x,this.y+y);
    }
    public 坐标(int 基准x, int 基准y) {
        x = 基准x;
        y = 基准y;
    }
}
