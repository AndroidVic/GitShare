package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;

import net.promasoft.trawellmate.adp.AdpDaWiseDetails;
import net.promasoft.trawellmate.arg.DataDayWise;
import net.promasoft.trawellmate.dsgn.CustomTextLink;
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.DelayHelper;
import net.promasoft.trawellmate.util.DiamensionManager;
import net.promasoft.trawellmate.util.ViewECAnimator;

import java.util.ArrayList;

public class DetailsAct extends AppCompatActivity {

    private ImageView statusBarBg;
    private boolean searchBgVisible = false;
    private NestedScrollView nestedContainer;
    private ImageView preExpandCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        new AlineActivityHelper(DetailsAct.this, true);
        statusBarBg = findViewById(R.id.ID_sts_bg_lay);


        nestedContainer = findViewById(R.id.ID_nested_container);

        AppBarLayout appBarLayout = findViewById(R.id.id_appBar_main);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    //  Collapsed
                    if (statusBarBg.getVisibility() == View.INVISIBLE) {
                        searchBgVisible = true;
//                        bottom_menu_lay.setVisibility(View.VISIBLE);
//                        bottom_menu_lay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
                        statusBarBg.setVisibility(View.VISIBLE);
                        statusBarBg.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_anim));
                    }

                } else if (verticalOffset == 0) {
                    // Expanded
                    if (statusBarBg.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;
                        statusBarBg.setVisibility(View.INVISIBLE);
                        statusBarBg.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_anim));
                    }
                } else if (searchBgVisible) {
                    //  Collapsed
                    if (statusBarBg.getVisibility() == View.VISIBLE) {
                        searchBgVisible = false;
//                        bottom_menu_lay.setVisibility(View.GONE);
//                        bottom_menu_lay.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
                        statusBarBg.setVisibility(View.INVISIBLE);
                        statusBarBg.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_anim));
                    }

                }

            }
        });


        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });

        initAboutPackage();

        initDaywiseLay();

        CustomTextLink writeReview = findViewById(R.id.ID_write_review);
        writeReview.setOnClickListener(view -> {
            startActivity(new Intent(DetailsAct.this, WriteReview.class));
        });

        Button bookATrip = findViewById(R.id.ID_hm_login);
        bookATrip.setOnClickListener(view -> {
            startActivity(new Intent(DetailsAct.this, BookingAct.class));

        });
    }

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<DataDayWise> dataModel;
    private AdpDaWiseDetails adapter;


    private void initDaywiseLay() {

        recyclerView = (RecyclerView) findViewById(R.id.ID_daywise_rv);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_fall_down));

        dataModel = new ArrayList<DataDayWise>();

        adapter = new AdpDaWiseDetails(getApplicationContext(), dataModel, new AdpDaWiseDetails.ClickLstnr() {
            @Override
            public void onClickItem(View view, int type) {

            }
        });

        recyclerView.setAdapter(adapter);

        ViewCompat.setNestedScrollingEnabled(recyclerView, false);

        recyclerView.setItemViewCacheSize(5);

        getData();
    }

    private void getData() {

        //TODO HERE
        dataModel.clear();
        DataDayWise data = new DataDayWise();
        data.mTitle = "Day 1- Arrive at Cochin - Munnar";
        data.mDescription = "You are greeted on arrival at Kochi International Airport/ Railway station by The Travel Planners representative and thereafter drive to Munnar hills (130 kms / 4 hrs). Enroute visit Cheyyappara Waterfalls. On the way to Munnar you can see tea gardens spread like a green carpet over hundreds of kilometers, lush green hills, a place so difficult to describe, very low population too far from pollution, most of the area covered by Tata tea. On arrival isExpanded in at Hotel and relax. Overnight stay at Nature Zone - Tree House";
        dataModel.add(data);
        DataDayWise data1 = new DataDayWise();
        data1.mTitle = "Day 2 - Munnar";
        data1.mDescription = "Morning sightseeing in Munnar which includes Mattupetty, Eco-Point, Tea Museum, Pothamedu etc. You can also enjoy boating in the Lake (optional). Overnight stay at Nature Zone - Tree House";
        dataModel.add(data1);

        DataDayWise data2 = new DataDayWise();
        data2.mTitle = "Day 3 - Munnar, Tekkady";
        data2.mDescription = "Morning drive to Thekkady covering a distance of 110 kms / 3.5 hrs through cardamom hills. Check into the Resort. After lunch visit spice plantation like cardamom, Pepper, Coffee, and tea estate etc… On way back to hotel you could stop at the local market and pick up some fragrant spices. Overnight at Poetree Sarovar - Club Room";
        dataModel.add(data2);

        DataDayWise data3 = new DataDayWise();
        data3.mTitle = "Day 4 - Tekkady";
        data3.mDescription = "Morning breakfast. Full day programme at Gavi (Jeep safari, boating, trekking) includes breakfast, lunch & entry fees. Gavi is an eco-tourist spot in Kerala. A must visit place for all nature lovers as Gavi is preserved in its natural scenic beauty untainted by the mighty hands of modernity. Evening come back at the hotel. Overnight stay at Poetree Sarovar - Club Room";
        dataModel.add(data3);

        DataDayWise data4 = new DataDayWise();
        data4.mTitle = "Day 5 - Tekkady, Houseboat";
        data4.mDescription = "Today morning after breakfast drive to Alleppey, Water locked place is endowed with immense natural beauty and array of rivers, canals and lakes ideal for boat cruise. On arrival isExpanded in to a traditional Kerala Style Houseboat and Cruise through the backwaters is the fabulous way to explore the fascinating beauty of the backwaters. Stay overnight in the Houseboat (Premium A/C [24 hour A/C]).";
        dataModel.add(data4);


        DataDayWise data5 = new DataDayWise();
        data5.mTitle = "Day 6 - Departure";
        data5.mDescription = "Departure transfer to Cochin airport for onward flight.";
        dataModel.add(data5);


        recyclerView.setItemViewCacheSize(dataModel.size());

        adapter.notifyDataSetChanged();

        if (dataModel.size() > 0) {
//            noDataLay.setVisibility(View.GONE);
        } else {
//            noDataLay.setVisibility(View.VISIBLE);
        }
    }

    FrameLayout expandable_view;
    Boolean isExpanded = false;
    ImageView arrow_btn;

    private void initAboutPackage() {
        preExpandCont = findViewById(R.id.ID_preexpnd_container);
        expandable_view = findViewById(R.id.expand);
        arrow_btn = findViewById(R.id.indicator_down);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_up);
        Drawable rdrawable = getResources().getDrawable(R.drawable.ic_arrow_down);
        expandable_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
                int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
                final int targetHeight = v.getMeasuredHeight();
                preExpandCont.setMinimumHeight(targetHeight);

                if (!isExpanded) {
//                    preExpandCont.setVisibility(View.VISIBLE);
                    DelayHelper.delayStartWith(100, () -> {
                        int scrollTo = ((View) preExpandCont.getParent().getParent()).getTop() - DiamensionManager.getInstance().convertDpToPixel(DetailsAct.this, 200) + targetHeight;
//                        int scrollTo = preExpandCont.getTop() + targetHeight - DiamensionManager.getInstance().convertDpToPixel(DetailsAct.this, 50);
                        nestedContainer.smoothScrollTo(0, scrollTo);
                    });
                    ViewECAnimator.expand(expandable_view, (int) DiamensionManager.getInstance().convertDpToPixel(DetailsAct.this, 210), targetHeight, 1);
                    arrow_btn.setImageDrawable(drawable);
                    isExpanded = true;

                } else {
//                    preExpandCont.setVisibility(View.GONE);
                    ViewECAnimator.collapse(expandable_view, (int) DiamensionManager.getInstance().convertDpToPixel(DetailsAct.this, 210), targetHeight, 1);
                    arrow_btn.setImageDrawable(rdrawable);
                    isExpanded = false;
                }

            }
        });
    }
}
