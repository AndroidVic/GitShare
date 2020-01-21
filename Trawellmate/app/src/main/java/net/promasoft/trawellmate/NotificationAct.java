package net.promasoft.trawellmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class NotificationAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notification);
        new AlineActivityHelper(NotificationAct.this, true);

    }
}