package jie.android.bmapdemo.data;

import java.util.ArrayList;
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
    private ArrayList<Data> buddy = new ArrayList<Data>();

    public User(final Data self) {
        this.self = self;
    }
    public void addBuddy(final Data data) {
        buddy.add(data);
    }

    public void removeBuddy(int id) {
        int index = 0;
        for (final Data data : buddy) {
            if (data.id == id) {
                buddy.remove(index);
                return;
            }
            ++ index;
        }
    }

    public final Data getSelf() {
        return self;
    }

    public final ArrayList<Data> getBuddy() {
        return buddy;
    }

    public final Data getBuddy(int id) {
        for (final Data data : buddy) {
            if (data.id == id) {
                return data;
            }
        }
        return null;
    }
}
