package jie.android.bmapdemo.map;

import com.baidu.mapapi.map.Marker;

/**
 * Created by Administrator on 2014/6/17.
 */
public interface OnMapEventListener {
    public void onMarkerClick(final MarkerData data);

    public void onLocateEnd();
}
