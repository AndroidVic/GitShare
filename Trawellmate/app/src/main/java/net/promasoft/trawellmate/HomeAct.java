package net.promasoft.trawellmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import net.promasoft.trawellmate.dsgn.CustomTextLink;
import net.promasoft.trawellmate.frg.FrgBooking;
import net.promasoft.trawellmate.frg.FrgHome;
import net.promasoft.trawellmate.frg.FrgSaved;
import net.promasoft.trawellmate.frg.FrgUserProfile;
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.AnimHelper;
import net.promasoft.trawellmate.util.DialogLogin;

public class HomeAct extends AppCompatActivity {

    private BottomNavigationView bottomNavigationMenu;

    private DrawerLayout mainDrawerLay;
    private NavigationView mNavigationView;
    private LinearLayout bottom_menu_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new AlineActivityHelper(HomeAct.this, false);

        initDrawer();
        bottom_menu_lay = findViewById(R.id.ID_bottom_menu_lay);
        FrgHome frgHome = FrgHome.newInstance(HomeAct.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgHome).commit();
        FrgBooking frgBooking = FrgBooking.newInstance(HomeAct.this);
        FrgSaved frgSaved = FrgSaved.newInstance(HomeAct.this);
        FrgUserProfile frgUserProfile = FrgUserProfile.newInstance(HomeAct.this);

        frgHome.setDrawerListner(new FrgHome.DrawerCloseListner() {
            @Override
            public void onCoordClose() {
                bottom_menu_lay.setVisibility(View.VISIBLE);
//                bottom_menu_lay.startAnimation(AnimationUtils.loadAnimation(HomeAct.this, R.anim.slide_up));
                AnimHelper.slideUp(bottom_menu_lay);

            }

            @Override
            public void onCoordOpened() {
//                bottom_menu_lay.setVisibility(View.GONE);
//                bottom_menu_lay.startAnimation(AnimationUtils.loadAnimation(HomeAct.this, R.anim.slide_down));
                AnimHelper.slideDown(bottom_menu_lay);

            }

            @Override
            public void onDrawerClicked() {

                if (!mainDrawerLay.isDrawerOpen(GravityCompat.START)) {
                    mainDrawerLay.openDrawer(GravityCompat.START);
                }
            }
        });

        bottomNavigationMenu = findViewById(R.id.ID_bottom_navigation);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.ID_nav_account) {
                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgUserProfile).commit();
            }
            if (id == R.id.ID_nav_booking) {
                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgBooking).commit();
            }
            if (id == R.id.ID_nav_explore) {
                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgHome).commit();
            }
            if (id == R.id.ID_nav_saved) {
                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgSaved).commit();

            }
            return true;
        });


    }


    private void initDrawer() {
        mainDrawerLay = findViewById(R.id.drawer_layout);


        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = mNavigationView.getHeaderView(0);
//        hdrUserName = headerView.findViewById(R.id.ID_nav_User_Name);
//        hdrUserNumber = headerView.findViewById(R.id.ID_nav_User_Number);
//        hdrUserImage = headerView.findViewById(R.id.ID_Nav_User_ImageView);


        LinearLayout iconBaseCont = mNavigationView.findViewById(R.id.ID_nav_side_container);

        View.OnClickListener onIconsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(HomeAct.this, R.anim.pulse_short));
                switch (view.getId()) {
                    case R.id.ID_nav_account:
                        startActivity(new Intent(HomeAct.this, UserProfileAct.class));
                        break;
                    case R.id.ID_nav_booking:
                        startActivity(new Intent(HomeAct.this, YourBookings.class));

                        break;
                    case R.id.ID_nav_change_pass:
                        startActivity(new Intent(HomeAct.this, UserPassChngeAct.class));
                        break;
                    case R.id.ID_nav_transaction:

                        break;
                    case R.id.ID_nav_claim_refund:

                        break;
                    case R.id.ID_nav_notification:
                        startActivity(new Intent(HomeAct.this, NotificationAct.class));
                        break;
                    case R.id.ID_nav_privacy:
                        startActivity(new Intent(HomeAct.this, PrivacyAct.class));

                        break;
                    case R.id.ID_nav_contact_us:
                        startActivity(new Intent(HomeAct.this, ContactUsAct.class));

                        break;
                    case R.id.ID_nav_faq:
                        startActivity(new Intent(HomeAct.this, FaqAct.class));

                        break;
                    case R.id.ID_nav_aboutus:
                        startActivity(new Intent(HomeAct.this, AboutUsAct.class));

                        break;
                    case R.id.ID_nav_logout:
                        finish();
                        break;
                }
            }
        };

        for (int i = 0; i < iconBaseCont.getChildCount(); i++) {
            try {
                LinearLayout child = (LinearLayout) iconBaseCont.getChildAt(i);
                child.setOnClickListener(onIconsClickListener);
            } catch (Exception e) {
            }
        }

        ImageView navShadeBg = mNavigationView.findViewById(R.id.ID_nav_shade_bg);
        navShadeBg.setOnClickListener(view -> {
            mainDrawerLay.closeDrawers();
        });
        mainDrawerLay.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                navShadeBg.setVisibility(View.VISIBLE);
                navShadeBg.startAnimation(AnimationUtils.loadAnimation(HomeAct.this, R.anim.fade_in_anim));
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                navShadeBg.setVisibility(View.GONE);

            }

            @Override
            public void onDrawerStateChanged(int newState) {

                if (newState == DrawerLayout.STATE_DRAGGING) {
                    if (mainDrawerLay.isDrawerOpen(GravityCompat.START)) {
                        if (navShadeBg.getVisibility() == View.VISIBLE) {
                            navShadeBg.setVisibility(View.GONE);
                            navShadeBg.startAnimation(AnimationUtils.loadAnimation(HomeAct.this, R.anim.fade_out_anim));
                        }
                    }
                }
                if (newState == DrawerLayout.STATE_SETTLING) {
                    if (mainDrawerLay.isDrawerOpen(GravityCompat.START)) {
                        if (navShadeBg.getVisibility() == View.GONE) {
                            navShadeBg.setVisibility(View.VISIBLE);
                            navShadeBg.startAnimation(AnimationUtils.loadAnimation(HomeAct.this, R.anim.fade_in_anim));
                        }
                    }
                }
            }
        });

    }

}
