package com.example.mlinglin.calculator.customView;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * TextView
 */

public class ShowView extends android.support.v7.widget.AppCompatTextView {

    private Paint textPaint;
    private float maxTextSize;
    private float minTextSize = 20;

    //new 实例化时调用
    public ShowView(Context context) {
        this(context, null);
    }

    //布局文件调用
    public ShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLines(1);
        textPaint = new TextPaint();
        textPaint.set(this.getPaint());
        // 获取当前所设置文字大小作为最大文字大小
        maxTextSize = this.getTextSize();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        matchingText(text.toString(), this.getWidth());
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    /**
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);    //空方法
        if (w != oldw) {
            matchingText(this.getText().toString(), w);
        }
    }

    private void matchingText(String s, int w) {
        if (w > 0) {
            int availableWidth = w - this.getPaddingLeft() - this.getPaddingRight();    //剩余可用宽度
            float nextSize = maxTextSize;
            textPaint.setTextSize(nextSize);
            //当下一个绘制文字的宽度大于可用宽度，字号逐一减小
            while (textPaint.measureText(s) > availableWidth) {
                nextSize -= 1;
                //防止出现过小
                if (nextSize <= minTextSize) {
                    nextSize = minTextSize;
                    break;
                }
                textPaint.setTextSize(nextSize);
            }
            // setTextSize参数值为sp值
            setTextSize(px2sp(getContext(), nextSize));
        }
    }
    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static float px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxValue / fontScale);
    }

}
