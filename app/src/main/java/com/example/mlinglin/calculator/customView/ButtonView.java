package com.example.mlinglin.calculator.customView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Button
 */

public class ButtonView extends android.support.v7.widget.AppCompatButton {

    GradientDrawable gradientDrawable;

    private int pressedColor = Color.GRAY;
    private int standardColor = Color.BLACK;
    private float currCorner = 50;

    boolean isTouchPass = true;

    //new 实例化调用
    public ButtonView(Context context) {
        this(context, null);
        init();
    }

    //布局文件声明调用
    public ButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    //增加一个默认显示样式时调用
    public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(standardColor);
        gradientDrawable.setCornerRadius(currCorner);
        setTextColor(Color.WHITE);
        setTextSize(26);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                setBackgroundDrawable(gradientDrawable);
                return setColor(event.getAction());
            }
        });
        setBackgroundDrawable(gradientDrawable);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        isTouchPass = false;
    }

    public boolean setColor(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                gradientDrawable.setColor(pressedColor);
                break;
            case MotionEvent.ACTION_UP:
                gradientDrawable.setColor(standardColor);
                break;
            case MotionEvent.ACTION_MOVE:
                gradientDrawable.setColor(pressedColor);
                break;
            case MotionEvent.ACTION_CANCEL:
                gradientDrawable.setColor(Color.RED);
                break;
        }
        return isTouchPass;
    }
}
