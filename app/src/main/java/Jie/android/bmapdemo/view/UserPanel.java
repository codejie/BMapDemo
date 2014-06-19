package jie.android.bmapdemo.view;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import jie.android.bmapdemo.R;
import jie.android.bmapdemo.map.MarkerData;

/**
 * Created by jzhang on 6/16/2014.
 */
public class UserPanel {

    public static View make(final Context context, final MarkerData data) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.userpanel, null);

        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(data.getTitle());

        return view;
    }
}
