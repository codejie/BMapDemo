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
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

import jie.android.bmapdemo.R;
import jie.android.bmapdemo.data.Pin;
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mapView = (MapView) this.findViewById(R.id.bmapView);
        map = mapView.getMap();
        mapLayer = new MapLayer(this, map);

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
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);
//        mapLayer.addMarker(0, new LatLng(39.963175, 116.400244), bitmap, "test", 1);

        LatLng ll = new LatLng(40.963175, 116.400244);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        map.animateMapStatus(u);
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
}
