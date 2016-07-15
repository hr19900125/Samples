package com.sc.samples.animation;

import android.content.Intent;

import com.sc.samples.BaseExampleFragment;

/**
 * Android Animation Examples
 * <p/>
 * http://blog.csdn.net/harvic880925/article/details/50995268
 * <p/>
 * 从Animation类继承的属性
 * Animation类是所有动画（scale、alpha、translate、rotate）的基类，这里以scale标签为例，讲解一下，Animation类所具有的属性及意义。关于Animation类的官方文档位置为：《Animation》
 * android:duration        动画持续时间，以毫秒为单位
 * android:fillAfter          如果设置为true，控件动画结束时，将保持动画最后时的状态
 * android:fillBefore       如果设置为true,控件动画结束时，还原到开始动画前的状态
 * android:fillEnabled    与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
 * android:repeatCount 重复次数
 * android:repeatMode	重复类型，有reverse和restart两个值，reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用才能看到效果。因为这里的意义是重复的类型，即回放时的动作。
 * android:interpolator  设定插值器，其实就是指定的动作效果，比如弹跳效果等，不在这小节中讲解，后面会单独列出一单讲解。
 * 对于android:duration，就不再讲解了，就是动画的持续时长，以毫秒为单位，下面看看android:fillAfter和android:fillBefore
 */
public class AnimationExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{
                "Alpha Animation",
                "Scale Animation",
                "Translate Animation",
                "Rotate Animation",
                "Set Animation",
                "Scale Animation Interpolator",
                "Rotate Animation Interpolator",
                "Alpha Animation Interpolator",
                "Translate Animation Interpolator",
                "Value Animator(1)"
        };
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), AlphaAnimationExampleActivity.class);
                break;
            case 1:
                intent = new Intent();
                intent.setClass(getActivity(), ScaleAnimationExampleActivity.class);
                break;
            case 2:
                intent = new Intent();
                intent.setClass(getActivity(), TranslateAnimationExampleActivity.class);
                break;
            case 3:
                intent = new Intent();
                intent.setClass(getActivity(), RotateAnimationExampleActivity.class);
                break;
            case 4:
                intent = new Intent();
                intent.setClass(getActivity(), SetAnimationExampleActivity.class);
                break;
            case 5:
                intent = new Intent();
                intent.setClass(getActivity(), ScaleAnimationInterpolatorActivity.class);
                break;
            case 6:
                intent = new Intent();
                intent.setClass(getActivity(), RotateAnimationInterpolatorActivity.class);
                break;
            case 7:
                intent = new Intent();
                intent.setClass(getActivity(), AlphaAnimationInterpolatorActivity.class);
                break;
            case 8:
                intent = new Intent();
                intent.setClass(getActivity(), TranslateAnimationInterpolatorActivity.class);
                break;
            case 9:
                intent = new Intent();
                intent.setClass(getActivity(), ValueAnimatorExample1Activity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
