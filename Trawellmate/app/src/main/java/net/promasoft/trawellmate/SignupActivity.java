package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import net.promasoft.trawellmate.util.AppAlerts;
import net.promasoft.trawellmate.util.VerifyOtpDialog;

public class SignupActivity extends AppCompatActivity{

    TextView verify;
    VerifyOtpDialog verifyOtpDialog;
    String msg = "Invalid number";
    LinearLayout layout;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        verify = findViewById(R.id.tv_verify);
        editText = findViewById(R.id.ph);

        layout = findViewById(R.id.signup_parent);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String checkNo = editText.getText().toString().trim();
                if (isPhoneNumberValid(checkNo)) {
                    verifyOtpDialog = new VerifyOtpDialog(SignupActivity.this);
                    verifyOtpDialog.showPage();
                } else {
                    AppAlerts.showMessage(layout, msg);
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


}
