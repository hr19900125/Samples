package com.sc.samples.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sc.samples.R;

/**
 * 一个渐入渐出的动画，对应的 java 类为 AlphaAnimation
 * 属性
 * android:fromAlpha
 * android:toAlpha
 * 代表动画开始和结束时透明度，0.0 表示完全透明，1.0 表示完全不透明，Float 值
 */
public class AlphaAnimationExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_animation_example);

        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }
}
