package net.promasoft.trawellmate.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import net.promasoft.trawellmate.ForgotPAsswordAct;
import net.promasoft.trawellmate.HomeAct;
import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.SignupActivity;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.dsgn.CustomTextLink;


public class DialogLogin extends RelativeLayout {

    private OnLoginListner mLoginListner;
    private Activity mContext;
    private static ViewGroup mRoot;
    public static View mChild;
    private EditText mUserPassword, mUserName;


    public DialogLogin(Activity activity, boolean overlayNeeded, OnLoginListner loginListner) {
        super(activity);
        this.mLoginListner = loginListner;
        mContext = activity;
        if (mChild != null) {
            if (mRoot != null) {
                closePage();
            }
        }

        mRoot = (ViewGroup) getContentView(activity);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mChild = mInflater.inflate(R.layout.dialog_login, this, true);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        RelativeLayout overLay = mChild.findViewById(R.id.ID_login_overlay);
        overLay.setVisibility(overlayNeeded ? VISIBLE : GONE);

        CardView loginClose = findViewById(R.id.ID_hm_login_close);
        loginClose.setOnClickListener(view -> {
            loginPageHome.setVisibility(View.GONE);
            loginPageHome.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_down));
            DelayHelper.delayStartWith(300, () -> {
                closePage();
            });
        });
        loginCard = findViewById(R.id.Ida_login_card);
//        loginCard.setVisibility(View.GONE);
        loginPageHome = findViewById(R.id.ID_hm_login_page);

        loginPageHome.setVisibility(View.VISIBLE);
        loginPageHome.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_up));
        initAnim();
        initLayout();
    }

    private void initLayout() {

        mUserName = findViewById(R.id.ID_login_username);
        mUserPassword = findViewById(R.id.ID_login_password);


    }


    public void showPage() {
        try {
            closePage();
            mRoot.addView(mChild);
//            startBounce();
        } catch (Exception e) {
            // Toast.makeText(mContext, "Already has", Toast.LENGTH_SHORT).show();
        }
    }

    public void closePage() {

        try {
            mRoot.removeView(mChild);
        } catch (Exception e) {

        }
        //  stopAnim();

    }


    private View getContentView(Activity a) {
        int id = a.getResources().getIdentifier("content", "id", "android");
        return a.findViewById(id);
    }

    private CardView loginCard;
    private RelativeLayout loginPageHome;

    private void initAnim() {


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
//                startAnim(loginCard, 120, R.anim.slide_up_fast);
        startAnim(usernameCard, 200, R.anim.slide_up);
        startAnim(passwordCard, 250, R.anim.slide_up);
        startAnim(loginBt, 300, R.anim.slide_up);
        startAnim(textLink, 350, R.anim.slide_up);
        startAnim(signinwithText, 400, R.anim.slide_up);
        startAnim(signinwithCard, 450, R.anim.slide_up);

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateUser();
//                loginPageHome.setVisibility(View.GONE);
//                loginPageHome.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_down));
//                loginCard.setVisibility(View.GONE);
//                loginCard.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_down));
            }
        });

        CustomTextLink signUpText = findViewById(R.id.ID_signup_text);
        signUpText.setOnClickListener(view -> {
            if (mLoginListner != null) {
                mLoginListner.onSignUpClicked();
            }
        });

        CustomTextLink frgtPassEmailSubmit = findViewById(R.id.ID_login_forget_pass);
        frgtPassEmailSubmit.setOnClickListener(view -> {
            mContext.startActivity(new Intent(mContext, ForgotPAsswordAct.class));
        });

    }

    private void validateUser() {
        String userName = mUserName.getText().toString().trim();
        String userpass = mUserPassword.getText().toString().trim();
        if (userName.isEmpty()) {
            new ToastHelper(mContext, "Enter Username").setDuration(1000).show();
        } else if (userpass.isEmpty()) {
            new ToastHelper(mContext, "Enter Password").setDuration(1000).show();
        } else {
            if (userName.toLowerCase().equals("john") && userpass.toLowerCase().equals("123")) {
                if (mLoginListner != null) {
                    mLoginListner.onLoginSuccess();
                }

            } else {
                new ToastHelper(mContext, "Invalid Credentials").setDuration(1000).show();
            }
        }

    }

    private void startAnim(final View view, int delay, final int anim) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(AnimationUtils.loadAnimation(mContext, anim));

                } catch (Exception e) {

                }
//                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_anim);
//                anim.setTarget(view);
//                anim.setDuration(800);
//                anim.start();
            }
        }, delay);
    }

    public interface OnLoginListner {
        void onLoginSuccess();

        void onSignUpClicked();
    }

}