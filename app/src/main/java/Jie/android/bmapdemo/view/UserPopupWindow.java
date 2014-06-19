package jie.android.bmapdemo.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import jie.android.bmapdemo.R;

/**
 * Created by jzhang on 6/19/2014.
 */
public class UserPopupWindow extends PopupWindow{

    public UserPopupWindow(View view, final OnUserPanelListener listener) {
        super(view);

        final Button btn1 = (Button) view.findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(view);
                    UserPopupWindow.this.dismiss();
                }
            }
        });

        final Button btn2 = (Button) view.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(view);
                    UserPopupWindow.this.dismiss();
                }
            }
        });

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (listener != null) {
                    return listener.onKey(view, i, keyEvent);
                }
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(view);
                    UserPopupWindow.this.dismiss();
                }
            }
        });

        setBackgroundDrawable(new ColorDrawable());
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
//        this.update();
    }

}
