package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import net.promasoft.trawellmate.adp.AdpDaWiseDetails;
import net.promasoft.trawellmate.arg.DataDayWise;

import java.util.ArrayList;

public class DemoEditAct extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<DataDayWise> dataModel;
    private AdpDaWiseDetails adapter;
    private LinearLayout noDataLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_edit);

        initRecyler();
    }

    private void initRecyler() {

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


        noDataLay = findViewById(R.id.ID_daywise_no_data);
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
            noDataLay.setVisibility(View.GONE);
        } else {
            noDataLay.setVisibility(View.VISIBLE);
        }
    }

}
