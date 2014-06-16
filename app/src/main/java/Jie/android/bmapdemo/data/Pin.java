package jie.android.bmapdemo.data;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

import jie.android.bmapdemo.map.MapLayer;

/**
 * Created by Administrator on 2014/6/15.
 */
public class Pin {

    private final MapLayer mapLayer;
    private final User user;

    private HashMap<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();

    public Pin(final MapLayer mapLayer, final User user) {
        this.mapLayer = mapLayer;
        this.user = user;

        initMapLayer();

        putUserSelf();
    }

    private void initMapLayer() {
        mapLayer.setMarkerClickListener(new MapLayer.OnMarkerClickListener() {
            @Override
            public void onClick(BaiduMap map, int index, Marker marker) {
                onMarkerClick(map, index, marker);
            }
        });
    }

    private void putUserSelf() {
        final User.Data data = user.getSelf();
        mapLayer.setLocation(new LatLng(data.x, data.y));
        put(data, false);
    }

    private void putUserBuddy() {
        for (final User.Data data : user.getBuddy()) {
            put(data, true);
        }
    }

    public void put(final User.Data data, boolean merged) {

    }

    private void onMarkerClick(BaiduMap map, int index, Marker marker) {

    }
}
