package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class WriteReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        new AlineActivityHelper(WriteReview.this, false);

        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });
        Button submitReview = findViewById(R.id.ID_submit_review);
        submitReview.setOnClickListener(view -> {
            finish();
        });
    }
}
