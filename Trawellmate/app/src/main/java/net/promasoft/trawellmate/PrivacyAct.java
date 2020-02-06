package net.promasoft.trawellmate;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class PrivacyAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        new AlineActivityHelper(PrivacyAct.this, true);

        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}
