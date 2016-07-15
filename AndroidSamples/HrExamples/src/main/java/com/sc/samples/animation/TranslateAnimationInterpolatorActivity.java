package com.sc.samples.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.sc.samples.R;

/**
 *
 */
public class TranslateAnimationInterpolatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator_example);
        mImageView = (ImageView) findViewById(R.id.image);

        Button accelerateDecelerate = (Button) findViewById(R.id.accelerate_decelerate);
        Button accelerate = (Button) findViewById(R.id.accelerate);
        Button anticipate = (Button) findViewById(R.id.anticipate);
        Button anticipateOvershoot = (Button) findViewById(R.id.anticipate_overshoot);
        Button bounce = (Button) findViewById(R.id.bounce);
        Button cycle = (Button) findViewById(R.id.cycle);
        Button decelerate = (Button) findViewById(R.id.decelerate);
        Button linear = (Button) findViewById(R.id.linear);
        Button overshoot = (Button) findViewById(R.id.overshoot);

        accelerateDecelerate.setOnClickListener(this);
        accelerate.setOnClickListener(this);
        anticipate.setOnClickListener(this);
        anticipateOvershoot.setOnClickListener(this);
        bounce.setOnClickListener(this);
        cycle.setOnClickListener(this);
        decelerate.setOnClickListener(this);
        linear.setOnClickListener(this);
        overshoot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.accelerate_decelerate:
                accelerateDecelerate();
                break;
            case R.id.accelerate:
                accelerate();
                break;
            case R.id.anticipate:
                anticipate();
                break;
            case R.id.anticipate_overshoot:
                anticipateOvershoot();
                break;
            case R.id.bounce:
                bounce();
                break;
            case R.id.cycle:
                cycle();
                break;
            case R.id.decelerate:
                decelerate();
                break;
            case R.id.linear:
                linear();
                break;
            case R.id.overshoot:
                overshoot();
                break;
        }
    }

    private TranslateAnimation getTranslateAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0, -200, 0, -200);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        return animation;
    }

    private void accelerateDecelerate() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        mImageView.startAnimation(animation);
    }

    private void accelerate() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new AccelerateInterpolator());
        mImageView.startAnimation(animation);
    }

    private void anticipate() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new AnticipateInterpolator());
        mImageView.startAnimation(animation);
    }

    private void anticipateOvershoot() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new AnticipateOvershootInterpolator());
        mImageView.startAnimation(animation);
    }

    private void bounce() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new BounceInterpolator());
        mImageView.startAnimation(animation);
    }

    private void cycle() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new CycleInterpolator(3.0f));
        mImageView.startAnimation(animation);
    }

    private void decelerate() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new DecelerateInterpolator());
        mImageView.startAnimation(animation);
    }

    private void linear() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new LinearInterpolator());
        mImageView.startAnimation(animation);
    }

    private void overshoot() {
        TranslateAnimation animation = getTranslateAnimation();
        animation.setInterpolator(new OvershootInterpolator());
        mImageView.startAnimation(animation);
    }
}
