package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import net.promasoft.trawellmate.dsgn.CustomTextLink;
import net.promasoft.trawellmate.util.AlineActivityHelper;

public class ContactUsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        new AlineActivityHelper(ContactUsAct.this, true);

        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });

        CustomTextLink callNumber = findViewById(R.id.ID_cntct_number);
        callNumber.setOnClickListener(view -> {
            Intent callintent = new Intent(Intent.ACTION_DIAL);
            callintent.setData(Uri.parse("tel:04772967111"));
            startActivity(callintent);
        });
        CustomTextLink callEmail = findViewById(R.id.ID_cntct_email);
        callEmail.setOnClickListener(view -> {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_APP_EMAIL);
//            startActivity(intent);
//            startActivity(Intent.createChooser(intent, "happiness@trawellmate.com"));


            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "happiness@trawellmate.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mail Regarding ");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
//emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

            startActivity(Intent.createChooser(emailIntent, "Chooser"));
        });

    }
}
