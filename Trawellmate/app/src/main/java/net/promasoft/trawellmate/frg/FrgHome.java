package net.promasoft.trawellmate.frg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;

import net.promasoft.trawellmate.DetailsAct;
import net.promasoft.trawellmate.NotificationAct;
import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.util.AnimHelper;
import net.promasoft.trawellmate.util.DialogLogin;

public class FrgHome extends Fragment {

    private Activity mActivity;
    private View view;
    private DrawerCloseListner mDrawerListner;
    private Button loginBt;

    public FrgHome(Activity activity) {
        this.mActivity = activity;
        // Required empty public constructor
    }

    public static FrgHome newInstance(Activity activity) {
        return new FrgHome(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_page, container, false);

        initLayoutView(view);

        return view;
    }

    private ImageView searchLay;
    public boolean searchBgVisible = false;

    private void initLayoutView(View mView) {

        ImageView menuSlider = mView.findViewById(R.id.ID_menu_iv);
        menuSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDrawerListner.onDrawerClicked();
            }
        });

        loginBt = mView.findViewById(R.id.ID_hm_login);
        AnimHelper.slideDown(loginBt);

        loginBt.setOnClickListener(view -> {
            loginBt.clearAnimation();
            loginBt.setVisibility(View.GONE);
            new DialogLogin(mActivity).showPage();
        });

        searchLay = mView.findViewById(R.id.ID_search_bg_lay);

        AppBarLayout appBarLayout = mView.findViewById(R.id.id_appBar_main);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    //  Collapsed
                    if (searchLay.getVisibility() == View.GONE) {
                        searchBgVisible = true;

                        if (mDrawerListner != null) {
                            mDrawerListner.onCoordClose();
                        }
                        if (loginBt.getVisibility() == View.VISIBLE) {
                            AnimHelper.slideUp(loginBt);
                        }
                        searchLay.setVisibility(View.VISIBLE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.fade_in_anim));
                    }

                } else if (verticalOffset == 0) {
                    // Expanded
                    if (searchLay.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;
                        searchLay.setVisibility(View.GONE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.fade_out_anim));
                    }
                } else if (searchBgVisible) {
                    //  Collapsed
                    if (searchLay.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;

                        if (mDrawerListner != null) {
                            mDrawerListner.onCoordOpened();
                        }
                        if (loginBt.getVisibility() == View.VISIBLE) {
                            AnimHelper.slideDown(loginBt);
                        }
                        searchLay.setVisibility(View.GONE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.fade_out_anim));

                    }

                }

            }
        });


        ImageView notification = mView.findViewById(R.id.ID_hm_notification);
        notification.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, NotificationAct.class));
        });

        TextView viewPackages = mView.findViewById(R.id.ID_view_packages);
        viewPackages.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });

        CardView viewPackages1 = mView.findViewById(R.id.ID_hm_pack1);
        viewPackages1.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages2 = mView.findViewById(R.id.ID_hm_pack2);
        viewPackages2.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages3 = mView.findViewById(R.id.ID_hm_pack3);
        viewPackages3.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages4 = mView.findViewById(R.id.ID_hm_pack4);
        viewPackages4.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages5 = mView.findViewById(R.id.ID_hm_pack5);
        viewPackages5.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
        CardView viewPackages6 = mView.findViewById(R.id.ID_hm_pack6);
        viewPackages6.setOnClickListener(view -> {
            startActivity(new Intent(mActivity, DetailsAct.class));
        });
    }

    public void setDrawerListner(DrawerCloseListner drawerListner) {
        this.mDrawerListner = drawerListner;
    }

    public interface DrawerCloseListner {

        void onCoordClose();

        void onCoordOpened();

        void onDrawerClicked();
    }

}
