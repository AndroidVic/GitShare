package net.promasoft.trawellmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.DialogLogin;

public class HomeAct extends AppCompatActivity {

    private ImageView searchLay;
    public boolean searchBgVisible = false;
    private LinearLayout bottom_menu_lay;
    private BottomNavigationView bottomNavigationMenu;
    private DrawerLayout mainDrawerLay;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new AlineActivityHelper(HomeAct.this, false);

        initDrawer();


        Button loginBt = findViewById(R.id.ID_hm_login);
        loginBt.setOnClickListener(view -> {
            loginBt.setVisibility(View.GONE);
            new DialogLogin(HomeAct.this).showPage();
        });

        bottomNavigationMenu = findViewById(R.id.ID_bottom_navigation);
        bottom_menu_lay = findViewById(R.id.ID_bottom_menu_lay);
        searchLay = findViewById(R.id.ID_search_bg_lay);

        AppBarLayout appBarLayout = findViewById(R.id.id_appBar_main);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    //  Collapsed
                    if (searchLay.getVisibility() == View.GONE) {
                        searchBgVisible = true;
                        bottom_menu_lay.setVisibility(View.VISIBLE);
                        bottom_menu_lay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
                        searchLay.setVisibility(View.VISIBLE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_anim));
                    }

                } else if (verticalOffset == 0) {
                    // Expanded
                    if (searchLay.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;
                        searchLay.setVisibility(View.GONE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_anim));
                    }
                } else if (searchBgVisible) {
                    //  Collapsed
                    if (searchLay.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;
                        bottom_menu_lay.setVisibility(View.GONE);
                        bottom_menu_lay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
                        searchLay.setVisibility(View.GONE);
                        searchLay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_anim));
                    }

                }

            }
        });


        ImageView notification = findViewById(R.id.ID_hm_notification);
        notification.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, NotificationAct.class));
        });

        TextView viewPackages = findViewById(R.id.ID_view_packages);
        viewPackages.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });

        CardView viewPackages1 = findViewById(R.id.ID_hm_pack1);
        viewPackages1.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });
        CardView viewPackages2 = findViewById(R.id.ID_hm_pack2);
        viewPackages2.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });
        CardView viewPackages3 = findViewById(R.id.ID_hm_pack3);
        viewPackages3.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });
        CardView viewPackages4 = findViewById(R.id.ID_hm_pack4);
        viewPackages4.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });
        CardView viewPackages5 = findViewById(R.id.ID_hm_pack5);
        viewPackages5.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });
        CardView viewPackages6 = findViewById(R.id.ID_hm_pack6);
        viewPackages6.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });

        bottomNavigationMenu.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.ID_nav_account) {
                startActivity(new Intent(HomeAct.this, UserAccountAct.class));
            }
            if (id == R.id.ID_nav_booking) {
                startActivity(new Intent(HomeAct.this, YourBookings.class));
            }
            return true;
        });


    }


    private void initDrawer() {
        mainDrawerLay = findViewById(R.id.drawer_layout);
        ImageView menuSlider = findViewById(R.id.ID_menu_iv);
        menuSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mainDrawerLay.isDrawerOpen(GravityCompat.START)) {
                    mainDrawerLay.openDrawer(GravityCompat.START);
                }

            }
        });

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
