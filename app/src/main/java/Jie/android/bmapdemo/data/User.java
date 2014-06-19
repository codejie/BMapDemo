package jie.android.bmapdemo.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2014/6/15.
 */
public class User {

    private final UserData self;
    private ArrayList<UserData> buddy = new ArrayList<UserData>();

    private final OnUserUpdatedListener listener;

    public User(int id, final String title, final OnUserUpdatedListener listener) {
        this.self = new UserData(id, 0, title, 0.0, 0.0);
        this.listener = listener;
    }

    public void setSelfLocation(double x, double y) {
        if (Math.abs(x - self.x) > 0.0001 || Math.abs(y - self.y) > 0.0001) {
//        if (x != self.x || y != self.y) {
            self.x = x;
            self.y = y;

            if (listener != null) {
                listener.onSelfDataUpdated(self);
            }
        }
    }

    public void addBuddy(final UserData data) {
        buddy.add(data);
        if (listener != null) {
            listener.onBuddyDataAdded(data);
        }
    }

    public void removeBuddy(int id) {
        int index = 0;
        for (final UserData data : buddy) {
            if (data.id == id) {
                buddy.remove(index);
                if (listener != null) {
                    listener.onBuddyDataRemoved(data);
                }
                return;
            }
            ++ index;
        }
    }

    public final UserData getSelf() {
        return self;
    }

    public final ArrayList<UserData> getBuddy() {
        return buddy;
    }

    public final UserData getBuddy(int id) {
        for (final UserData data : buddy) {
            if (data.id == id) {
                return data;
            }
        }
        return null;
    }
}
