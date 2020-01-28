package net.promasoft.trawellmate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class UserPassChngeAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pass_chng);
        new AlineActivityHelper(UserPassChngeAct.this, false);

        Button profileSave = findViewById(R.id.ID_pass_chng_bt);
        profileSave.setOnClickListener(view -> {
            finish();
        });


        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });

    }
}
