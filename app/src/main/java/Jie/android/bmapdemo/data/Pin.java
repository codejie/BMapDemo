package jie.android.bmapdemo.data;

import java.util.ArrayList;
import java.util.HashMap;

import jie.android.bmapdemo.map.MapLayer;

/**
 * Created by Administrator on 2014/6/15.
 */
public class Pin {

    private final MapLayer mapPlayery;
    private final User user;

    private HashMap<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();

    public Pin(final MapLayer mapLayer, final User user) {
        this.mapPlayery = mapLayer;
        this.user = user;
    }

    private int getIndex(final User.Data data) {
        return -1;
    }

    public void put() {

    }
}
