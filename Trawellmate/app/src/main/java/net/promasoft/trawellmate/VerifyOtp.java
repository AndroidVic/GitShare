package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mukesh.OtpView;

import net.promasoft.trawellmate.argapp.UserConstantResult;
import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.args.RegisterArgs;
import net.promasoft.trawellmate.args.RegisterResult;
import net.promasoft.trawellmate.args.ValidatMobileResult;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.ntwk.LoginReqsVly;
import net.promasoft.trawellmate.util.AlertMsgDialog;
import net.promasoft.trawellmate.util.AppConstant;
import net.promasoft.trawellmate.util.LoadingScreen;

public class VerifyOtp extends AppCompatActivity implements View.OnClickListener {
    private Button validateButton;
    private OtpView otpView;
    private TextView cancelButton;
    private String userNumber = "";
    private long tempId ;
    private boolean needReturn = false;
    private LoadingScreen loadingScreen;
    private UserDetailsArg userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_otp);
        loadingScreen = new LoadingScreen(VerifyOtp.this);
        initializeUi();
        setListeners();
        needReturn = getIntent().getBooleanExtra("needReturn", false);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.validate_btn) {

            callVerifyOtp(otpView.getText().toString());

        }
    }

    private void processHome() {
        userData = UserPrefHelper.getInstance(VerifyOtp.this).getUserData();
        if (userData != null) {
            userData.userMobile = userNumber;
            UserPrefHelper.getInstance(VerifyOtp.this).setUserData(userData);
        }
        submitRegister();

    }

    public void submitRegister() {

        RegisterArgs registerArgs = new RegisterArgs();
        registerArgs.nickname = userData.userFullName;
        registerArgs.password = "";
        registerArgs.mobile = userData.userMobile;
        registerArgs.email = userData.userEmail;
        registerArgs.country = "IN";

        loadingScreen.showLoading();
        new LoginReqsVly().register(VerifyOtp.this, registerArgs, new LoginReqsVly.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                RegisterResult emailResult = new Gson().fromJson(result, RegisterResult.class);
                if (emailResult.getStatus().equalsIgnoreCase(AppConstant.SUCCESS)) {
                    Toast.makeText(VerifyOtp.this, "Welcome " +  userData.userFullName, Toast.LENGTH_SHORT).show();
                    SharedPrefHelper.getInstance(VerifyOtp.this).setIsLogin(true);

                    UserConstantResult manager = new UserConstantResult();
                    manager.TOKEN = emailResult.getTokenId();
                    manager.USER_ID = emailResult.getDataRegister().getUserDetails().getUid();

                    AppConstant.TOKEN = emailResult.getTokenId();
                    AppConstant.USER_ID = emailResult.getDataRegister().getUserDetails().getUid();

                    UserPrefHelper.getInstance(VerifyOtp.this).setUserConst(manager);
                    UserPrefHelper.getInstance(VerifyOtp.this).setUserData(userData);

                    continueHome();

                } else {
                    new AlertMsgDialog(VerifyOtp.this, AlertMsgDialog.DIA_INFO, "Failed", "" + emailResult.getMessage(), "ok", new AlertMsgDialog.ActionListner() {
                        @Override
                        public void onPositive() {

                        }

                        @Override
                        public void onNegative() {

                        }
                    }).showPage();

                }
                loadingScreen.cancelLoading();
            }

            @Override
            public void onRequestError(VolleyError errorMessage) {
                loadingScreen.cancelLoading();

            }

            @Override
            public void onIntrnError(String errorMessage) {
                loadingScreen.cancelLoading();

            }
        });

    }

    private void continueHome() {
        SharedPrefHelper.getInstance(VerifyOtp.this).setIsLogin(true);
        Intent intent = new Intent(VerifyOtp.this, HomeAct.class);
        ActivityCompat.finishAffinity(this);
        startActivity(intent);
        try {
            if (AppConstant.MOBILE_VALID_ACT != null) {
                AppConstant.MOBILE_VALID_ACT.finish();
            }
        } catch (Exception e) {

        }
        finish();
    }


    private void initializeUi() {

        TextView number = findViewById(R.id.ID_ver_otp_number);
        userNumber = getIntent().getStringExtra("mobile");
        tempId = getIntent().getLongExtra("tempid",0);
        number.setText("" + userNumber);

        otpView = findViewById(R.id.otp_view);
        cancelButton = findViewById(R.id.cancel_btn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("numberVerified", false);
                setResult(SignupActivity.OTP_REQ_CODE, intent);
                finish();
            }
        });

        validateButton = findViewById(R.id.validate_btn);
    }

    public void callVerifyOtp(String otp) {
        loadingScreen.showLoading();
        new LoginReqsVly().otpVerification(VerifyOtp.this, tempId, userNumber, otp, new LoginReqsVly.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                loadingScreen.cancelLoading();

                ValidatMobileResult mobileResult = new Gson().fromJson(result, ValidatMobileResult.class);
                if (mobileResult.getStatus().equalsIgnoreCase(AppConstant.SUCCESS)) {
                    Toast.makeText(VerifyOtp.this, "" + mobileResult.getMessage(), Toast.LENGTH_SHORT).show();
                    if (needReturn) {
                        Intent intent = new Intent();
                        intent.putExtra("numberVerified", true);
                        setResult(SignupActivity.OTP_REQ_CODE, intent);
                        finish();
                    } else {
                        processHome();
                    }
                } else {
                    //error msg
                    new AlertMsgDialog(VerifyOtp.this, AlertMsgDialog.DIA_INFO, "Error", "Incorrect otp.Please check the otp you have entered!!", "ok", new AlertMsgDialog.ActionListner() {
                        @Override
                        public void onPositive() {
                        }

                        @Override
                        public void onNegative() {

                        }
                    }).showPage();
                }


            }

            @Override
            public void onRequestError(VolleyError errorMessage) {
                loadingScreen.cancelLoading();

            }

            @Override
            public void onIntrnError(String errorMessage) {
                loadingScreen.cancelLoading();
                Toast.makeText(getApplicationContext(), "" + errorMessage, Toast.LENGTH_LONG);

            }
        });

    }

    private void setListeners() {
        validateButton.setOnClickListener(this);
//        otpView.setOtpCompletionListener(this);
    }


}
