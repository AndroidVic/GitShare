package net.promasoft.trawellmate.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import net.promasoft.trawellmate.R;


public class VerifyOtpDialog extends RelativeLayout implements OnOtpCompletionListener, View.OnClickListener {

    private final RelativeLayout loadingContainer;
    private Activity mContext;
    private ViewGroup mRoot;
    private View mChild;
    private Snackbar snackbar;
    private OtpView otpView;
    EditText otp_1, otp_2, otp_3, otp_4;
    int pin1, pin2, pin3, pin4;
    private Button validateButton;
    private Button cancelButton;

    public VerifyOtpDialog (Activity activity) {
        super(activity);
        mContext = activity;
        mRoot = (ViewGroup) getContentView(activity);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mChild = mInflater.inflate(R.layout.layout_otp, this, true);
        initializeUi();
        setListeners();
        /*otpView = findViewById(R.id.otp_view);
        otpView.setListener(new OnOtpCompletionListener() {
            @Override public void onOtpCompleted(String otp) {

                // do Stuff
                Log.d("onOtpCompleted=>", otp);
            }
        });
*/



        /*int pin1 = Integer.parseInt(otp_1.getText().toString().trim());
        int pin2 = Integer.parseInt(otp_2.getText().toString().trim());
        int pin3 = Integer.parseInt(otp_3.getText().toString().trim());
        int pin4 = Integer.parseInt(otp_4.getText().toString().trim());*/

        loadingContainer = (RelativeLayout) mChild.findViewById(R.id.Loading_Container);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

//        EditText newPass = (EditText) findViewById(R.id.ID_c_pass_new_pass);

//        ImageView closeBt = findViewById(R.id.ID_c_pass_close);
//        closeBt.setOnClickListener(view -> {
//            closePage();
//        });
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


    @Override
    public void onOtpCompleted(String otp) {

    }
    private void initializeUi() {
        otpView = findViewById(R.id.otp_view);
        validateButton = findViewById(R.id.validate_btn);
    }

    private void setListeners() {
        validateButton.setOnClickListener(this);
        otpView.setOtpCompletionListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}