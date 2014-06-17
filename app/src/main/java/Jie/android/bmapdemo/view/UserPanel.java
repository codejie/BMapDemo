package jie.android.bmapdemo.view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import jie.android.bmapdemo.R;

/**
 * Created by jzhang on 6/16/2014.
 */
public class UserPanel {

    public static View make(final Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.userpanel, null);
        return view;
    }
}
