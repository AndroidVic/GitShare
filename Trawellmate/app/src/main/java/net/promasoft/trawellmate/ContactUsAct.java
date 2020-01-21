package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class ContactUsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        new AlineActivityHelper(ContactUsAct.this, true);

    }
}
