package jie.android.bmapdemo.data;

import android.content.Context;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

import jie.android.bmapdemo.R;
import jie.android.bmapdemo.map.MapLayer;
import jie.android.bmapdemo.view.UserPanel;

/**
 * Created by Administrator on 2014/6/15.
 */
public class Pin {

//    private final Context context;
//    private final MapLayer mapLayer;
//    private final User user;
//
//    private HashMap<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();
//
//    private int index = 0;
//
//    public Pin(final Context context, final MapLayer mapLayer, final User user) {
//        this.context = context;
//        this.mapLayer = mapLayer;
//        this.user = user;
//
//        initMapLayer();
//
//        putUserSelf();
//        putUserBuddy();
//    }
//
//    private void initMapLayer() {
//        mapLayer.setMarkerClickListener(new MapLayer.OnMarkerClickListener() {
//            @Override
//            public void onClick(BaiduMap map, int index, Marker marker) {
//                onMarkerClick(map, index, marker);
//            }
//        });
//    }
//
//    private void putUserSelf() {
//        final UserData data = user.getSelf();
////        mapLayer.setLocation(new LatLng(data.x, data.y));
//        put(data, false);
//    }
//
//    private void putUserBuddy() {
//        for (final UserData data : user.getBuddy()) {
//            put(data, true);
//        }
//    }
//
//    public void put(final UserData data, boolean merged) {
//        final BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.user_pin);
//        mapLayer.addMarker(index, new LatLng(data.x, data.y), bitmap,data.title,index);
//        ++ index;
//    }
//
//    private void onMarkerClick(BaiduMap map, int index, Marker marker) {
//        final View panel = UserPanel.make(context);
//        final InfoWindow iw = new InfoWindow(panel, marker.getPosition(), null);
//        map.showInfoWindow(iw);
//    }
}
