package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class GoThroughAct extends AppCompatActivity {


    private ViewFlipper mViewFlipper;
    int SLIDER_SIZE = 0;
    int mCurIndex = 0;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_through);

        RelativeLayout page1 = findViewById(R.id.ID_gothr_page1);
        RelativeLayout page2 = findViewById(R.id.ID_gothr_page2);
        RelativeLayout page3 = findViewById(R.id.ID_gothr_page3);

        Button nxtBt1 = findViewById(R.id.Ida_gothr_next1);
        Button nxtBt2 = findViewById(R.id.Ida_gothr_next2);
        Button nxtBt3 = findViewById(R.id.Ida_gothr_next3);


        nxtBt1.setOnClickListener(view -> {
            page1.setVisibility(View.GONE);
            page1.startAnimation(AnimationUtils.loadAnimation(GoThroughAct.this, R.anim.slide_to_left));
            page2.setVisibility(View.VISIBLE);
            page2.startAnimation(AnimationUtils.loadAnimation(GoThroughAct.this, R.anim.slide_from_right));
            initPage2Anim();

        });
        nxtBt2.setOnClickListener(view -> {
            page2.setVisibility(View.GONE);
            page2.startAnimation(AnimationUtils.loadAnimation(GoThroughAct.this, R.anim.slide_to_left));
            page3.setVisibility(View.VISIBLE);
            page3.startAnimation(AnimationUtils.loadAnimation(GoThroughAct.this, R.anim.slide_from_right));
            initPage3Anim();
        });
        nxtBt3.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(GoThroughAct.this, LoginAct.class));

        });

        initPage1Anim();
        initViewFlipper();
        addDots();

    }

    private void initViewFlipper() {

        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_right));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_to_left));
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Toast.makeText(FirstFlowAct.this, "end", Toast.LENGTH_SHORT).show();
//                animateChild();
                selectDot(mCurIndex);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        SLIDER_SIZE = mViewFlipper.getChildCount() - 1;

    }

    private List<ImageView> dots;

    public void addDots() {
        dots = new ArrayList<>();
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.ID_dot_indicator);

        for (int i = 0; i < SLIDER_SIZE + 1; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(getResources().getDrawable(R.drawable.circle_default));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.gravity = Gravity.CENTER;
            params.setMargins(10, 0, 10, 0);
            dotsLayout.addView(dot, params);

            dots.add(dot);
        }
        selectDot(mCurIndex);
    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < SLIDER_SIZE + 1; i++) {
            int drawableId = (i == idx) ? (R.drawable.circle_selected) : (R.drawable.circle_default);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }

//    private void animateChild() {
//        selectDot(mCurIndex);
//
//        RelativeLayout base = (RelativeLayout) mViewFlipper.getChildAt(mCurIndex);
//        LinearLayout childBase = (LinearLayout) base.getChildAt(1);
//        TextView textView = (TextView) childBase.getChildAt(0);
//        textView.setVisibility(View.VISIBLE);
//        textView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.item_animation_pop_up));
//        if (childBase.getChildCount() > 1) {
//            TextView textView1 = (TextView) childBase.getChildAt(1);
//            textView1.setVisibility(View.VISIBLE);
//            textView1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.item_animation_pop_up));
//        }
//
//    }

    private void initPage1Anim() {

        ImageView gothrImg1 = findViewById(R.id.Ida_gothr_img1);
        gothrImg1.setVisibility(View.GONE);
        ImageView gothrImg2 = findViewById(R.id.Ida_gothr_img2);
        gothrImg2.setVisibility(View.GONE);
        ImageView gothrImg3 = findViewById(R.id.Ida_gothr_img3);
        gothrImg3.setVisibility(View.GONE);
        ImageView gothrObj1 = findViewById(R.id.Ida_gothr_obj1);
        gothrObj1.setVisibility(View.GONE);

        startAnim(gothrImg1, 100, R.anim.slide_from_left);
        startAnim(gothrImg3, 300, R.anim.slide_from_right);
        startAnim(gothrImg2, 400, R.anim.slide_from_left);
        startAnim(gothrObj1, 600, R.anim.slide_from_left);

        startAnim(gothrImg1, 800, R.anim.floating_anim_x);
        startAnim(gothrImg3, 900, R.anim.floating_anim_y);
        startAnim(gothrImg2, 900, R.anim.floating_anim_x_lng);

        startAnim(gothrObj1, 920, R.anim.floating_anim_x_lng);


    }

    private void initPage2Anim() {

        ImageView gothrImg1 = findViewById(R.id.Ida_gothr_img4);
        gothrImg1.setVisibility(View.GONE);
        ImageView gothrImg2 = findViewById(R.id.Ida_gothr_img5);
        gothrImg2.setVisibility(View.GONE);
        ImageView gothrImg3 = findViewById(R.id.Ida_gothr_img6);
        gothrImg3.setVisibility(View.GONE);
        ImageView gothrObj1 = findViewById(R.id.Ida_gothr_obj2);
        gothrObj1.setVisibility(View.GONE);

//        startAnim(gothrImg1, 100, R.anim.slide_from_left);
//        startAnim(gothrImg3, 300, R.anim.slide_from_right);
//        startAnim(gothrImg2, 400, R.anim.slide_from_left);
        startAnim(gothrObj1, 10, R.anim.slide_from_left);

        startAnim(gothrImg1, 100, R.anim.floating_anim_y_up);
        startAnim(gothrImg3, 200, R.anim.floating_anim_x);
        startAnim(gothrImg2, 300, R.anim.floating_anim_x_lng);

        startAnim(gothrObj1, 350, R.anim.floating_anim_x_lng);


    }

    private void initPage3Anim() {

        ImageView gothrImg1 = findViewById(R.id.Ida_gothr_img7);
        gothrImg1.setVisibility(View.GONE);
        ImageView gothrImg2 = findViewById(R.id.Ida_gothr_img8);
        gothrImg2.setVisibility(View.GONE);
        ImageView gothrImg3 = findViewById(R.id.Ida_gothr_img9);
        gothrImg3.setVisibility(View.GONE);
        ImageView gothrObj1 = findViewById(R.id.Ida_gothr_obj3);
        gothrObj1.setVisibility(View.GONE);

//        startAnim(gothrImg1, 100, R.anim.slide_from_left);
//        startAnim(gothrImg3, 300, R.anim.slide_from_right);
//        startAnim(gothrImg2, 400, R.anim.slide_from_left);
        startAnim(gothrObj1, 10, R.anim.slide_from_left);

        startAnim(gothrImg1, 100, R.anim.floating_anim_y_up);
        startAnim(gothrImg3, 200, R.anim.floating_anim_x);
        startAnim(gothrImg2, 300, R.anim.floating_anim_x_lng);

        startAnim(gothrObj1, 350, R.anim.floating_anim_x_lng);

    }


    private void startAnim(final View view, int delay, final int anim) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(AnimationUtils.loadAnimation(GoThroughAct.this, anim));

                } catch (Exception e) {

                }
//                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_anim);
//                anim.setTarget(view);
//                anim.setDuration(800);
//                anim.start();
            }
        }, delay);
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_right));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_to_left));
                    mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
//                            animateChild();
                            selectDot(mCurIndex);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mViewFlipper.showNext();
                    mCurIndex++;
                    if (mCurIndex == 1) {
                        initPage2Anim();
                    } else if (mCurIndex == 2) {
                        initPage3Anim();
                    }
                    if (mCurIndex > SLIDER_SIZE) {
                        mCurIndex = 0;
                    }
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_left));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_to_right));
                    mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
//                            animateChild();
                            selectDot(mCurIndex);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mViewFlipper.showPrevious();
                    mCurIndex--;
                    if (mCurIndex < 0) {
                        mCurIndex = SLIDER_SIZE;
                    }
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

}
