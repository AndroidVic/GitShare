package net.promasoft.trawellmate;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Button profileSave = findViewById(R.id.ID_prf_save);
        profileSave.setOnClickListener(view -> {
            finish();
        });

    }
}
