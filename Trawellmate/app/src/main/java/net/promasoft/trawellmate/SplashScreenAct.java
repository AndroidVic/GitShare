package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import net.promasoft.trawellmate.argapp.UserConstantResult;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.AppConstant;

public class SplashScreenAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new AlineActivityHelper(SplashScreenAct.this, false);

        ImageView appLogo = findViewById(R.id.Ida_splash_icon);
//        startAnim(appLogo, 100, R.anim.rotate_y);
        appLogo.setVisibility(View.INVISIBLE);
//        startAnim(appLogo, 200, R.anim.rotate_y_inter);
//        startAnim(appLogo, 1600, R.anim.fade_out_anim);
//        startAnim(appLogo, 1800, R.anim.fade_in_anim);
        startAnim(appLogo, 200, R.anim.bounce_down);
        startAnim(appLogo, 650, R.anim.fade_out_anim);
        startAnim(appLogo, 850, R.anim.fade_in_anim);
        continueService(2);

        UserConstantResult userConst = UserPrefHelper.getInstance(SplashScreenAct.this).getUserConst();
        if (userConst != null) {
            AppConstant.TOKEN = userConst.TOKEN;
            AppConstant.USER_ID = userConst.USER_ID;
        }

    }

    private void continueService(int delay) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resumeFlow();
                finish();
            }
        }, delay * 1000);

    }

    private void resumeFlow() {
        if (SharedPrefHelper.getInstance(SplashScreenAct.this).getIsFirstTime()) {
            startActivity(new Intent(SplashScreenAct.this, GoThroughAct.class));
        } else {
            if (SharedPrefHelper.getInstance(SplashScreenAct.this).getIsLogin()) {
                startActivity(new Intent(SplashScreenAct.this, HomeAct.class));
            } else {
                startActivity(new Intent(SplashScreenAct.this, StartPageAct.class));
            }
        }

    }

    private void startAnim(final View view, int delay, final int anim) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(AnimationUtils.loadAnimation(SplashScreenAct.this, anim));

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
