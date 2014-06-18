package jie.android.bmapdemo;

import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;


import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;

import jie.android.bmapdemo.map.OnMapEventListener;
import jie.android.bmapdemo.view.UserPanel;

/**
 * Created by jzhang on 6/18/2014.
 */
public class MainOnMapEventListener implements OnMapEventListener {

    private final MainActivity activity;

    public MainOnMapEventListener(final MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onMarkerClick(int index, Marker marker) {
        final View panel = UserPanel.make(activity);


        Point p = activity.getMap().getProjection().toScreenLocation(marker.getPosition());

        final PopupWindow pw = new PopupWindow(panel);
//        //pw.setWidth(250);
        pw.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);

        pw.showAtLocation(activity.getMapView(), Gravity.LEFT | Gravity.BOTTOM,p.x, p.y );

        final Button btn = (Button) panel.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw.dismiss();
            }
        });
//
//        PopupWindow pw = new PopupWindow(panel, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
//
//
//
//        Point p = activity.getMap().getProjection().toScreenLocation(marker.getPosition());
//
//        ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(p.x, p.y);//ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//, p.x, p.y, 0);
//
//
//
//        activity.getMapView().addView(panel, ll);


//        MapView.LayoutParams layoutParams = new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT, p.x, p.y);
//
//        final InfoWindow iw = new InfoWindow(panel, marker.getPosition(), null);
//        activity.getMap().showInfoWindow(iw);
    }

    @Override
    public void onLocateEnd() {

    }
}
