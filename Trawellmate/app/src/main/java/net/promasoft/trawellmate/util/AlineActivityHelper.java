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

import com.google.android.material.snackbar.Snackbar;
import com.mukesh.OtpView;

import net.promasoft.trawellmate.ForgotPAsswordAct;
import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.SignupActivity;
import net.promasoft.trawellmate.dsgn.CustomTextLink;


public class AlineActivityHelper extends RelativeLayout {

    public AlineActivityHelper(Activity activity, boolean addStatusBar) {
        super(activity);
        int statusBarHeight = ScreenDiaHelper.getStatusBarHeight(activity);
        setMargins(getContentView(activity), -1 * statusBarHeight);
        if (addStatusBar) {
            setStatusBar(activity);
        }
    }

    private View getContentView(Activity a) {
        int id = a.getResources().getIdentifier("content", "id", "android");
        return a.findViewById(id);
    }

    public static void setMargins(View v, int top) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(0, top, 0, 0);
            v.requestLayout();
        }
    }

    public static void setStatusBar(Activity activity) {
        try {
            int statusBarHeight = ScreenDiaHelper.getStatusBarHeight(activity);
            View startsBarBg = activity.findViewById(R.id.ID_sts_bg_lay);
            ViewGroup.LayoutParams layoutParams = startsBarBg.getLayoutParams();
            layoutParams.height = statusBarHeight;
            startsBarBg.setLayoutParams(layoutParams);
        } catch (Exception e) {

        }
    }


}