package net.promasoft.trawellmate;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class UserPassChngeAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset_layout);
        new AlineActivityHelper(UserPassChngeAct.this, false);

        Button profileSave = findViewById(R.id.ID_pass_chng_bt);
        profileSave.setOnClickListener(view -> {
            finish();
        });

    }
}
