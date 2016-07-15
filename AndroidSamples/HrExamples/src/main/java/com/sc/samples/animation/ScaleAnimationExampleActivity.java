package com.sc.samples.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sc.samples.R;

/**
 * scale标签是缩放动画，可以实现动态调控件尺寸的效果，有下面几个属性：
 * <p/>
 * android:fromXScale    起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
 * android:toXScale        结尾的X方向上相对自身的缩放比例，浮点值；
 * android:fromYScale    起始的Y方向上相对自身的缩放比例，浮点值，
 * android:toYScale        结尾的Y方向上相对自身的缩放比例，浮点值；
 * android:pivotX            缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，当为数值时，表示在当前View的左上角，即原点处加上50px，做为起始缩放点；如果是50%，表示在当前控件的左上角加上自己宽度的50%做为起始点；如果是50%p，那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标。（具体意义，后面会举例演示）
 * android:pivotY           缩放起点Y轴坐标，取值及意义跟android:pivotX一样。
 */
public class ScaleAnimationExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_animation_example);

        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }
}
