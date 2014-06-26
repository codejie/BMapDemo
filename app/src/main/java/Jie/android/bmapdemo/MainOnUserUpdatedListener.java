package jie.android.bmapdemo;

import com.baidu.mapapi.model.LatLng;

import jie.android.bmapdemo.data.OnUserUpdatedListener;
import jie.android.bmapdemo.data.UserData;
import jie.android.bmapdemo.map.MapLayer;

/**
 * Created by codejie@gmail.com on 6/18/2014.
 */
public class MainOnUserUpdatedListener implements OnUserUpdatedListener {

    private final MainActivity activity;

    public MainOnUserUpdatedListener(final MainActivity activity) {
        this.activity = activity;
    }
    @Override
    public void onSelfDataUpdated(UserData data) {
        final MapLayer mapLayer = activity.getMapLayer();

        mapLayer.removeUserData(data.id);

        mapLayer.setLocation(new LatLng(data.x, data.y));
        mapLayer.addUserData(data);
    }

    @Override
    public void onBuddyDataAdded(UserData data) {
        final MapLayer mapLayer = activity.getMapLayer();
        mapLayer.addUserData(data);
    }

    @Override
    public void onBuddyDataRemoved(UserData data) {

    }

    @Override
    public void onBuddyDataUpdated(UserData data) {

    }

    @Override
    public void onBuddyDataRefreshed() {

    }
}
