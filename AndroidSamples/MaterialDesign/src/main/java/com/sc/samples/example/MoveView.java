package com.sc.samples.example;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

/**
 * Android View 滑动的实现
 * http://blog.csdn.net/itachi85/article/details/50724558
 */
public class MoveView extends View{

    private Scroller mScroller;

    private int lastY;
    private int lastX;

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public MoveView(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            Log.e("MoveView", "currX = " + mScroller.getCurrX() + ", currY = " + mScroller.getCurrY());
            //通过不断的重绘不断的调用computeScroll方法
            invalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, 2000);
        invalidate();
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastX = x;
//                lastY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int offsetX = x - lastX;
//                int offsetY = y - lastY;
//                //1.使用layout的方式
//                //layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
//                //2.使用offsetLeftAndRight()与offsetTopAndBottom()的方式
//                //offsetLeftAndRight(offsetX);
//                //offsetTopAndBottom(offsetY);
//                //3.使用LayoutParams 布局参数的方式
//                //LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                //Log.e("MoveView", "leftMargin = " + layoutParams.leftMargin + ", topMargin = " + layoutParams.topMargin);
//                //layoutParams.leftMargin = getLeft() + offsetX;
//                //layoutParams.topMargin = getTop() + offsetY;
//                //setLayoutParams(layoutParams);
//                //5.使用scollTo与scollBy的方式
//                ((View)getParent()).scrollBy(-offsetX,-offsetY);
//                break;
//        }
//
//        return true;
//    }
}
