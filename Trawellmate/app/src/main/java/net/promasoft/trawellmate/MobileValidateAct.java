package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import net.promasoft.trawellmate.args.ValidatMobileResult;
import net.promasoft.trawellmate.ntwk.LoginReqsVly;
import net.promasoft.trawellmate.util.AppConstant;
import net.promasoft.trawellmate.util.DelayHelper;
import net.promasoft.trawellmate.util.LoadingScreen;
import net.promasoft.trawellmate.util.ToastHelper;

public class MobileValidateAct extends AppCompatActivity {

    private static final int PHONE_NUMBER_RC = 00003;
    private GoogleApiClient googleApiClient;
    private EditText numberEt;
    private TextView nameEt;
    private String mNumber = "";
    private LoadingScreen loadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_validate);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        DelayHelper.delayStartWith(500, new DelayHelper.DelayListner() {
            @Override
            public void onStartTask() {
                requestPhoneNumber();

            }
        });
        loadingScreen = new LoadingScreen(MobileValidateAct.this);

        nameEt = findViewById(R.id.ID_mobile_valid_name);
        String name = getIntent().getStringExtra("name");
        nameEt.setText(name == null ? "" : name);

        numberEt = findViewById(R.id.ID_mobile_valid_number);
        Button verify = findViewById(R.id.ID_mobile_valid_bt);
        verify.setOnClickListener(view -> {
//            finish();
            if (checkIsMobile(numberEt.getText().toString())) {
                checkMobileDuplication(numberEt.getText().toString());
            }

        });

        ImageView back = findViewById(R.id.ID_arw_bck);
        back.setOnClickListener(view -> {
            finish();
        });

        AppConstant.MOBILE_VALID_ACT = MobileValidateAct.this;
    }

    private boolean checkIsMobile(String mobile) {
        if (mobile.isEmpty()) {
            new ToastHelper(MobileValidateAct.this, "Enter mobile number").setDuration(2000).show();
            return false;
        }
        if (mobile.length() > 6 && mobile.length() < 14) {
            mNumber = mobile;
            return true;
        } else {
            new ToastHelper(MobileValidateAct.this, "Enter a valid mobile number").setDuration(2000).show();
            return false;
        }
    }

    public void requestPhoneNumber() {
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(), PHONE_NUMBER_RC, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            Log.e("MobileValid", "Could not start hint picker Intent", e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_NUMBER_RC) {
            if (resultCode == RESULT_OK) {
                Credential cred = data.getParcelableExtra(Credential.EXTRA_KEY);
//                Toast.makeText(this, "num: " + cred.getId(), Toast.LENGTH_SHORT).show();
                numberEt.setText("" + cred.getId());
                numberEt.setSelection(cred.getId().length());
            }
        }
    }


    public void checkMobileDuplication(String mobile) {

        loadingScreen.showLoading();
        new LoginReqsVly().checkMobileDuplication(MobileValidateAct.this, mobile, new LoginReqsVly.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                ValidatMobileResult mobileResult = new Gson().fromJson(result, ValidatMobileResult.class);
                if (mobileResult.getStatus().equalsIgnoreCase(AppConstant.SUCCESS)) {
                    processValidateNumberOtp(mobileResult);
                    numberEt.setError(null);
                } else {
                    numberEt.setError(mobileResult.getMessage());
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

    private void processValidateNumberOtp(ValidatMobileResult mobileResult) {
        Intent intent = new Intent(MobileValidateAct.this, VerifyOtp.class);
        intent.putExtra("mobile", mNumber);
        try {
            intent.putExtra("tempid", mobileResult.getData().getTempid());
        } catch (Exception e) {
            Toast.makeText(this, "Data Error, please try again", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }

}
