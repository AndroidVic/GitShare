package net.promasoft.trawellmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class UserAccountAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        LinearLayout iconBaseCont = findViewById(R.id.ID_acc_icon_cont);

        View.OnClickListener onIconsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(UserAccountAct.this, R.anim.pulse_short));
                switch (view.getId()) {
                    case R.id.ID_acc_status:

                        break;
                    case R.id.ID_acc_booking:
                        startActivity(new Intent(UserAccountAct.this, YourBookings.class));


                        break;
                    case R.id.ID_acc_user:
                        startActivity(new Intent(UserAccountAct.this, UserProfileAct.class));
                        break;
                    case R.id.ID_acc_transaction:

                        break;
                    case R.id.ID_acc_pass_change:
                        startActivity(new Intent(UserAccountAct.this, UserPassChngeAct.class));

                        break;
                    case R.id.ID_acc_claim_refund:

                        break;
                    case R.id.ID_acc_notification:
                        startActivity(new Intent(UserAccountAct.this, NotificationAct.class));

                        break;
                    case R.id.ID_acc_prrivacy_policy:
                        startActivity(new Intent(UserAccountAct.this, PrivacyAct.class));

                        break;
                    case R.id.ID_acc_contact_us:
                        startActivity(new Intent(UserAccountAct.this, ContactUsAct.class));

                        break;
                    case R.id.ID_acc_faq:
                        startActivity(new Intent(UserAccountAct.this, FaqAct.class));
                        break;
                    case R.id.ID_acc_about_us:

                        break;
                }
            }
        };

        for (int i = 0; i < iconBaseCont.getChildCount(); i++) {
            LinearLayout child = (LinearLayout) iconBaseCont.getChildAt(i);
            child.setOnClickListener(onIconsClickListener);

        }
    }
}
