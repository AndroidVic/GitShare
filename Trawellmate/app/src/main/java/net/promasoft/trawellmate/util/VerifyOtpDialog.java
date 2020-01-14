package net.promasoft.trawellmate.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

import net.promasoft.trawellmate.R;


public class VerifyOtpDialog extends RelativeLayout {

    private final RelativeLayout loadingContainer = null;
    private Activity mContext;
    private ViewGroup mRoot;
    private View mChild;
    private Snackbar snackbar;

    public VerifyOtpDialog(Activity activity) {
        super(activity);
        mContext = activity;
        mRoot = (ViewGroup) getContentView(activity);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mChild = mInflater.inflate(R.layout.cus_password_change, this, true);
//
//        loadingContainer = (RelativeLayout) mChild.findViewById(R.id.Loading_Container);

        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

//        EditText newPass = (EditText) findViewById(R.id.ID_c_pass_new_pass);

//        ImageView closeBt = findViewById(R.id.ID_c_pass_close);
//        closeBt.setOnClickListener(view -> {
//            closePage();
//        });
    }


    private void showMessage(String msg) {
        if (snackbar == null) {
            snackbar = Snackbar
                    .make(loadingContainer, msg, Snackbar.LENGTH_SHORT);
            try {
                SnackbarContentLayout contentLayout = (SnackbarContentLayout) ((FrameLayout) snackbar.getView()).getChildAt(0);
                @SuppressLint("RestrictedApi") TextView tv = contentLayout.getMessageView();
                tv.setTextColor(Color.WHITE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        snackbar.setText(msg);
        snackbar.show();
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


}