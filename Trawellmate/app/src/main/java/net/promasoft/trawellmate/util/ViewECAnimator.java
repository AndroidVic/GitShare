package net.promasoft.trawellmate.util;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Toast;

//
// Created by ViC on 10-Jan-19.
//
public class ViewECAnimator {

    public static void expand(final View v, final int minHeight, final int maxHeight, final int type, CmpltListner cmpltListner) {//0- both , 1-height , 2-width

//        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        final int targetHeight = v.getMeasuredHeight();
        final int targetHeight = maxHeight;

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        if (type == 0 || type == 1) {
            v.getLayoutParams().height = minHeight;
        }
        if (type == 0 || type == 2) {
            v.getLayoutParams().width = minHeight;
        }
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {


            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int hei = interpolatedTime >= 1
                        ? maxHeight
                        : (int) (targetHeight * interpolatedTime);
//                if (hei <= maxHeight) {
                if (type == 0 || type == 1) {
                    v.getLayoutParams().height = hei < minHeight ? minHeight : hei;
                }
                if (type == 0 || type == 2) {
                    v.getLayoutParams().width = hei < minHeight ? minHeight : hei;
                }
                v.requestLayout();
//                } else {
//                    v.getLayoutParams().height = maxHeight;
//                    v.getLayoutParams().width = maxHeight;
//                    v.requestLayout();
//                }

                if (interpolatedTime == 1) {
                    if (cmpltListner != null) {
                        cmpltListner.onCompleted();
                    }
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void expand(final View v, final int minHeight, final int maxHeight, final int type) {//0- both , 1-height , 2-width

//        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        final int targetHeight = v.getMeasuredHeight();
        final int targetHeight = maxHeight;

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        if (type == 0 || type == 1) {
            v.getLayoutParams().height = minHeight;
        }
        if (type == 0 || type == 2) {
            v.getLayoutParams().width = minHeight;
        }
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {


            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int hei = interpolatedTime >= 1
                        ? maxHeight
                        : (int) (targetHeight * interpolatedTime);
//                if (hei <= maxHeight) {
                if (type == 0 || type == 1) {
                    v.getLayoutParams().height = hei < minHeight ? minHeight : hei;
                }
                if (type == 0 || type == 2) {
                    v.getLayoutParams().width = hei < minHeight ? minHeight : hei;
                }
                v.requestLayout();
//                } else {
//                    v.getLayoutParams().height = maxHeight;
//                    v.getLayoutParams().width = maxHeight;
//                    v.requestLayout();
//                }


            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v, final int minHeight, final int maxHeight, final int type) {//0- both , 1-height , 2-width
//        final int initialHeight = v.getMeasuredHeight();
        final int initialHeight = maxHeight;

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.d("Collapse", "interpolator: " + interpolatedTime);
                if (interpolatedTime == 1) {
                    if (minHeight == 0) {
                        v.setVisibility(View.GONE);
                    }
                } else {
                    int hei = initialHeight - (int) (initialHeight * interpolatedTime);
                    if (hei >= minHeight) {
                        if (type == 0 || type == 1) {
                            v.getLayoutParams().height = hei;
                        }
                        if (type == 0 || type == 2) {
                            v.getLayoutParams().width = hei;
                        }
                        v.requestLayout();
                    } else {
                        if (type == 0 || type == 1) {
                            v.getLayoutParams().height = minHeight;
                        }
                        if (type == 0 || type == 2) {
                            v.getLayoutParams().width = minHeight;
                        }
                        v.requestLayout();
                    }
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


    public static void expandToWrap(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            return;
        }
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime >= 0.5
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapseFromWrap(View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public interface CmpltListner {

        void onCompleted();
    }
}
