package net.promasoft.trawellmate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class StartPageAct extends AppCompatActivity {
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_travel);
        new AlineActivityHelper(StartPageAct.this, false);


    }
}
