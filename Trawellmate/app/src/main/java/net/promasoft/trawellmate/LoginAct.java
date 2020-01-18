package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.promasoft.trawellmate.dsgn.CustomTextLink;

public class LoginAct extends AppCompatActivity {

    private LinearLayout infoTextLay;
    private TextView signinText;
    private CardView loginCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(flag);
        setContentView(R.layout.activity_login);


        loginCard = findViewById(R.id.Ida_login_card);
        loginCard.setVisibility(View.GONE);
        infoTextLay = findViewById(R.id.Ida_info_text);
        infoTextLay.setVisibility(View.VISIBLE);
        signinText = findViewById(R.id.Ida_signintext);

        final TextView text1 = findViewById(R.id.Ida_text1);
        final TextView text2 = findViewById(R.id.Ida_text2);
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        startAnim(text1, 100, R.anim.fade_in_anim);
        startAnim(text2, 500, R.anim.fade_in_anim);


        final CardView usernameCard = findViewById(R.id.IDa_username);
        usernameCard.setVisibility(View.INVISIBLE);
        final CardView passwordCard = findViewById(R.id.Ida_password);
        passwordCard.setVisibility(View.INVISIBLE);
        final Button loginBt = findViewById(R.id.ID_login_signin);
        loginBt.setVisibility(View.INVISIBLE);
        final CustomTextLink textLink = findViewById(R.id.ID_login_forget_pass);
        textLink.setVisibility(View.INVISIBLE);
        final TextView signinwithText = findViewById(R.id.Ida_signn_withtext);
        signinwithText.setVisibility(View.INVISIBLE);
        final LinearLayout signinwithCard = findViewById(R.id.Ida_signn_with);
        signinwithCard.setVisibility(View.INVISIBLE);


        Button continueBt = findViewById(R.id.ID_continue_bt);
        continueBt.setVisibility(View.INVISIBLE);
        startAnim(continueBt, 1400, R.anim.item_animation_fall_down);

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoTextLay.setVisibility(View.GONE);
                infoTextLay.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, R.anim.fade_out_anim));
//                loginCard.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, R.anim.fade_in_anim));
//                signinText.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, R.anim.fade_in_anim));
//                loginCard.setVisibility(View.VISIBLE);
//                signinText.setVisibility(View.VISIBLE);

                startAnim(signinText, 100, R.anim.fade_in_anim);
                startAnim(loginCard, 120, R.anim.slide_up_fast);
                startAnim(usernameCard, 200, R.anim.slide_up);
                startAnim(passwordCard, 250, R.anim.slide_up);
                startAnim(loginBt, 300, R.anim.slide_up);
                startAnim(textLink, 350, R.anim.slide_up);
                startAnim(signinwithText, 400, R.anim.slide_up);
                startAnim(signinwithCard, 450, R.anim.slide_up);

            }
        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginAct.this, HomeAct.class));
            }
        });

        CustomTextLink signUpText = findViewById(R.id.ID_signup_text);
        signUpText.setOnClickListener(view -> {
            startActivity(new Intent(LoginAct.this, SignupActivity.class));
        });

    }

    private void startAnim(final View view, int delay, final int anim) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, anim));

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
