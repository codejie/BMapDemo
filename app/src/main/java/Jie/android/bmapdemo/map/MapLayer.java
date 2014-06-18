package jie.android.bmapdemo.map;

import android.content.Context;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jie.android.bmapdemo.R;
import jie.android.bmapdemo.data.UserData;

/**
 * Created by Administrator on 2014/6/15.
 */
public class MapLayer {

    private Context context;
    private final BaiduMap map;

    private final OnMapEventListener listener;

    private int markerIndex = 0;
    private HashMap<Integer, MarkerData> mapMarker = new HashMap<Integer, MarkerData>();

    public MapLayer(Context context, final BaiduMap map, final OnMapEventListener listener) {
        this.context = context;
        this.map = map;
        this.listener = listener;

        initMap();
    }

    private void initMap() {
        map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (listener != null) {
                    listener.onMarkerClick(marker.getExtraInfo().getInt("index"), marker);
                }
                return false;
            }
        });
    }

    public void addUserData(final UserData data) {
        BitmapDescriptor bd = null;
        if (data.type == 0) {
            bd = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);
        } else {
            bd = BitmapDescriptorFactory.fromResource(R.drawable.user_pin);
        }

        final Marker marker = (Marker) map.addOverlay(new MarkerOptions().position(new LatLng(data.x, data.y)).icon(bd).title(data.title).zIndex(markerIndex));
        final Bundle bundle = new Bundle();
        bundle.putInt("index", markerIndex);
        marker.setExtraInfo(bundle);
        MarkerData md = new MarkerData(marker);
        md.addUserId(data.id);
        mapMarker.put(markerIndex, md);
        ++ markerIndex;
    }

    public void removeUserData(int id) {
        Iterator it = mapMarker.entrySet().iterator();
        while (it.hasNext()) {
            final HashMap.Entry<Integer, MarkerData> entry = (HashMap.Entry<Integer, MarkerData>) it.next();
            for (final Integer i : entry.getValue().getUserId()) {
                if (i == id) {
                    entry.getValue().getMarker().getIcon().recycle();
                    entry.getValue().getMarker().remove();

                    mapMarker.remove(entry);
                    break;
                }
            }
        }
    }

    public void setLocation(final LatLng location) {
        MapStatusUpdate up = MapStatusUpdateFactory.newLatLng(location);
        map.animateMapStatus(up);
    }
}
