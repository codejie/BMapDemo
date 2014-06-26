package jie.android.bmapdemo;

import android.app.Activity;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

import jie.android.bmapdemo.data.OnUserUpdatedListener;
import jie.android.bmapdemo.data.User;
import jie.android.bmapdemo.data.UserData;
import jie.android.bmapdemo.map.MapLayer;
import jie.android.bmapdemo.map.MarkerData;
import jie.android.bmapdemo.map.OnMapEventListener;
import jie.android.bmapdemo.view.ControlPopupWindow;
import jie.android.bmapdemo.view.FlatButton;
import jie.android.bmapdemo.view.UserPanel;
import jie.android.bmapdemo.view.UserPopupWindow;

public class BMapFragment extends Fragment {

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
        public boolean onMapViewClicked();
    }

    private static final String Tag = BMapFragment.class.getSimpleName();

    private static final int MSG_USER_LOCATION = 100;
    private static final int MSG_USER_RELOCATION = 101;

    private static final String PARAM_USERID = "param_userid";
    private OnFragmentInteractionListener onFragmentInteractionListener;

    private MapView mapView;
    private BaiduMap map;
    private LocationClient locationClient;

    private MapLayer mapLayer;
    private User user;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_USER_LOCATION:
                    final Bundle bundle = (Bundle)msg.obj;
                    user.setSelfLocation(bundle.getDouble("latitude"), bundle.getDouble("longitude"));
                    break;
                case MSG_USER_RELOCATION:
                    final UserData data = user.getSelf();
                    mapLayer.removeUserData(data.id);
                    mapLayer.setLocation(new LatLng(data.x, data.y));
                    mapLayer.addUserData(data);
                    break;
                default:;
            }
        }
    };

    private OnUserUpdatedListener onUserUpdatedListener = new OnUserUpdatedListener() {

        @Override
        public void onSelfDataUpdated(UserData data) {
            mapLayer.removeUserData(data.id);
            mapLayer.setLocation(new LatLng(data.x, data.y));
            mapLayer.addUserData(data);
        }

        @Override
        public void onBuddyDataAdded(UserData data) {
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
    };
    private OnMapEventListener onMapEventListener = new OnMapEventListener() {
        @Override
        public void onMarkerClick(MarkerData data) {
            onMapMarkerClick(data);
        }

        @Override
        public void onLocateEnd() {

        }
    };

    public static BMapFragment newInstance(String userid) {
        BMapFragment fragment = new BMapFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_USERID, userid);
        fragment.setArguments(args);
        return fragment;
    }
    public BMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        } else {
        }

        user = new User(0, "User", onUserUpdatedListener);

        initUserLocation();
    }

    private void initUserLocation() {
        locationClient = new LocationClient(this.getActivity());

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
//        locationClient.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmap, container, false);

        mapView = (MapView)  view.findViewById(R.id.bmapView);

        map = mapView.getMap();
        map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (onFragmentInteractionListener != null) {
                    onFragmentInteractionListener.onMapViewClicked();
                }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        map.setMyLocationEnabled(true);
        mapLayer = new MapLayer(this.getActivity(), map, onMapEventListener);

        final FlatButton fb = (FlatButton) view.findViewById(R.id.view);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View panel = LayoutInflater.from(BMapFragment.this.getActivity()).inflate(R.layout.view_pop_controlpanel, null);

                ControlPopupWindow pw = new ControlPopupWindow(panel, null);
                pw.show(mapView, 10, 10);
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFragmentInteractionListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!locationClient.isStarted()) {
            locationClient.start();
        } else {
            Message msg = Message.obtain(handler, MSG_USER_RELOCATION);
            msg.sendToTarget();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        locationClient.stop();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    private void onMapMarkerClick(MarkerData data) {
        final View panel = UserPanel.make(this.getActivity(), data);

        Point p = map.getProjection().toScreenLocation(data.getMarker().getPosition());

        UserPopupWindow pw = new UserPopupWindow(panel, new UserPopupWindow.OnUserPanelListener() {
            @Override
            public void onClick(View view) {
                Log.d("=====", "onClick = " + view.getId());
                Toast.makeText(BMapFragment.this.getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d("=====", "onKey = " + i + " event = " + keyEvent.getKeyCode());
                return false;
            }
        });
        //pw.showAtLocation(mapView, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, p.x, mapView.getHeight() - p.y);
        pw.show(mapView, p.x, p.y);
    }


}
