package jie.android.bmapdemo.map;

import android.content.Context;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.HashMap;

/**
 * Created by Administrator on 2014/6/15.
 */
public class MapLayer {

    public interface OnMarkerClickListener {
        public void onClick(final BaiduMap map, int index, final Marker marker);
    }

    public interface OnMapEventListener {
        public void onLocatedEnd();
    }

    private Context context;
    private final BaiduMap map;

    private OnMarkerClickListener clickListener = null;
    private OnMapEventListener mapEventListener = null;

    private HashMap<Integer, Marker> mapMarker = new HashMap<Integer, Marker>();

    public MapLayer(Context context, final BaiduMap map) {
        this.context = context;
        this.map = map;

        initMap();
    }

    private void initMap() {
        map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (clickListener != null) {
                    clickListener.onClick(map, marker.getExtraInfo().getInt("index"), marker);
                }
                return false;
            }
        });
    }

    public void addMarker(int index, final LatLng lat, final BitmapDescriptor bitmap, final String title, int z) {
       final Marker marker = (Marker) map.addOverlay(new MarkerOptions().position(lat).icon(bitmap).title(title).zIndex(z));
       Bundle data = new Bundle();
       data.putInt("index", index);
       marker.setExtraInfo(data);
       mapMarker.put(index, marker);
   }

   public void removeMarker(int index) {
       final Marker marker = mapMarker.remove(index);
       if (marker != null) {
           marker.remove();
       }
   }

   public void removeAllMarker() {
       for (final Integer index : mapMarker.keySet()) {
           removeMarker(index);
       }
   }

   public void setMarkerClickListener(final OnMarkerClickListener listener) {
       clickListener = listener;
   }

   public void setMapEventListener(final OnMapEventListener listener) {
       mapEventListener = listener;
   }

    public void setLocation(final LatLng location) {

    }
}
