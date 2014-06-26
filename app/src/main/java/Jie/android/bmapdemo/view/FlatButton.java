package jie.android.bmapdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import jie.android.bmapdemo.R;

/**
 * TODO: document your custom view class.
 */
public class FlatButton extends Button {

    private int dx, dy;
    private OnClickListener listener;

    public FlatButton(Context context) {
        super(context);
    }

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlatButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)getLayoutParams();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_MOVE: {
                params.bottomMargin -= (int)event.getRawY() - dy;
                if (params.bottomMargin >= 800) {
                    params.bottomMargin = 800;
                } else if (params.bottomMargin <= 80) {
                    params.bottomMargin = 80;
                }
                dy = (int)event.getRawY();
//                params.rightMargin -= (int)event.getRawX() - dx;
                dx=(int)event.getRawX();
                setLayoutParams(params);
                return true;
            }
            case MotionEvent.ACTION_UP: {
                params.bottomMargin -= (int)event.getRawY() - dy;
//                params.rightMargin -= (int)event.getRawX() - dx;
                setLayoutParams(params);
                if (listener != null) {
                    listener.onClick(this);
                }
                return true;
            }
            case MotionEvent.ACTION_DOWN: {
                dx = (int)event.getRawX();
                dy = (int)event.getRawY();
                params.topMargin = getHeight();
//                params.leftMargin= getWidth();
                setLayoutParams(params);
                return true;
            }
        }
        return false;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.listener = l;
    }
}
