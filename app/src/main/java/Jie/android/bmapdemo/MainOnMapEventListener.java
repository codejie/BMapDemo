package jie.android.bmapdemo;

import android.graphics.Point;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.baidu.mapapi.map.Marker;

import jie.android.bmapdemo.map.MarkerData;
import jie.android.bmapdemo.map.OnMapEventListener;
import jie.android.bmapdemo.view.UserPanel;
import jie.android.bmapdemo.view.UserPopupWindow;

/**
 * Created by codejie@gmail.com on 6/18/2014.
 */
public class MainOnMapEventListener implements OnMapEventListener {

    private final MainActivity activity;

    public MainOnMapEventListener(final MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onMarkerClick(final MarkerData data) {
        final View panel = UserPanel.make(activity, data);

        Point p = activity.getMap().getProjection().toScreenLocation(data.getMarker().getPosition());

        UserPopupWindow pw = new UserPopupWindow(panel, new UserPopupWindow.OnUserPanelListener() {
            @Override
            public void onClick(View view) {
                Log.d("=====", "onClick = " + view.getId());
                Toast.makeText(activity, "Button Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d("=====", "onKey = " + i + " event = " + keyEvent.getKeyCode());
                return false;
            }
        });

        pw.showAtLocation(activity.getMapView(), Gravity.LEFT | Gravity.BOTTOM, p.x, activity.getMapView().getHeight() - p.y);
    }

    @Override
    public void onLocateEnd() {

    }
}
