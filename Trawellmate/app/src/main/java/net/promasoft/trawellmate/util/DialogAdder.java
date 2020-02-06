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
import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.SignupActivity;
import net.promasoft.trawellmate.dsgn.CustomTextLink;


public class DialogAdder extends RelativeLayout {

    private OnLoginListner mLoginListner;
    private Activity mContext;
    private static ViewGroup mRoot;
    public static View mChild;
    private EditText mUserPassword, mUserName;


    public DialogAdder(Activity activity, OnLoginListner loginListner) {
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
        mChild = mInflater.inflate(R.layout.prompt_login, this, true);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);


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
    }

}