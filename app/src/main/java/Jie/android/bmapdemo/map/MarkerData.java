package jie.android.bmapdemo.map;

import com.baidu.mapapi.map.Marker;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/6/17.
 */
public class MarkerData {
    private ArrayList<Integer> userId = new ArrayList<Integer>();
    private Marker marker;
    private String title;

    public MarkerData(final Marker marker) {
        this.marker = marker;
    }

    public final ArrayList<Integer> getUserId() {
        return userId;
    }

    public final Marker getMarker() {
        return marker;
    }

    public final String getTitle() {
        return title;
    }

    public void addUserId(int id) {
        userId.add(id);
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
