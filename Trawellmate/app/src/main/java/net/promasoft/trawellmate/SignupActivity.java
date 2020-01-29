package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputLayout;

import net.promasoft.trawellmate.ntwk.LoginReqsVly;
import net.promasoft.trawellmate.util.AppSnackAlerts;
import net.promasoft.trawellmate.util.ToastHelper;
import net.promasoft.trawellmate.util.VerifyOtpDialog;

public class SignupActivity extends AppCompatActivity {

    TextView mVerifyMobile;
    VerifyOtpDialog verifyOtpDialog;
    String msg = "Invalid number";
    LinearLayout layout;
    EditText mPhone;
    TextInputLayout mTextInputPhone;
     EditText mName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        layout = findViewById(R.id.signup_parent);

        mName = findViewById(R.id.ID_signup_name);
        mVerifyMobile = findViewById(R.id.ID_mobile_verify);
        mPhone = findViewById(R.id.ID_signup_phone);
        mTextInputPhone = findViewById(R.id.ID_layoutPhoneNo);


        mVerifyMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String checkNo = mPhone.getText().toString().trim();
                if (isPhoneNumberValid(checkNo)) {
                    mTextInputPhone.setError(null);
                    new LoginReqsVly().checkMobileDuplication(SignupActivity.this, checkNo, new LoginReqsVly.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(SignupActivity.this, "" + result, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onRequestError(VolleyError errorMessage) {

                        }

                        @Override
                        public void onIntrnError(String errorMessage) {
                            Toast.makeText(SignupActivity.this, "" + errorMessage, Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    new ToastHelper(SignupActivity.this, "Invalid mobile number").setDuration(2000).setBgColor(R.color.colorPrimary).show();
                    mTextInputPhone.setError("Invalid mobile number");
                }
            }
        });
    }

    private boolean isPhoneNumberValid(String checkNo) {
        if (checkNo.length() == 10) {
            return true;
        }
        return false;
    }

    public void processValidateNumberOtp() {
        final String checkNo = mPhone.getText().toString().trim();
        if (isPhoneNumberValid(checkNo)) {
            Intent intent = new Intent(getApplicationContext(), VerifyOtp.class);
            intent.putExtra("mobile", checkNo);
            startActivity(intent);
        } else {
            AppSnackAlerts.showMessage(layout, msg);
        }
    }

}
