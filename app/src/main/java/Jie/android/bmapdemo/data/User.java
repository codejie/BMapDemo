package jie.android.bmapdemo.data;

import java.util.HashMap;

/**
 * Created by Administrator on 2014/6/15.
 */
public class User {
    public class Data {
        public int id;
        public int type;//0:self; 1: buddy
        public int title;
        public double x;
        public double y;
    }

    private Data self;
    private HashMap<Integer, Data> buddy = new HashMap<Integer, Data>();

    public User(final Data self) {
        this.self = self;
    }
    public void addBuddy(final Data data) {
        buddy.put(data.id, data);
    }

    public void removeBuddy(int id) {
        buddy.remove(id);
    }

}
