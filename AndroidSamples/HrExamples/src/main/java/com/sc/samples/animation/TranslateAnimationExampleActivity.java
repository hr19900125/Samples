package com.sc.samples.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sc.samples.R;

/**
 * 代表一个水平、垂直的位移。对应的类为 TranslateAnimation. 属性
 * android:fromXDelta 属性代表起始 X 方向的位置
 * android:toXDelta
 * android:fromYDelta
 * android:toYDelta
 * 代表动画起始或者结束 X / Y 方向上的位置，Float 或者百分比值
 * 浮点数 num%、num%p 分别相对于自身或者父控件
 * 如果以浮点数字表示，是一个绝对值，代表相对自身原始位置的像素值；
 * 如果以 num%表示，代表相对于自己的百分比，比如 toXDelta 定义为 100%就表示在 X 方向上移动自己的 1 倍距离
 * 如果以 num%p 表示，代表相对于父类组件的百分比。
 *
 * http://www.cnblogs.com/bavariama/archive/2013/01/29/2881225.html
 */
public class TranslateAnimationExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_animation_example);

        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }
}
