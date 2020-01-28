package net.promasoft.trawellmate.frg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.util.DrawerFrgListner;

public class FrgAboutUs extends Fragment {

    private View view;
    private DrawerFrgListner mDrawerListner;

    public FrgAboutUs() {
        // Required empty public constructor
    }

    public static FrgAboutUs newInstance() {
        return new FrgAboutUs();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_booking, container, false);

        ImageView menuBar = view.findViewById(R.id.ID_arw_bck);
        menuBar.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_menu));
        menuBar.setOnClickListener(view1 -> {
            mDrawerListner.onDrawerClicked();
        });


        return view;
    }

    public void setDrawerListner(DrawerFrgListner drawerListner) {
        this.mDrawerListner = drawerListner;
    }


}
