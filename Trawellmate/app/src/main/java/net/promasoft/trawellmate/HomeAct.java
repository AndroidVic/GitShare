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
import android.view.Menu;
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

import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.dsgn.CustomTextLink;
import net.promasoft.trawellmate.frg.FrgBooking;
import net.promasoft.trawellmate.frg.FrgCart;
import net.promasoft.trawellmate.frg.FrgHome;
import net.promasoft.trawellmate.frg.FrgPromptLogin;
import net.promasoft.trawellmate.frg.FrgSaved;
import net.promasoft.trawellmate.frg.FrgUserAccount;
import net.promasoft.trawellmate.frg.FrgUserProfile;
import net.promasoft.trawellmate.util.AlertMsgDialog;
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.AnimHelper;
import net.promasoft.trawellmate.util.DelayHelper;
import net.promasoft.trawellmate.util.DialogAdder;
import net.promasoft.trawellmate.util.DialogLogin;
import net.promasoft.trawellmate.util.DrawerFrgListner;
import net.promasoft.trawellmate.util.ToastHelper;

public class HomeAct extends AppCompatActivity implements DrawerFrgListner {

    private BottomNavigationView bottomNavigationMenu;

    private DrawerLayout mainDrawerLay;
    private NavigationView mNavigationView;
    private LinearLayout bottom_menu_lay;
    private DialogLogin dialogLogin;
    private CardView loginCircleBt;

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
        FrgCart frgCart = FrgCart.newInstance(HomeAct.this);
        FrgSaved frgSaved = FrgSaved.newInstance(HomeAct.this);
        FrgUserProfile frgUserProfile = FrgUserProfile.newInstance(HomeAct.this);
        FrgUserAccount frgUserAcc = FrgUserAccount.newInstance(HomeAct.this);

        frgHome.setDrawerListner(HomeAct.this);
        frgBooking.setDrawerListner(HomeAct.this);
        frgSaved.setDrawerListner(HomeAct.this);
        frgUserProfile.setDrawerListner(HomeAct.this);
        frgCart.setDrawerListner(HomeAct.this);

        bottomNavigationMenu = findViewById(R.id.ID_bottom_navigation);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.ID_nav_account) {
                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgUserAcc).commit();
            }
            if (id == R.id.ID_nav_booking) {
                if (SharedPrefHelper.getInstance(HomeAct.this).getIsLogin()) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgBooking).commit();
                } else {
                    FrgPromptLogin frgPromptLogin = FrgPromptLogin.newInstance(HomeAct.this, "My Trips", "Log in to check your packages", () -> {
                        dialogLogin = new DialogLogin(HomeAct.this,false, new DialogLogin.OnLoginListner() {
                            @Override
                            public void onLoginSuccess() {
                                loginCircleBt.setVisibility(View.GONE);
                                bottomNavigationMenu.getMenu().removeItem(R.id.ID_nav_account);
                                bottomNavigationMenu.getMenu().add(Menu.NONE, R.id.ID_nav_account, Menu.NONE, "Account").setIcon(R.drawable.ic_account_circle);

                                dialogLogin.closePage();
                                new ToastHelper(HomeAct.this, "Welcome John").setDuration(1000).show();
                                SharedPrefHelper.getInstance(HomeAct.this).setIsLogin(true);
                                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgHome).commit();

                            }

                            @Override
                            public void onSignUpClicked() {
                                startActivity(new Intent(HomeAct.this, SignupActivity.class));

                            }
                        });
                        dialogLogin.showPage();
                    });
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgPromptLogin).commit();
                }
            }
            if (id == R.id.ID_nav_explore) {
                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgHome).commit();
            }
            if (id == R.id.ID_nav_saved) {
                if (SharedPrefHelper.getInstance(HomeAct.this).getIsLogin()) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgSaved).commit();
                } else {
                    FrgPromptLogin frgPromptLogin = FrgPromptLogin.newInstance(HomeAct.this, "My Favourite", "Log in to check your packages", () -> {
                        dialogLogin = new DialogLogin(HomeAct.this,false, new DialogLogin.OnLoginListner() {
                            @Override
                            public void onLoginSuccess() {
                                loginCircleBt.setVisibility(View.GONE);
                                bottomNavigationMenu.getMenu().removeItem(R.id.ID_nav_account);
                                bottomNavigationMenu.getMenu().add(Menu.NONE, R.id.ID_nav_account, Menu.NONE, "Account").setIcon(R.drawable.ic_account_circle);

                                dialogLogin.closePage();
                                new ToastHelper(HomeAct.this, "Welcome John").setDuration(1000).show();
                                SharedPrefHelper.getInstance(HomeAct.this).setIsLogin(true);
                                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgHome).commit();
                            }

                            @Override
                            public void onSignUpClicked() {
                                startActivity(new Intent(HomeAct.this, SignupActivity.class));

                            }
                        });
                        dialogLogin.showPage();
                    });
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgPromptLogin).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgPromptLogin).commit();
                }
            }
            if (id == R.id.ID_nav_cart) {
                if (SharedPrefHelper.getInstance(HomeAct.this).getIsLogin()) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgCart).commit();
                } else {
                    FrgPromptLogin frgPromptLogin = FrgPromptLogin.newInstance(HomeAct.this, "My Cart", "Log in to check orders", () -> {
                        dialogLogin = new DialogLogin(HomeAct.this, false,new DialogLogin.OnLoginListner() {
                            @Override
                            public void onLoginSuccess() {
                                loginCircleBt.setVisibility(View.GONE);
                                bottomNavigationMenu.getMenu().removeItem(R.id.ID_nav_account);
                                bottomNavigationMenu.getMenu().add(Menu.NONE, R.id.ID_nav_account, Menu.NONE, "Account").setIcon(R.drawable.ic_account_circle);

                                dialogLogin.closePage();
                                new ToastHelper(HomeAct.this, "Welcome John").setDuration(1000).show();
                                SharedPrefHelper.getInstance(HomeAct.this).setIsLogin(true);
                                getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgHome).commit();
                            }

                            @Override
                            public void onSignUpClicked() {
                                startActivity(new Intent(HomeAct.this, SignupActivity.class));

                            }
                        });
                        dialogLogin.showPage();
                    });
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgPromptLogin).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.hm_base_container, frgPromptLogin).commit();
                }
            }
            return true;
        });

        loginCircleBt = findViewById(R.id.ID_hm_addons_login);

        if (SharedPrefHelper.getInstance(HomeAct.this).getIsLogin()) {
            loginCircleBt.setVisibility(View.GONE);
            bottomNavigationMenu.getMenu().removeItem(R.id.ID_nav_account);
            bottomNavigationMenu.getMenu().add(Menu.NONE, R.id.ID_nav_account, Menu.NONE, "Account").setIcon(R.drawable.ic_account_circle);

        }

        loginCircleBt.setOnClickListener(view -> {
            dialogLogin = new DialogLogin(HomeAct.this,false, new DialogLogin.OnLoginListner() {
                @Override
                public void onLoginSuccess() {
                    loginCircleBt.setVisibility(View.GONE);
                    bottomNavigationMenu.getMenu().removeItem(R.id.ID_nav_account);
                    bottomNavigationMenu.getMenu().add(Menu.NONE, R.id.ID_nav_account, Menu.NONE, "Account").setIcon(R.drawable.ic_account_circle);

                    dialogLogin.closePage();
                    new ToastHelper(HomeAct.this, "Welcome John").setDuration(1000).show();
                    SharedPrefHelper.getInstance(HomeAct.this).setIsLogin(true);
                }

                @Override
                public void onSignUpClicked() {
                    startActivity(new Intent(HomeAct.this, SignupActivity.class));

                }
            });
            dialogLogin.showPage();
//
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
                        new AlertMsgDialog(HomeAct.this, AlertMsgDialog.DIA_LOGOUT, "Logout", "Are you sure to exit", "exit", new AlertMsgDialog.ActionListner() {
                            @Override
                            public void onPositive() {
                                SharedPrefHelper.getInstance(HomeAct.this).setIsLogin(false);
                                finish();
                            }

                            @Override
                            public void onNegative() {

                            }
                        }).showPage();
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
}
