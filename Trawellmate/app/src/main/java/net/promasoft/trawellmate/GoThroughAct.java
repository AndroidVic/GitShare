package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GoThroughAct extends AppCompatActivity {

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

    }

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

}
