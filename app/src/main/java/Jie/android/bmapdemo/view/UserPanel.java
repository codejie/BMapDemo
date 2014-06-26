package jie.android.bmapdemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import jie.android.bmapdemo.R;
import jie.android.bmapdemo.map.MarkerData;

/**
 * Created by codejie@gmail.com on 6/16/2014.
 */
public class UserPanel {

    public static View make(final Context context, final MarkerData data) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_pop_userpanel, null);

        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(data.getTitle());

        return view;
    }
}
