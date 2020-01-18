package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeAct extends AppCompatActivity {

    private ImageView searchLay;
    public boolean searchBgVisible = false;
    private LinearLayout bottom_menu_lay;
    private BottomNavigationView bottomNavigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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


        TextView viewPackages = findViewById(R.id.ID_view_packages);
        viewPackages.setOnClickListener(view -> {
            startActivity(new Intent(HomeAct.this, DetailsAct.class));
        });

        bottomNavigationMenu.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.ID_nav_account) {
                startActivity(new Intent(HomeAct.this, UserAccountAct.class));
            }
            return true;
        });
    }
}
