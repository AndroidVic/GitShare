package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import net.promasoft.trawellmate.argapp.UserConstantResult;
import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.args.RegisterArgs;
import net.promasoft.trawellmate.args.RegisterResult;
import net.promasoft.trawellmate.args.ValidatMobileResult;
import net.promasoft.trawellmate.args.ValidateEmailResult;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.dsgn.CheckView;
import net.promasoft.trawellmate.dsgn.CustomTextLink;
import net.promasoft.trawellmate.ntwk.LoginReqsVly;
import net.promasoft.trawellmate.util.AlertMsgDialog;
import net.promasoft.trawellmate.util.AppConstant;
import net.promasoft.trawellmate.util.DialogLogin;
import net.promasoft.trawellmate.util.LoadingScreen;
import net.promasoft.trawellmate.util.ToastHelper;
import net.promasoft.trawellmate.util.VerifyOtpDialog;

public class SignupActivity extends AppCompatActivity {

    public static final int OTP_REQ_CODE = 1111;
    TextView mVerifyMobile;
    VerifyOtpDialog verifyOtpDialog;
    String msg = "Invalid number";
    CoordinatorLayout layout;
    EditText mPhone;
    TextInputLayout mTextInputPhone;
    EditText mName;

    String mSignupPass, mSignupCpass, mUsername, mEmailId, mPhoneNo;
    EditText mPass, mCpass;
    TextInputLayout mTextInputName, mTextInputEmail, mTextInputPassword, mTextInputCpassword;
    EditText mEmail;
    Button mSignup;
    private DialogLogin dialogLogin;
    private LoadingScreen loadingScreen;
    private String mMobileNumber = "";
    private CheckView mEmailCheckView;
    private CheckView mMobileCheckView;
    private boolean mMobileVerifiedStatus = false;
    private boolean mEmailValidStatus = false;
    private String VerifiedEmailID = "";
    private boolean MobileVerified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        layout = findViewById(R.id.signup_parent);

        mName = findViewById(R.id.ID_signup_name);
        mVerifyMobile = findViewById(R.id.ID_mobile_verify);
        mPhone = findViewById(R.id.ID_signup_phone);
        mTextInputPhone = findViewById(R.id.ID_layoutPhoneNo);


        mEmail = findViewById(R.id.ID_signup_email);
        mPass = findViewById(R.id.ID_signup_password);
        mCpass = findViewById(R.id.ID_signup_password_confirm);
        mTextInputName = findViewById(R.id.layoutName);
        mTextInputEmail = findViewById(R.id.layoutEmail);
        mTextInputPassword = findViewById(R.id.layoutPassword);
        mTextInputCpassword = findViewById(R.id.layoutCPassword);
        mEmailCheckView = findViewById(R.id.ID_email_verified);
        mMobileCheckView = findViewById(R.id.ID_mobile_verified);

        CustomTextLink mSignIn = findViewById(R.id.ID_signup_signin);
        mSignIn.setOnClickListener(view -> {

            dialogLogin = new DialogLogin(SignupActivity.this, true, new DialogLogin.OnLoginListner() {
                @Override
                public void onLoginSuccess() {

                }

                @Override
                public void onSignUpClicked() {
                    dialogLogin.closePage();
                }

                @Override
                public void onGoogleClicked() {

                }

                @Override
                public void onFacebokClicked() {

                }
            });
            dialogLogin.showPage();

        });
        mSignup = findViewById(R.id.ID_signup_signupbtn);
        mSignup.setOnClickListener(v -> {
            mSignupCpass = mCpass.getText().toString().trim();
            mSignupPass = mPass.getText().toString().trim();
            mUsername = mName.getText().toString().trim();
            mPhoneNo = mPhone.getText().toString().trim();
            mEmailId = mEmail.getText().toString().trim();
            validate();
        });


        mVerifyMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFieldMobile(mTextInputPhone, "Invalid mobile number")) {
                    mTextInputPhone.setError(null);
                    checkMobileDuplication(mMobileNumber);
                } else {
                    new ToastHelper(SignupActivity.this, "Invalid mobile number").setDuration(2000).setBgColor(R.color.colorPrimary).show();
                    mTextInputPhone.setError("Invalid mobile number");
                }
            }
        });

        loadingScreen = new LoadingScreen(SignupActivity.this);

        mEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (validateFieldEmail(mTextInputEmail, "Invalid email id")) {
                        if (!mEmail.getText().toString().equals(VerifiedEmailID)) {
                            checkEmailService(mEmail.getText().toString().trim());
                        }
                    }
                }
            }
        });

        mPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (!mPhone.getText().toString().equals(mMobileNumber)) {
                        mMobileVerifiedStatus = false;
                        mMobileCheckView.setVisibility(View.GONE);
                        mVerifyMobile.setVisibility(View.VISIBLE);
                    } else {
                        if (mMobileVerifiedStatus) {
                            mMobileCheckView.setVisibility(View.VISIBLE);
                            mMobileCheckView.check();
                            mVerifyMobile.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });


    }

    private void validate() {
        if (validateFieldEmpty(mTextInputName, "Enter username")) {
            if (validateFieldEmpty(mTextInputPhone, "Enter your mobile number")) {
                if (validateFieldMobile(mTextInputPhone, "Invalid mobile number")) {
                    if (validateFieldEmpty(mTextInputEmail, "Enter your email address")) {
                        if (validateFieldEmail(mTextInputEmail, "Invalid email id")) {
                            if (validateFieldEmpty(mTextInputPassword, "Enter your password")) {
                                if (validateFieldEmpty(mTextInputCpassword, "Enter confirm password")) {
                                    if (checkPassword(mTextInputCpassword, "Password doesn't match")) {
                                        if (MobileVerified) {
                                            submitRegister();
                                        } else {
                                            Toast.makeText(SignupActivity.this, "Mobile not verified", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkPassword(TextInputLayout mTextInputCpassword, String msg) {
        if (mSignupPass.equals(mSignupCpass)) {
            mTextInputCpassword.setError(null);
            return true;
        } else {
            mTextInputCpassword.setError(msg);
            return false;
        }
    }

    public boolean validateFieldEmpty(TextInputLayout field, String msg) {
        EditText child = (EditText) field.getEditText();
        String text = child.getText().toString().trim();
        if (child.getText().toString().isEmpty()) {
            field.setError(msg);
            return false;
        } else {
            field.setError(null);
            return true;
        }
    }

    public boolean validateFieldMobile(TextInputLayout field, String msg) {
        EditText child = (EditText) field.getEditText();
        String text = child.getText().toString().trim();
        mMobileNumber = text;
        int i = text.length();
        if (i >= 6 && i <= 14) {
            field.setError(null);
            return true;
        } else {
            field.setError(msg);
            return false;
        }
    }

    public boolean validateFieldEmail(TextInputLayout field, String msg) {
        EditText child = (EditText) field.getEditText();
        String text = child.getText().toString().trim();
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches())) {
            field.setError(msg);
            mEmailCheckView.setVisibility(View.GONE);
            return false;
        } else {
            field.setError(null);
        }
        return true;
    }

    public void processValidateNumberOtp(ValidatMobileResult mobileResult) {
        final String checkNo = mPhone.getText().toString().trim();
        Intent intent = new Intent(getApplicationContext(), VerifyOtp.class);
        intent.putExtra("needReturn", true);
        intent.putExtra("mobile", checkNo);
        intent.putExtra("tempid", mobileResult.getData().getTempid());
        startActivityForResult(intent, OTP_REQ_CODE);
    }

    public void checkMobileDuplication(String mobile) {

        loadingScreen.showLoading();
        new LoginReqsVly().checkMobileDuplication(SignupActivity.this, mobile, new LoginReqsVly.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                ValidatMobileResult mobileResult = new Gson().fromJson(result, ValidatMobileResult.class);
                if (mobileResult.getStatus().equalsIgnoreCase(AppConstant.SUCCESS)) {
                    processValidateNumberOtp(mobileResult);
                } else {
                    mMobileVerifiedStatus = false;
                    mMobileCheckView.setVisibility(View.GONE);
                    mVerifyMobile.setVisibility(View.VISIBLE);
                    mTextInputPhone.setError(mobileResult.getMessage());
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

    public void checkEmailService(String email) {

        loadingScreen.showLoading();
        new LoginReqsVly().checkEmailDuplication(SignupActivity.this, email, new LoginReqsVly.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                ValidateEmailResult emailResult = new Gson().fromJson(result, ValidateEmailResult.class);
                if (emailResult.getStatus().equalsIgnoreCase(AppConstant.SUCCESS)) {
                    VerifiedEmailID = email;
                    mEmailValidStatus = true;
                    mTextInputEmail.setError(null);
                    mEmailCheckView.setVisibility(View.VISIBLE);
                    mEmailCheckView.check();
                } else {
                    mEmailValidStatus = false;
                    mEmailCheckView.setVisibility(View.GONE);
                    mTextInputEmail.setError(emailResult.getMessage());
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

    public void submitRegister() {

        RegisterArgs registerArgs = new RegisterArgs();
        registerArgs.nickname = mUsername;
        registerArgs.password = mSignupPass;
        registerArgs.mobile = mPhoneNo;
        registerArgs.email = mEmailId;
        registerArgs.country = "IN";

        loadingScreen.showLoading();
        new LoginReqsVly().register(SignupActivity.this, registerArgs, new LoginReqsVly.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                RegisterResult emailResult = new Gson().fromJson(result, RegisterResult.class);
                if (emailResult.getStatus().equalsIgnoreCase(AppConstant.SUCCESS)) {
                    Toast.makeText(SignupActivity.this, "Welcome " + mUsername, Toast.LENGTH_SHORT).show();
                    SharedPrefHelper.getInstance(SignupActivity.this).setIsLogin(true);

                    UserDetailsArg userDetailsArg = new UserDetailsArg();
                    userDetailsArg.userFullName = mUsername;
                    userDetailsArg.userEmail = mEmailId;
                    userDetailsArg.userMobile = mPhoneNo;

                    UserPrefHelper.getInstance(SignupActivity.this).setUserData(userDetailsArg);

                    UserConstantResult manager = new UserConstantResult();
                    manager.TOKEN = emailResult.getTokenId();
                    manager.USER_ID = emailResult.getDataRegister().getUserDetails().getUid();

                    AppConstant.TOKEN = emailResult.getTokenId();
                    AppConstant.USER_ID = emailResult.getDataRegister().getUserDetails().getUid();

                    UserPrefHelper.getInstance(SignupActivity.this).setUserConst(manager);
                    UserPrefHelper.getInstance(SignupActivity.this).setUserData(userDetailsArg);

                    startActivity(new Intent(SignupActivity.this, HomeAct.class));

                } else {
                    new AlertMsgDialog(SignupActivity.this, AlertMsgDialog.DIA_INFO, "Failed", "" + emailResult.getMessage(), "ok", new AlertMsgDialog.ActionListner() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == OTP_REQ_CODE) {
            try {
                MobileVerified = data.getBooleanExtra("numberVerified", false);
                if (MobileVerified) {
                    mTextInputPhone.setEnabled(false);
                    mTextInputPhone.setError(null);
                    mMobileVerifiedStatus = true;
                    mMobileCheckView.setVisibility(View.VISIBLE);
                    mMobileCheckView.check();
                    mVerifyMobile.setVisibility(View.GONE);
                }
            } catch (Exception e) {

            }

        }
    }

}
