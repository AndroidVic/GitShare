package net.promasoft.trawellmate;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.promasoft.trawellmate.adp.AdpNotificationDetails;
import net.promasoft.trawellmate.argapp.DataNotification;
import net.promasoft.trawellmate.util.AlineActivityHelper;

import java.util.ArrayList;
import java.util.List;

public class NotificationAct extends AppCompatActivity {

    AdpNotificationDetails notificationRecyclerAdapter;
    private List<DataNotification> mlist;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notification);
        new AlineActivityHelper(NotificationAct.this, false);

        ImageView arwBack = findViewById(R.id.ID_arw_bck);
        arwBack.setOnClickListener(view -> {
            onBackPressed();
        });


        recyclerView = findViewById(R.id.ID_notification_rv);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mlist = new ArrayList<>();
        retrievePackagesData();
        notificationRecyclerAdapter = new AdpNotificationDetails(mlist);
        recyclerView.setAdapter(notificationRecyclerAdapter);
    }


    private void retrievePackagesData() {
        // mlist = new ArrayList<>();
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));
        mlist.add(new DataNotification("Costa Rica", "Rs.85,000", "Grand European Tour", "Mar 12- Mar 20", "5 mins ago"));

    }

}