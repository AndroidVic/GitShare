package net.promasoft.trawellmate.frg;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.dsgn.CircleImageView;
import net.promasoft.trawellmate.util.DrawerFrgListner;

public class FrgUserProfile extends Fragment {

    private Activity mAction;
    private View view;
    private DrawerFrgListner mDrawerListner;

    public FrgUserProfile(Activity activity) {
        // Required empty public constructor
        this.mAction = activity;
    }

    public static FrgUserProfile newInstance(Activity activity) {
        return new FrgUserProfile(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_user_profile, container, false);
        ImageView menuBar = view.findViewById(R.id.ID_arw_bck);
        menuBar.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_menu));
        menuBar.setOnClickListener(view1 -> {
            mDrawerListner.onDrawerClicked();
        });
        initViews();
        return view;
    }

    private void initViews() {
        CircleImageView userImg = view.findViewById(R.id.ID_profile_img);
        TextView userNickName = view.findViewById(R.id.ID_profile_nickname);
        EditText userFirstName = view.findViewById(R.id.ID_profile_fname);
        EditText userLastName = view.findViewById(R.id.ID_profile_lname);
        EditText userMobile = view.findViewById(R.id.ID_profile_mobile);
        EditText userEmail = view.findViewById(R.id.ID_profile_email);

        if (SharedPrefHelper.getInstance(getActivity()).getIsLogin()) {
            UserDetailsArg userData = UserPrefHelper.getInstance(getActivity()).getUserData();
            if (userData != null) {
                userNickName.setText("" + userData.userFullName);
                userFirstName.setText("" + userData.userFirstName);
                userLastName.setText("" + userData.userLastName);
                userEmail.setText("" + userData.userEmail);
                userMobile.setText("" + userData.userMobile);
                if (!userData.userPhotUri.isEmpty()) {
                    Picasso.with(getActivity()).load(userData.userPhotUri).placeholder(R.drawable.ic_user_img_default).into(userImg);
                }
            }

        }

    }

    public void setDrawerListner(DrawerFrgListner drawerListner) {
        this.mDrawerListner = drawerListner;
    }


}
