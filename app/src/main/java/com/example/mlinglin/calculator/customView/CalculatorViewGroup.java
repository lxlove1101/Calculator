package com.example.mlinglin.calculator.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.security.MessageDigest;

/**
 * Layout
 */

public class CalculatorViewGroup extends ViewGroup {

    private int horizontalSpace = 50;   //水平间距
    private int verticalSpace = 50;     //竖直间距

    //new 实例化调用
    public CalculatorViewGroup(Context context) {
        super(context);
    }

    //布局文件声明调用
    public CalculatorViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //增加一个默认显示样式时调用
    public CalculatorViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //循环测量子View大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int usedHorizontal = 50;     //水平已经使用的距离
        int usedVertical = 20;        //垂直已经使用的距离
        int width = getMeasuredWidth();
//        int height = getMeasuredHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            //判断是否已经超出宽度，这里不能用getWidth，因为getWidth是在Layout方法执行后才可以获得
            if (view.getMeasuredWidth() + usedHorizontal > width) {
                //已经超出了宽度
                usedVertical += view.getMeasuredHeight() + verticalSpace;
                usedHorizontal = 50;
            }
            view.layout(usedHorizontal, usedVertical, usedHorizontal + view.getMeasuredWidth(), usedVertical + view.getMeasuredHeight());
            usedHorizontal += view.getMeasuredWidth() + horizontalSpace;
        }

    }

//    @Override
//    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
//        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
    }
}
