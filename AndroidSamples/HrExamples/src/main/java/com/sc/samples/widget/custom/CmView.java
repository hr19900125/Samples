package com.sc.samples.widget.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sc.samples.R;

/**
 * 自定义的View，使用attr来自定义属性
 */
public class CmView extends View {

    //要显示的文本customText
    private String mCustomText;

    //存储文本的颜色customColor
    private int mCustomColor = 0xFF000000;

    //画笔
    private TextPaint mTextPaint;

    //字体大小
    private float mFontSize = 14;

    public CmView(Context context) {
        super(context);
        init(null, 0);
    }

    public CmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CmView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);

    }

    private void init(AttributeSet attrs, int defStyle) {
        //首先判断attrs是否为null
        if (attrs != null) {
            //获取AttributeSet所有的XML的属性的数量
            int count = attrs.getAttributeCount();
            //遍历AttributeSet中的XML属性
            for (int i = 0; i < count; i++) {
                //获取attr的资源ID
                int attrResId = attrs.getAttributeNameResource(i);
                switch (attrResId) {
                    case R.attr.customText:
                        //customText属性
                        mCustomText = attrs.getAttributeValue(i);
                        Log.e("CmView", "custom text : " + mCustomText);
                        break;
                    case R.attr.customColor:
                        //customColor属性
                        //如果读取不到对应的颜色值，那么就用黑色作为默认颜色
                        mCustomColor = attrs.getAttributeIntValue(i, 0xFF000000);
                        Log.e("CmView", "custom color : " + mCustomColor);
                        break;
                    case R.attr.customFontSize:
                        mFontSize = attrs.getAttributeIntValue(i, 14);
                        Log.e("CmView", "custom font size : " + mFontSize);
                        break;
                }
            }
        }

        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(3 * mFontSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("CmView", "onDraw ----------");
        if (mCustomText != null && !mCustomText.equals("")) {
            Log.e("CmView", "drawText");
            mTextPaint.setColor(mCustomColor);
            //将文本绘制显示出来
            canvas.drawText(mCustomText, 0, mFontSize, mTextPaint);
        }
    }
}
