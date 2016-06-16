package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sc.samples.R;

/**
 * Android View 滑动的实现
 */
public class MoveViewActivity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view);

//        //4.使用View动画来移动
//        final View moveView = findViewById(R.id.move_view);
////        moveView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.move_view_translate));
////        moveView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_view_translate));
//        moveView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator.ofFloat(moveView, "translationX", 0, 300).setDuration(1000).start();
//            }
//        });

        final View moveView = findViewById(R.id.move_view);
        moveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoveView)moveView).smoothScrollTo(-400 , 0);
            }
        });
    }
}
