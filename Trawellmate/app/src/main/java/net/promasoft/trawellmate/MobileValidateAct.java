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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;

import net.promasoft.trawellmate.util.DelayHelper;
import net.promasoft.trawellmate.util.ToastHelper;

public class MobileValidateAct extends AppCompatActivity {

    private static final int PHONE_NUMBER_RC = 00003;
    private GoogleApiClient googleApiClient;
    private EditText numberEt;
    private TextView nameEt;
    private String mNumber = "";

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

        nameEt = findViewById(R.id.ID_mobile_valid_name);
        String name = getIntent().getStringExtra("name");
        nameEt.setText(name == null ? "" : name);

        numberEt = findViewById(R.id.ID_mobile_valid_number);
        Button verify = findViewById(R.id.ID_mobile_valid_bt);
        verify.setOnClickListener(view -> {
//            finish();
            if (checkIsMobile(numberEt.getText().toString())) {
                Intent intent = new Intent(MobileValidateAct.this, VerifyOtp.class);
                intent.putExtra("number", mNumber);
                startActivity(intent);
            }

        });

        ImageView back = findViewById(R.id.ID_arw_bck);
        back.setOnClickListener(view -> {
            finish();
        });

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

}
