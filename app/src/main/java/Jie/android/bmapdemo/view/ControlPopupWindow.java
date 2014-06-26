package jie.android.bmapdemo.view;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import jie.android.bmapdemo.R;

/**
 * Created by codejie@gmail.com on 6/26/2014.
 */
public class ControlPopupWindow extends PopupWindow{

    public interface OnControlPanelListener {
        public void onClick(View view);
    }

    private final OnControlPanelListener listener;

    public ControlPopupWindow(View view, OnControlPanelListener listener) {
        super(view);

        this.listener = listener;
    }

    public void show(final View parent, int x, int y) {
        setBackgroundDrawable(new ColorDrawable());
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
//        this.update();

        showAtLocation(parent, Gravity.LEFT | Gravity.BOTTOM, x, y);
    }

}
