package com.sc.samples.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

/**
 * http://blog.csdn.net/harvic880925/article/details/50598322
 */
public class ObjectAnimatorExample1Activity extends AppCompatActivity {

    private Button mButtonStart;
    private Button mButtonCancel;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_example_2);

        mTextView = (TextView) findViewById(R.id.text);
        mButtonStart = (Button) findViewById(R.id.btn_start);
        mButtonCancel = (Button) findViewById(R.id.btn_cancel);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doScaleXAnimation();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 属性动画并不是直接改变alpha 这个属性，而是调用setAlpha这个方法来做到变化
     * <p/>
     * 如常用的
     * //1、透明度：alpha
     * public void setAlpha(float alpha)
     * <p/>
     * //2、旋转度数：rotation、rotationX、rotationY
     * public void setRotation(float rotation)
     * public void setRotationX(float rotationX)
     * public void setRotationY(float rotationY)
     * <p/>
     * //3、平移：translationX、translationY
     * public void setTranslationX(float translationX)
     * public void setTranslationY(float translationY)
     * <p/>
     * //缩放：scaleX、scaleY
     * public void setScaleX(float scaleX)
     * public void setScaleY(float scaleY)
     */
    private void doAlphaAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "alpha", 1, 0, 1);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * (1)、setRotationX、setRotationY与setRotation
     * setRotationX(float rotationX)：表示围绕X轴旋转，rotationX表示旋转度数
     * setRotationY(rotationY):表示围绕Y轴旋转，rotationY表示旋转度数
     * setRotation(float rotation):表示围绕Z旋转,rotation表示旋转度数
     */
    private void doRotationAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotation", 0, 180, 0);
        animator.setDuration(2000);
        animator.start();
    }

    private void doRotationXAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotationX", 0, 270, 0);
        animator.setDuration(2000);
        animator.start();
    }

    private void doRotationYAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotationY", 0, 270, 0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * setTranslationX与setTranslationY
     * setTranslationX(float translationX) :表示在X轴上的平移距离,以当前控件为原点，向右为正方向，参数translationX表示移动的距离。
     * setTranslationY(float translationY) :表示在Y轴上的平移距离，以当前控件为原点，向下为正方向，参数translationY表示移动的距离。
     */
    private void doTranslationXAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "translationX", 0, 200, -200, 0);
        animator.setDuration(2000);
        animator.start();
    }

    private void doTranslationYAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "translationY", 0, 200, 100, 0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * setScaleX与setScaleY
     * setScaleX(float scaleX):在X轴上缩放，scaleX表示缩放倍数
     * setScaleY(float scaleY):在Y轴上缩放，scaleY表示缩放倍数
     */
    private void doScaleXAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "scaleX", 1, 3, 1);
        animator.setDuration(2000);
        animator.start();
    }

    private void doScaleYAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "scaleY", 0, 2, 1);
        animator.setDuration(2000);
        animator.start();
    }
}
