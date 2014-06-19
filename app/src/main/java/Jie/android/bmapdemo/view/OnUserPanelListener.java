package jie.android.bmapdemo.view;

import android.view.KeyEvent;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by jzhang on 6/19/2014.
 */
public interface OnUserPanelListener {
    public void onClick(View view);
    public boolean onKey(View view, int i, KeyEvent keyEvent);
}
