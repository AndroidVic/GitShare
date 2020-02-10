package net.promasoft.trawellmate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.args.ForgotPassResult;
import net.promasoft.trawellmate.args.ValidateEmailResult;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.ntwk.LoginReqsVly;
import net.promasoft.trawellmate.util.AlertMsgDialog;
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.AppConstant;
import net.promasoft.trawellmate.util.LoadingScreen;

public class ForgotPAsswordAct extends AppCompatActivity {

    private LoadingScreen loadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        new AlineActivityHelper(ForgotPAsswordAct.this, false);


        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });

        EditText emailEt = findViewById(R.id.ID_frgt_email);

        Button confirm = findViewById(R.id.ID_frgt_email_bt);
        confirm.setOnClickListener(view -> {
            forgotPass(emailEt.getText().toString());
        });

        loadingScreen = new LoadingScreen(ForgotPAsswordAct.this);

    }

    private void forgotPass(String email) {
        loadingScreen.showLoading();
        new LoginReqsVly().forgotPaswword(ForgotPAsswordAct.this, email, new LoginReqsVly.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                ForgotPassResult emailResult = new Gson().fromJson(result, ForgotPassResult.class);

                new AlertMsgDialog(ForgotPAsswordAct.this, AlertMsgDialog.DIA_INFO, "" + emailResult.getStatus(), "" + emailResult.getMessage(), "exit", new AlertMsgDialog.ActionListner() {
                    @Override
                    public void onPositive() {
                        if (emailResult.getStatus().equalsIgnoreCase(AppConstant.SUCCESS)) {
                            finish();
                        }
                    }

                    @Override
                    public void onNegative() {

                    }
                }).showPage();


                loadingScreen.cancelLoading();
            }

            @Override
            public void onRequestError(VolleyError errorMessage) {
                loadingScreen.cancelLoading();
                Toast.makeText(ForgotPAsswordAct.this, "Error Occured", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onIntrnError(String errorMessage) {
                loadingScreen.cancelLoading();
                Toast.makeText(ForgotPAsswordAct.this, "" + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
