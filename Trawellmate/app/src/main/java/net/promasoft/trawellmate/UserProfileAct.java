package net.promasoft.trawellmate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.dsgn.CircleImageView;
import net.promasoft.trawellmate.util.AlineActivityHelper;

public class UserProfileAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        new AlineActivityHelper(UserProfileAct.this, false);

        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });
        Button profileSave = findViewById(R.id.ID_prf_save);
        profileSave.setOnClickListener(view -> {
            finish();
        });
        initViews();
    }

    private void initViews() {
        CircleImageView userImg = findViewById(R.id.ID_profile_img);
        TextView userNickName = findViewById(R.id.ID_profile_nickname);
        EditText userFirstName = findViewById(R.id.ID_profile_fname);
        EditText userLastName = findViewById(R.id.ID_profile_lname);
        EditText userMobile = findViewById(R.id.ID_profile_mobile);
        EditText userEmail = findViewById(R.id.ID_profile_email);

        if (SharedPrefHelper.getInstance(UserProfileAct.this).getIsLogin()) {
            UserDetailsArg userData = UserPrefHelper.getInstance(UserProfileAct.this).getUserData();
            if (userData != null) {
                userNickName.setText("" + userData.userFullName);
                userFirstName.setText("" + userData.userFirstName);
                userLastName.setText("" + userData.userLastName);
                userEmail.setText("" + userData.userEmail);
                userMobile.setText("" + userData.userMobile);
                if (!userData.userPhotUri.isEmpty()) {
                    Picasso.with(UserProfileAct.this).load(userData.userPhotUri).placeholder(R.drawable.ic_user_icon).into(userImg);
                }
            }

        }

    }

}
