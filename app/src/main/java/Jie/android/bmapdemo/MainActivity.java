package jie.android.bmapdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

import jie.android.bmapdemo.R;
import jie.android.bmapdemo.data.User;
import jie.android.bmapdemo.data.UserData;
import jie.android.bmapdemo.map.MapLayer;

public class MainActivity extends Activity {

    private static final int MSG_USER_LOCATION = 100;

    private MapView mapView;
    private BaiduMap map;
    private LocationClient locationClient;

    private MapLayer mapLayer;
    private User user;

    private MainOnUserUpdatedListener onUserUpdatedListener = new MainOnUserUpdatedListener(this);
    private MainOnMapEventListener onMapEventListener = new MainOnMapEventListener(this);

    private int tempUserId = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_USER_LOCATION:
                    final Bundle bundle = (Bundle)msg.obj;
                    user.setSelfLocation(bundle.getDouble("latitude"), bundle.getDouble("longitude"));
                    break;
                default:;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        user = new User(0, "User", onUserUpdatedListener);

        mapView = (MapView) this.findViewById(R.id.bmapView);
        map = mapView.getMap();
        map.setMyLocationEnabled(true);
        mapLayer = new MapLayer(this, map, onMapEventListener);

        final Button btn = (Button) this.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(view);
            }
        });

        initUserLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        locationClient.stop();
        mapView.onDestroy();
        super.onDestroy();
    }

    private void onButtonClick(View view) {
        user.addBuddy(new UserData(tempUserId , 1, "B-" + tempUserId ++ ,user.getSelf().x + Math.random() * 0.025, user.getSelf().y + Math.random() * 0.045));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUserLocation() {
        locationClient = new LocationClient(this);

        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                final Bundle bundle = new Bundle();
                bundle.putDouble("latitude", bdLocation.getLatitude());
                bundle.putDouble("longitude", bdLocation.getLongitude());
                final Message msg = Message.obtain(handler,MSG_USER_LOCATION, bundle);
                msg.sendToTarget();
            }

            @Override
            public void onReceivePoi(BDLocation bdLocation) {

            }
        });
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        locationClient.setLocOption(option);
        locationClient.start();
    }

    public final MapView getMapView() {
        return mapView;
    }

    public final BaiduMap getMap() {
        return map;
    }

    public final MapLayer getMapLayer() {
        return mapLayer;
    }

    public final User getUser() {
        return user;
    }
}
