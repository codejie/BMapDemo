package jie.android.bmapdemo.data;

/**
 * Created by Administrator on 2014/6/17.
 */
public class UserData {
    public int id;
    public int type;//0:self; 1: buddy
    public String title;
    public double x;
    public double y;

    public UserData(int id, int type, String title, double x, double y) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.x = x;
        this.y = y;
    }
}
