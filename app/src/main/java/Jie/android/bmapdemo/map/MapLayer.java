package jie.android.bmapdemo.map;

import android.content.Context;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.HashMap;

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
        map.setMyLocationEnabled(true);

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

    }

    public void removeUserData(int id) {

    }

    public void addMarker(int index, final LatLng lat, final BitmapDescriptor bitmap, final String title, int z) {
       final Marker marker = (Marker) map.addOverlay(new MarkerOptions().position(lat).icon(bitmap).title(title).zIndex(z));
//       Bundle data = new Bundle();
//       data.putInt("index", index);
//       marker.setExtraInfo(data);
//       mapMarker.put(index, marker);
   }

   public void removeMarker(int index) {
//       final Marker marker = mapMarker.remove(index);
//       if (marker != null) {
//           marker.getIcon().recycle();
//           marker.remove();
//       }
   }

   public void removeAllMarker() {
       for (final Integer index : mapMarker.keySet()) {
           removeMarker(index);
       }
   }

    public void setLocation(final LatLng location) {
        MapStatusUpdate up = MapStatusUpdateFactory.newLatLng(location);
        map.animateMapStatus(up);
    }
}
