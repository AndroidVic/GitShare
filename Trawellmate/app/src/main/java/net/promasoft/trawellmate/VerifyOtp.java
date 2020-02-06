package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;

public class VerifyOtp extends AppCompatActivity implements View.OnClickListener,
        OnOtpCompletionListener {
    private Button validateButton;
    private OtpView otpView;
    private TextView cancelButton;
    private String userNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_otp);
        initializeUi();
        setListeners();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.validate_btn) {
//            Toast.makeText(this, otpView.getText(), Toast.LENGTH_SHORT).show();
            if (otpView.getText().toString().equals("1234")) {

                UserDetailsArg userData = UserPrefHelper.getInstance(VerifyOtp.this).getUserData();
                if (userData != null) {
                    userData.userMobile = userNumber;
                    UserPrefHelper.getInstance(VerifyOtp.this).setUserData(userData);
                }
                SharedPrefHelper.getInstance(VerifyOtp.this).setIsLogin(true);
                startActivity(new Intent(VerifyOtp.this, HomeAct.class));
                finish();
            }
        }
    }

    @Override
    public void onOtpCompleted(String otp) {

    }

    private void initializeUi() {

        TextView number = findViewById(R.id.ID_ver_otp_number);
        userNumber = getIntent().getStringExtra("number");
        number.setText("" + userNumber);

        otpView = findViewById(R.id.otp_view);
        cancelButton = findViewById(R.id.cancel_btn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        validateButton = findViewById(R.id.validate_btn);
        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {

               /* // do Stuff
                Toast.makeText(VerifyOtp.this, "otp :"+otp, Toast.LENGTH_SHORT).show();

                Log.d("onOtpCompleted=>", otp);*/
            }


        });
    }

    private void setListeners() {
        validateButton.setOnClickListener(this);
//        otpView.setOtpCompletionListener(this);
    }

}
