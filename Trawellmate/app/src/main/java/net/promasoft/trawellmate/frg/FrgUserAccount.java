package net.promasoft.trawellmate.frg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import net.promasoft.trawellmate.AboutUsAct;
import net.promasoft.trawellmate.ContactUsAct;
import net.promasoft.trawellmate.FaqAct;
import net.promasoft.trawellmate.NotificationAct;
import net.promasoft.trawellmate.PrivacyAct;
import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.StartPageAct;
import net.promasoft.trawellmate.UserPassChngeAct;
import net.promasoft.trawellmate.UserProfileAct;
import net.promasoft.trawellmate.YourBookings;
import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.dsgn.CircleImageView;
import net.promasoft.trawellmate.util.AlertMsgDialog;
import net.promasoft.trawellmate.util.DrawerFrgListner;

public class FrgUserAccount extends Fragment {

    private Activity mActivity;
    private View view;
    private DrawerFrgListner mDrawerListner;

    public FrgUserAccount(Activity activity) {
        // Required empty public constructor
        this.mActivity = activity;
    }

    public static FrgUserAccount newInstance(Activity activity) {
        return new FrgUserAccount(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_user_account, container, false);

        RelativeLayout loginLay = view.findViewById(R.id.ID_acc_login_layout);
        RelativeLayout userLay = view.findViewById(R.id.ID_acc_user_layout);

        if (SharedPrefHelper.getInstance(getActivity()).getIsLogin()) {
            userLay.setVisibility(View.VISIBLE);
            loginLay.setVisibility(View.GONE);
            UserDetailsArg userData = UserPrefHelper.getInstance(getActivity()).getUserData();
            if (userData != null) {
                TextView userName = view.findViewById(R.id.ID_acc_user_name);
                userName.setText("" + userData.userFullName);
                if (!userData.userPhotUri.isEmpty()) {
                    CircleImageView userPhoto = view.findViewById(R.id.ID_acc_user_photo);
                    Picasso.with(getActivity()).load(userData.userPhotUri).placeholder(R.drawable.ic_user_img_default).into(userPhoto);
                }
            }

        } else {
            userLay.setVisibility(View.GONE);
            loginLay.setVisibility(View.VISIBLE);
        }


        initView();

        return view;
    }

    private void initView() {

        LinearLayout iconBaseCont = view.findViewById(R.id.ID_nav_side_container);

        View.OnClickListener onIconsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                view.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.pulse_short));
                switch (view.getId()) {
                    case R.id.ID_nav_account:
                        if (SharedPrefHelper.getInstance(getActivity()).getIsLogin()) {
                            startActivity(new Intent(mActivity, UserProfileAct.class));
                        } else {
                            startActivity(new Intent(getActivity(), StartPageAct.class));
                            getActivity().overridePendingTransition(R.anim.slide_from_down, R.anim.fade_out_anim);
                        }
                        break;
                    case R.id.ID_nav_booking:
                        if (SharedPrefHelper.getInstance(getActivity()).getIsLogin()) {
                            startActivity(new Intent(mActivity, YourBookings.class));
                        } else {
                            startActivity(new Intent(getActivity(), StartPageAct.class));
                            getActivity().overridePendingTransition(R.anim.slide_from_down, R.anim.fade_out_anim);
                        }
                        break;
                    case R.id.ID_nav_change_pass:
                        startActivity(new Intent(mActivity, UserPassChngeAct.class));
                        break;
                    case R.id.ID_nav_transaction:
                        if (SharedPrefHelper.getInstance(getActivity()).getIsLogin()) {

                        } else {
                            startActivity(new Intent(getActivity(), StartPageAct.class));
                            getActivity().overridePendingTransition(R.anim.slide_from_down, R.anim.fade_out_anim);
                        }
                        break;
                    case R.id.ID_nav_claim_refund:
                        if (SharedPrefHelper.getInstance(getActivity()).getIsLogin()) {

                        } else {
                            startActivity(new Intent(getActivity(), StartPageAct.class));
                            getActivity().overridePendingTransition(R.anim.slide_from_down, R.anim.fade_out_anim);
                        }
                        break;
                    case R.id.ID_nav_notification:
                        startActivity(new Intent(mActivity, NotificationAct.class));

                        break;
                    case R.id.ID_nav_privacy:
                        startActivity(new Intent(mActivity, PrivacyAct.class));

                        break;
                    case R.id.ID_nav_contact_us:
                        startActivity(new Intent(mActivity, ContactUsAct.class));

                        break;
                    case R.id.ID_nav_faq:
                        startActivity(new Intent(mActivity, FaqAct.class));

                        break;
                    case R.id.ID_nav_aboutus:
                        startActivity(new Intent(mActivity, AboutUsAct.class));

                        break;
                    case R.id.ID_nav_logout:
                        new AlertMsgDialog(mActivity, AlertMsgDialog.DIA_LOGOUT, "Logout", "Are you sure to exit", "exit", new AlertMsgDialog.ActionListner() {
                            @Override
                            public void onPositive() {
                                SharedPrefHelper.getInstance(mActivity).setIsLogin(false);
                                getActivity().finish();
                            }

                            @Override
                            public void onNegative() {

                            }
                        }).showPage();
                        break;
                }
            }
        };

        boolean isLogin = SharedPrefHelper.getInstance(getActivity()).getIsLogin();
        for (int i = 0; i < iconBaseCont.getChildCount(); i++) {
            try {
                LinearLayout child = (LinearLayout) iconBaseCont.getChildAt(i);
                child.setOnClickListener(onIconsClickListener);
            } catch (Exception e) {
            }
        }

        if (!isLogin) {
            LinearLayout child1 = iconBaseCont.findViewById(R.id.ID_nav_change_pass);
            child1.setVisibility(View.GONE);
            LinearLayout child2 = iconBaseCont.findViewById(R.id.ID_nav_transaction);
            child2.setVisibility(View.GONE);
            LinearLayout child3 = iconBaseCont.findViewById(R.id.ID_nav_claim_refund);
            child3.setVisibility(View.GONE);
            LinearLayout child4 = iconBaseCont.findViewById(R.id.ID_nav_logout);
            child4.setVisibility(View.GONE);
            LinearLayout child5 = iconBaseCont.findViewById(R.id.ID_nav_notification);
            child5.setVisibility(View.GONE);
        }

    }


}
