package com.sc.samples.animation;

import android.animation.TypeEvaluator;

/**
 *
 */
public class PointEvaluator implements TypeEvaluator<Point> {

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int start = startValue.getRadius();
        int end = endValue.getRadius();
        int radius = (int) (start + (end - start) * fraction);
        return new Point(radius);
    }
}
