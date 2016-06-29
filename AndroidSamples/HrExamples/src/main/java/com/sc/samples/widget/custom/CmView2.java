package com.sc.samples.widget.custom;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sc.samples.R;

/**
 * 自定义的View，使用<declare-styleable>和obtainStyledAttributes方法
 * http://blog.csdn.net/iispring/article/details/50708044
 */
public class CmView2 extends View {

    //要显示的文本customText
    private String mCustomText;

    //存储文本的颜色customColor
    private int mCustomColor = 0xFF000000;

    //画笔
    private TextPaint mTextPaint;

    //字体大小
    private float mFontSize = 14;

    public CmView2(Context context) {
        super(context);
        init(null, 0);
    }

    public CmView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CmView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);

    }

    private void init(AttributeSet attrs, int defStyle) {
        //首先判断attributeSet是否为null
        if (attrs != null) {
            //获取当前MyView所在的Activity的theme
            Resources.Theme theme = getContext().getTheme();
            //通过theme的obtainStyledAttributes方法获取TypedArray对象
            TypedArray typedArray = theme.obtainStyledAttributes(attrs, R.styleable.CmView2, 0, 0);
            //获取typedArray的长度
            int count = typedArray.getIndexCount();
            //通过for循环遍历typedArray
            for (int i = 0; i < count; i++) {
                //通过typedArray的getIndex方法获取指向R.styleable中对应的属性ID
                int styledAttr = typedArray.getIndex(i);
                switch (styledAttr) {
                    case R.styleable.CmView2_customText2:
                        //如果是R.styleable.MyView_customText，表示属性是customText
                        //通过typedArray的getString方法获取字符串值
                        mCustomText = typedArray.getString(i);
                        break;
                    case R.styleable.CmView2_customColor2:
                        //如果是R.styleable.MyView_customColor，表示属性是customColor
                        //通过typedArray的getColor方法获取整数类型的颜色值
                        mCustomColor = typedArray.getColor(i, 0xFF000000);
                        break;
                }
            }
            //在使用完typedArray之后，要调用recycle方法回收资源
            typedArray.recycle();
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
