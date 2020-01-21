package net.promasoft.trawellmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class PrivacyAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        new AlineActivityHelper(PrivacyAct.this, true);

    }
}
